/**
 * Project Name:MyJava  
 * File Name:Office2.java  
 * Package Name:api.lang.ClassLoader  
 * Date:Jan 25, 20133:22:41 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.ClassLoader;

/**
 * ClassName: Office2 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 25, 2013 3:22:41 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class Office2 {
    static {
        System.out.println("this is Office2 static block.");
    }
    
    
    public void print(String str) {
        System.out.println("嗨，" + str + ", 你终于找到我了！");  
    }
    public String toString() {
        return "我是一个A对象！";  
    }   
}
