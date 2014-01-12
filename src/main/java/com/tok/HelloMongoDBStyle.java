package com.tok;
import com.mongodb.*;

import java.net.UnknownHostException;

/**
**/
public class HelloMongoDBStyle {
    public static void main(String[] args) throws UnknownHostException{
        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));

        // get the database
        DB database = client.getDB("m101");
        // get the collection from DB
        DBCollection collection = database.getCollection("hw1");
        DBObject document = collection.findOne();
        System.out.println(document);

    }

}
