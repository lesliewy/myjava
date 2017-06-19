/**
 * Project Name:MyJava  
 * File Name:AboutAbs.java  
 * Package Name:api.lang.Math  
 * Date:Dec 14, 20124:55:07 PM  
 * Copyright (c) 2012, wy All Rights Reserved.  
 *  
 */
package api.lang.Math;


/**
 * ClassName: AboutAbs <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Dec 14, 2012 4:55:07 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class AboutAbs {
    /*
     * abs 使用时如果是 Integer.MIN_VALUE   Long.MIN_VALUE  仍然是该值.
     */
    public void test1(){
        System.out.println("Integer.MIN_VALUE:"+Integer.MIN_VALUE);
        System.out.println("Long.MIN_VALUE:"+Long.MIN_VALUE);  
        System.out.println(Math.abs(-3));
        // 负数
        System.out.println(Math.abs(Integer.MIN_VALUE));   
        System.out.println(Math.abs(Integer.MIN_VALUE+1));
        // 负数
        System.out.println(Math.abs(Long.MIN_VALUE));    
        System.out.println(Math.abs(Long.MIN_VALUE+1));
    }
}
