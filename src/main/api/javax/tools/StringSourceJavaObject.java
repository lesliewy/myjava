/**
 * Project Name:MyJava  
 * File Name:StringSourceJavaObject.java  
 * Package Name:api.javax.tools  
 * Date:Feb 3, 20138:51:53 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.javax.tools;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;
import javax.tools.JavaFileObject.Kind;

/**
 * ClassName: StringSourceJavaObject <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 3, 2013 8:51:53 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class StringSourceJavaObject extends SimpleJavaFileObject {

    private String content = null;
        /**
         * Creates a new instance of StringSourceJavaObject.  
         *  
         * @param uri
         * @param kind  
         */
    protected StringSourceJavaObject(String name, String content) {
        super(URI.create("string:///" + name.replace("\\.","/") + Kind.SOURCE.extension), Kind.SOURCE); 
//        super(URI.create(name.replace("\\.","/") + Kind.SOURCE.extension), Kind.SOURCE); 
        this.content = content; 
    }
    
    public CharSequence getCharContent(boolean  ignoreEncodingErrors) throws IOException { 
        return content; 
    }

}
