package tProps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Harry on 09/05/2015.
 */
public class TwitterPropertiesLoader {

    public TwitterProperties getProperties() throws IOException {

        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        TwitterProperties twitterProperties = new TwitterProperties();

        twitterProperties.setConsumerKey(prop.getProperty("consumerKey"));
        twitterProperties.setSecretConsumerKey(prop.getProperty("secretConsumerKey"));
        twitterProperties.setAccessKey(prop.getProperty("accessToken"));
        twitterProperties.setSecretAccessKey(prop.getProperty("secretAccessToken"));
        twitterProperties.setDbHost(prop.getProperty("host"));
        twitterProperties.setDbPort(Integer.parseInt(prop.getProperty("port")));
        twitterProperties.setDbName(prop.getProperty("dbName"));
        twitterProperties.setDbCollectionName(prop.getProperty("collectionName"));
        twitterProperties.setClientName(prop.getProperty("clientName"));


        return twitterProperties;
    }

}
