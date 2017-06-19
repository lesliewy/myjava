/**
 * Project Name:MyJava  
 * File Name:Assembly.java  
 * Package Name:api.net.URLClassLoader  
 * Date:Jan 25, 20134:03:43 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.net.URLClassLoader;

/**
 * ClassName: Assembly <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 25, 2013 4:03:43 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class Assembly {
    static {
        System.out.println("this is Assembly static block.");
    }
    public void start(){
        System.out.println("this is Assembly start().");
    }
}
