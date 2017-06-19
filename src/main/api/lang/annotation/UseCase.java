/**
 * Project Name:MyJava  
 * File Name:UseCase.java  
 * Package Name:api.lang.annotation  
 * Date:Jan 30, 20135:26:56 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * ClassName: UseCase <br/>  
 * Function: Test Annotation: UseCase
 * Reason:   
 * date: Jan 30, 2013 5:26:56 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id();
    public String description() default "no description";
}
