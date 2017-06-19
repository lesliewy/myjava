/**
 * Project Name:MyJava  
 * File Name:UseCaseTracker.java  
 * Package Name:api.lang.annotation  
 * Date:Jan 30, 20135:34:20 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: UseCaseTracker <br/>  
 * Function: Test Annotion: UseCase
 * Reason:   
 * date: Jan 30, 2013 5:34:20 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class UseCaseTracker {

    public static void trackUseCases(List<Integer> useCases, Class<?> cl) { 
        for(Method m : cl.getDeclaredMethods()) { 
            UseCase uc = m.getAnnotation(UseCase.class); 
            if(uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " " + uc.description()); 
                useCases.remove(new Integer(uc.id())); 
            }
        }
        for(int i : useCases) {
            System.out.println("Warning: Missing use case-" + i); 
        }
    }
    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>(); 
        Collections.addAll(useCases, 47, 48, 49, 50); 
        trackUseCases(useCases, PasswordUtils.class); 
    }
}
