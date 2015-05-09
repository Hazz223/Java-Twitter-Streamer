import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import tProps.TwitterProperties;

import java.net.UnknownHostException;

/**
 * Created by Harry on 09/05/2015.
 */
public class MongoTwitterDb {

    public DBCollection createDbCollection(TwitterProperties properties) throws UnknownHostException {

        System.out.println("Creating Mongo Db client");
        MongoClient mongoClient = new MongoClient(properties.getDbHost(), properties.getDbPort());
        DB db = mongoClient.getDB(properties.getDbName());

        DBCollection coll = db.getCollection(properties.getDbCollectionName());

        return coll;
    }

}
