/**
 * Project Name:MyJava  
 * File Name:PasswordUtils.java  
 * Package Name:api.lang.annotation  
 * Date:Jan 30, 20135:31:17 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.annotation;

import java.util.List;

/**
 * ClassName: PasswordUtils <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 30, 2013 5:31:17 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class PasswordUtils {
    
    @UseCase(id = 47, description = 
    "Passwords must contain at least one numeric") 
    public boolean validatePassword(String password) { 
        return (password.matches("\\w*\\d\\w*")); 
    } 
    
    @UseCase(id = 48) 
    public String encryptPassword(String password) { 
        return new StringBuilder(password).reverse().toString(); 
    }
    
    @UseCase(id = 49, description = 
    "New passwords can’t equal previously used ones") 
    public boolean checkForNewPassword(List<String> prevPasswords, String password) { 
        return !prevPasswords.contains(password); 
    }
}
