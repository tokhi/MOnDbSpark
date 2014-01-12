package com.tok;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * spark and free marker combination.
 */
public class HelloWorldSparkFreeMarkerStyle {
    public static void main(String[] args) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class,"/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template helloTemplate = config.getTemplate("hello.ftl");
                    HashMap<String,Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name","FreeMarker!");
                    helloTemplate.process(helloMap,stringWriter);
                    System.out.println(stringWriter);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return stringWriter;
            }
        });

        Spark.get(new Route("/fruits") {
            @Override
            public Object handle(Request request, Response response) {
               Map<String,Object> fruitsMap = new HashMap<String,Object>();
               fruitsMap.put("fruits", Arrays.asList("peach", "banana", "grapes", "annanas"));
                StringWriter stringWriter = new StringWriter();
                try {
                    Template fruitTemplate = config.getTemplate("fruits.ftl");
                    fruitTemplate.process(fruitsMap,stringWriter);
                    System.out.println(stringWriter);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return stringWriter;
            }
        });
        Spark.post(new Route("/favorite_fruits") {
            @Override
            public Object handle(Request request, Response response) {
                final String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "why you don't pick up a fruit?";
                }
                else{
                    return "your favorite fruit is " +fruit;
                }
            }
        });
    }
}
