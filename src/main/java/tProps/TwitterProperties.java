package tProps;

/**
 * Created by Harry on 09/05/2015.
 */
public class TwitterProperties {

    private String consumerKey;
    private String secretConsumerKey;
    private String accessKey;
    private String secretAccessKey;
    private String dbHost;
    private Integer dbPort;
    private String dbName;
    private String dbCollectionName;
    private String clientName;


    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getSecretConsumerKey() {
        return secretConsumerKey;
    }

    public void setSecretConsumerKey(String secretConsumerKey) {
        this.secretConsumerKey = secretConsumerKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public Integer getDbPort() {
        return dbPort;
    }

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbCollectionName() {
        return dbCollectionName;
    }

    public void setDbCollectionName(String collectionName) {
        this.dbCollectionName = collectionName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
