package twitterStreamer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.twitter.hbc.core.Client;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Harry on 08/05/2015.
 */
public class TwitterStreamThread implements Runnable {


    private Client hoseBirdClient;
    private BlockingQueue<String> msgQueue;
    private Boolean keepRunning = true;
    private DBCollection db;

    public TwitterStreamThread(Client hoseBirdClient, BlockingQueue<String> msgQueue, DBCollection db) {
        this.hoseBirdClient = hoseBirdClient;
        this.msgQueue = msgQueue;
        this.db = db;
    }

    public void run() {

        this.runTwitterFeed(db);
    }

    private void runTwitterFeed(DBCollection coll) {

        Long numberOfTweetsAdded = 0L;
        Date date = new Date();

        while (keepRunning) {
            try {

                String time = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(date);

                String message = msgQueue.take();
                JSONObject jsonTweet = new JSONObject(message);
                String text = jsonTweet.getString("text");
                JSONObject jsonUser = jsonTweet.getJSONObject("user");
                String name = jsonUser.getString("name");
                String userName = jsonUser.getString("screen_name");
                String profileImage = jsonUser.getString("profile_image_url_https");
                String location = jsonUser.getString("location");

                BasicDBObject tweet = new BasicDBObject("name", name)
                        .append("text", text)
                        .append("profileImage", profileImage)
                        .append("screenName", userName)
                        .append("location", location)
                        .append("dateAdded", time);
                coll.insert(tweet);

                numberOfTweetsAdded++;

                if (numberOfTweetsAdded % 10 == 0) {
                    System.out.println("Number of tweets added since the start of program: " + numberOfTweetsAdded);
                    System.out.println("Logged at: " + time);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                keepRunning = false;
            } catch (Exception ex) {
                ex.printStackTrace();
                keepRunning = false;
            }
        }
    }

    public synchronized void turnOffStream() {

        this.keepRunning = false;
    }
}
