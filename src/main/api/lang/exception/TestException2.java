/**
 * Project Name:MyJava  
 * File Name:TestException2.java  
 * Package Name:api.lang.exception  
 * Date:Mar 28, 20139:59:45 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.exception;

import java.util.List;

import org.junit.Test;


/**
 * ClassName: TestException2 <br/>  
 * Function:  
 * Reason: 
 * date: Mar 28, 2013 9:59:45 AM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestException2 {
    /**
     *  1,绝对不要直接catch exception, 因为 NPE、OutOfMemoryErrors 等异常也能被捕获.
     */
    @Test
    public void test1(){
        try{
            throwNPE();
        } catch (Exception e){
            System.out.println("catch NPE.");
        }
        System.out.println("success.");
    }
    
    private void throwNPE(){
        List a = null;
        a.size();
    }
}
