/**
 * Project Name:MyJava  
 * File Name:AssertTest1.java  
 * Package Name:api.lang.AssertionError  
 * Date:Nov 15, 20134:32:49 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.AssertionError;

public class AssertTest1 {
    /*
     * java api.lang.AssertionError.AssertTest1:   输出 aaa;  默认是disable assertion;
     * java -ea api.lang.AssertionError.AssertTest1: AssertionError异常;  enable assertion;
     */
    public static void main(String[] args) {
        // assert expression1(bool)
//        assert 1 > 5;
        
        // assert expression1:expression2     expression2是抛出AssertionError时带的信息;
        assert 1>5:"error, 1<=5";
        System.out.println("aaa");
    }
}
