/**
 * Project Name:MyJava  
 * File Name:UserAnnotation.java  
 * Package Name:api.lang.annotation  
 * Date:Oct 26, 201310:55:13 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.annotation;

import java.util.HashMap;
import java.util.Map;

/**
         * ClassName: UserAnnotation <br/>  
         * Function: TODO ADD FUNCTION. <br/>  
         * Reason: TODO ADD REASON(可选). <br/>  
         * date: Oct 26, 2013 10:55:13 AM <br/>  
         *  
         * @author Leslie  
         * @version   
         * @since version 1.0  
         */
public class UserAnnotation {
    @TestA(name = "param", id = 1, gid = Long.class)
    //类成员注解
    private Integer age;

    @TestA(name = "construct", id = 2, gid = Long.class)
    //构造方法注解
    public UserAnnotation() {
    }

    @TestA(name = "public method", id = 3, gid = Long.class)
    //类方法注解
    public void a() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    @TestA(name = "protected method", id = 4, gid = Long.class)
    //类方法注解
    protected void b() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    @TestA(name = "private method", id = 5, gid = Long.class)
    //类方法注解
    private void c() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    public void b(Integer a) {
    }
}
