/**
 * Project Name:MyJava  
 * File Name:SettingLocale.java  
 * Package Name:api.util.Locale.examples  
 * Date:Nov 13, 201311:44:57 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Locale.setting;

import java.util.Locale;

import org.junit.Test;

/*
 * the language code and the optional script, region, and variant codes.
 * 
 */
public class CreatingLocale {

    /*
     * Locale.Builder Class:
     * Java SE 7 新增;
     * 
     */
    @Test
    public void test1(){
        Locale aLocale = new Locale.Builder().setLanguage("fr").setRegion("CA").build();
        
        Locale bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        Locale cLocale = new Locale.Builder().setLanguage("en").setRegion("GB").build(); // Great Britain
        
        Locale dLocale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();

    }
    
    /*
     * Locale Constructors:
     * Locale(String language)
     * Locale(String language, String country)
     * Locale(String language, String country, String variant)

     */
    @Test
    public void test2(){
        Locale aLocale = new Locale("fr", "CA");
        Locale bLocale = new Locale("en", "US");
        Locale cLocale = new Locale("en", "GB");
        Locale dLocale = new Locale("ru");
    }
    
    /*
     * forLanguageTag Factory Method:
     * Java SE 7 新增;
     */
    @Test
    public void test3(){
        Locale aLocale = Locale.forLanguageTag("en-US");
        Locale bLocale = Locale.forLanguageTag("ja-JP-u-ca-japanese");
    }
    
    /*
     * Locale Constants:
     * 
     */
    @Test
    public void test4(){
        Locale cLocale = Locale.JAPAN;
        Locale dLocale = Locale.CANADA_FRENCH;

        // 3个等价;
        Locale j1Locale = Locale.JAPANESE;
        Locale j2Locale = new Locale.Builder().setLanguage("ja").build();
        Locale j3Locale = new Locale("ja");

        // 3个等价;
        Locale j4Locale = Locale.JAPAN;
        Locale j5Locale = new Locale.Builder().setLanguage("ja").setRegion("JP").build();
        Locale j6Locale = new Locale("ja", "JP");
    }
}
