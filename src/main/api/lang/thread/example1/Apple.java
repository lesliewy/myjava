/**
 * Project Name:MyJava  
 * File Name:Apple.java  
 * Package Name:api.lang.thread.example1  
 * Date:Feb 16, 2017 12:01:39 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.lang.thread.example1;

/**
 * ClassName: Apple <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 16, 2017 12:01:39 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Apple {
	private int id = 0;
	private String name = "apple";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Apple id: " + id + " name:" + name;
	}
}
