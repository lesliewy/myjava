/**
 * Project Name:MyJava  
 * File Name:AboutSequenceInputStream.java  
 * Package Name:api.io  
 * Date:Sep 9, 20139:32:57 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;

import org.junit.Test;

public class AboutSequenceInputStream {
    @Test
    public void test1(){
        try {
            SequenceInputStream si = new SequenceInputStream(new FileInputStream("3.txt"),new FileInputStream("4.txt"));
            int c;
            while(( c = si.read()) != -1){
                System.out.print((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
              
            // TODO Auto-generated catch block  
            e.printStackTrace();  
                  
        }
    }
    
    @Test
    public void test2(){
        Vector<InputStream> v = new Vector<InputStream>();
        try {
            v.addElement(new FileInputStream("3.txt"));
            v.addElement(new FileInputStream("4.txt"));
            v.addElement(new FileInputStream("test1.dat"));
            v.addElement(new FileInputStream("text.xml"));
            SequenceInputStream si = new SequenceInputStream(v.elements());
            int c = 0;
            while((c = si.read()) != -1){
                System.out.print((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
                  
        }
    }
}
