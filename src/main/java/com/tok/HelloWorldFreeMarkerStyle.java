package com.tok;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

/**
 *
 */
public class HelloWorldFreeMarkerStyle {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class,"/");

        try {
            Template helloTemplate = config.getTemplate("hello.ftl");
            StringWriter stringWriter = new StringWriter();
            HashMap<String,Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name","FreeMarker!");
            helloTemplate.process(helloMap,stringWriter);
            System.out.println(stringWriter);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
