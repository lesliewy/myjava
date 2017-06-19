/**
 * Project Name:MyJava  
 * File Name:TestException1.java  
 * Package Name:api.lang.Exception  
 * Date:Mar 27, 201311:03:00 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.exception;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class TestException1 {
    /**
     * 继承自 java.lang.Throwable
     * printStackTrace(): 第一行是 detail message, 后面是stack trace;
     * getMessage(): detail message;  Exception("aaa"); 参数是detail message.
     * 
     */
    @Test
    public void test1() {
        try {
            throwExcep1();
        } catch (Exception e) {
            System.out.println("e.getMessage(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void throwExcep1() throws Exception {
        throw new Exception("aaa");
    }

    /**
     * 创建一个新exception,只需extends Exception
     *  
     */
    @Test
    public void test2() {
        try {
            a();
        } catch (HighLevelException e) {
            e.printStackTrace();
        }
    }

    static void a() throws HighLevelException {
        try {
            b();
        } catch (MidLevelException e) {
            throw new HighLevelException(e);
        }
    }

    static void b() throws MidLevelException {
        c();
    }

    static void c() throws MidLevelException {
        try {
            d();
        } catch (LowLevelException e) {
            throw new MidLevelException(e.fillInStackTrace());
        }
    }

    static void d() throws LowLevelException {
        e();
    }

    static void e() throws LowLevelException {
        //        throw new LowLevelException();
        try {
            URL url = new URL("AAA://ABC");
        } catch (MalformedURLException e1) {
            System.out.println("e1.cause:" + e1.getCause());
            System.out.println("e1.stacktrace: " + e1.getStackTrace());
            //            e1.printStackTrace();
            throw new LowLevelException(e1.getCause());
        }
    }
}

class HighLevelException extends Exception {

    private static final long serialVersionUID = 1L;

    HighLevelException(Throwable cause) {
        super(cause);
    }
}

class MidLevelException extends Exception {
    private static final long serialVersionUID = 1L;

    MidLevelException(Throwable cause) {
        super(cause);
    }
}

class LowLevelException extends Exception {

    /**
     * Creates a new instance of LowLevelException.  
     *  
     * @param fillInStackTrace  
     */
    public LowLevelException(Throwable fillInStackTrace) {
        new Exception(fillInStackTrace);
    }

    private static final long serialVersionUID = 4141026452013534310L;
}
