/**
 * Project Name:MyJava  
 * File Name:User.java  
 * Package Name:examples.java.transient1  
 * Date:Feb 20, 2017 12:07:49 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package examples.java.transient1;

import java.io.Serializable;

/**
 * ClassName: User <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 20, 2017 12:07:49 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class User implements Serializable{
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private transient String password;
	
	private static String address = "NanJing";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		User.address = address;
	}
	
}
