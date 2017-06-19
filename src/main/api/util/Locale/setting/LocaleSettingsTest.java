/**
 * Project Name:MyJava  
 * File Name:LocaleSettingsTest.java  
 * Package Name:api.util.Locale.setting  
 * Date:Nov 13, 20132:31:37 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Locale.setting;

import java.text.DateFormat;
import java.util.Locale;

import org.junit.Test;

public class LocaleSettingsTest {

    /*
     * 判断 DateFormat 支持哪些locale;
     */
    @Test
    public void test1(){
        Locale list[] = DateFormat.getAvailableLocales();
        for (Locale aLocale : list) {
//            System.out.println(aLocale.toString());
            System.out.println(aLocale.getDisplayName());
        }
    }
    
    /*
     * 默认的Locale:
     * Set by the Java Virtual Machine when it starts up, the default Locale corresponds to the locale of the host platform
     * On the Windows platform, these default values are initialized according to the "Standards and Formats" and "Display Language" settings in the Windows control panel
     * 
     */
    @Test
    public void test2(){
        Locale defaultLocale = Locale.getDefault();
//        Locale defaultLocale = Locale.getDefault(Locale.Category.FORMAT);
        System.out.println(defaultLocale.toString());
    }
}
