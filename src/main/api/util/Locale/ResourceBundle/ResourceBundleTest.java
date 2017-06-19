/**
 * Project Name:MyJava  
 * File Name:ResourceBundleTest.java  
 * Package Name:api.util.Locale.ResourceBundle  
 * Date:Nov 13, 20132:49:57 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Locale.ResourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class ResourceBundleTest {

    /*
     * base name_language code_country code_variant 
     * ButtonLabel
       ButtonLabel_de
       ButtonLabel_en_GB
       ButtonLabel_fr_CA_UNIX
     * 
     * 
     * 查找 ResourceBundle 的优先顺序:
     *  ButtonLabel_fr_CA_UNIX
        ButtonLabel_fr_CA
        ButtonLabel_fr
        ButtonLabel_en_US
        ButtonLabel_en
        ButtonLabel
     * 其中default locale 是 en_US;
     * 
     */
    @Test
    public void test1(){
        Locale currentLocale = new Locale("fr", "CA", "UNIX");
        ResourceBundle introLabels = ResourceBundle.getBundle(
                                         "ButtonLabel", currentLocale);
    }
    
    /*
     * ListResourceBundle  PropertyResourceBundle  两种实现;
     * 
     */
}
