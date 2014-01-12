package com.tok;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * simple spark demo
 */
public class HelloWorldSparkStyle{
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello world from Spark!";
            }
        });
    }




}
