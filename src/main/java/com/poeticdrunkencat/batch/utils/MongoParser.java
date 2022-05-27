package com.poeticdrunkencat.batch.utils;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;

public class MongoParser {

    String dbName;
    String colectionName;
    String host = System.getenv("MONGO_HOST");
    String port = System.getenv("MONGO_PORT");
    String userName = System.getenv("MONGO_USER");
    String password = System.getenv("MONGODB_ROOT_PASSWORD");
    String mongouri;




    public MongoParser(String dbName, String colectionName,boolean userAuth) {
        this.dbName = dbName;
        this.colectionName = colectionName;
        if (userAuth) {
            this.mongouri = "mongodb://" + this.userName + ":" + this.password + "@" + this.host + ":" + this.port + "/?authSource=" + this.dbName;
        }else{
            this.mongouri = "mongodb://" + this.host + ":" + this.port + "/?authSource=" + this.dbName;
        }
    }

}
