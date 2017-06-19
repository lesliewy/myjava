/**
 * Project Name:MyJava  
 * File Name:I18NSample.java  
 * Package Name:api.util.Locale.examples  
 * Date:Nov 13, 201311:20:01 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Locale.examples;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * 需要:
 * MessagesBundle.properties
   MessagesBundle_de_DE.properties
   MessagesBundle_en_US.properties
   MessagesBundle_fr_FR.properties
 *
 * java api.util.Locale.examples.I18NSample fr FR
 * 
 */
public class I18NSample {
    static public void main(String[] args) {

        String language;
        String country;

        if (args.length != 2) {
            language = new String("en");
            country = new String("US");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }

        Locale currentLocale;
        ResourceBundle messages;

        // 创建 Locale
        currentLocale = new Locale(language, country);

        // 获取 bundle
        messages = ResourceBundle.getBundle("api.util.Locale.examples.MessagesBundle", currentLocale);
        
        // 获取String
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));
    }

}
