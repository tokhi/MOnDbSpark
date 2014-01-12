package com.tok;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 */
public class HelloMongoDbSparkFreeMarkerStyle {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class,"/");

        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));

        // get the database
        DB database = client.getDB("m101");
        // get the collection from DB
        final DBCollection collection = database.getCollection("hw1");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template helloTemplate = config.getTemplate("hello.ftl");
                   DBObject document = collection.findOne();
                    helloTemplate.process(document, stringWriter);
                    System.out.println(stringWriter);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return stringWriter;
            }
        });

        Spark.get(new Route("/echo/:thing") {
            @Override
            public Object handle(Request request, Response response) {
              return request.params(":thing");
            }
        });
    }

}
