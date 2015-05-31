package twitterStreamer;

import com.google.common.collect.Lists;
import com.mongodb.DBCollection;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import tProps.TwitterProperties;
import tProps.TwitterPropertiesLoader;
import tProps.TwitterSearchTerms;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Harry on 08/05/2015.
 */


public class Main {

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

        TwitterSearchTerms searchTerms = new TwitterSearchTerms();
        List<String> terms = searchTerms.getSearchTerms();

        if(terms.size() == 0){
            System.out.println("No Search terms found. Please add some.");
            throw new RuntimeException("Failed");
        }

        try {
            TwitterProperties tProperties = new TwitterPropertiesLoader().getProperties();

            Client hoseBirdClient = createHoseBirdClient(
                    tProperties,
                    msgQueue,
                    eventQueue,
                    terms);

            DBCollection collection = new MongoTwitterDb().createDbCollection(tProperties);

            hoseBirdClient.connect();

            TwitterStreamThread twitterStreamThread = new TwitterStreamThread(hoseBirdClient, msgQueue, collection);
            Thread twitterThread = new Thread(twitterStreamThread);

            System.out.println("Press any key to end the program");

            twitterThread.start();

            String input = scan.nextLine();

            twitterStreamThread.turnOffStream();

            hoseBirdClient.stop();

            System.out.println("Goodbye!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Client createHoseBirdClient(TwitterProperties properties, BlockingQueue<String> msgQueue, BlockingQueue<Event> eventQueue, List<String> terms) {

        Hosts hoseBirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        hosebirdEndpoint.filterLevel(Constants.FilterLevel.None);

        hosebirdEndpoint.trackTerms(terms);

        Authentication hoseBirdAuth = new OAuth1(
                properties.getConsumerKey(),
                properties.getSecretConsumerKey(),
                properties.getAccessKey(),
                properties.getSecretAccessKey());

        ClientBuilder builder = new ClientBuilder()
                .name(properties.getClientName())
                .hosts(hoseBirdHosts)
                .authentication(hoseBirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);

        Client hosebirdClient = builder.build();

        return hosebirdClient;
    }
}
