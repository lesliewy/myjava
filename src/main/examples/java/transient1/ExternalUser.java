/**
 * Project Name:MyJava  
 * File Name:ExternalUser.java  
 * Package Name:examples.java.transient1  
 * Date:Feb 20, 2017 12:26:59 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package examples.java.transient1;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * date: Feb 20, 2017 12:26:59 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class ExternalUser implements Externalizable{
	private transient String content = "abcd";
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(content);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		content = (String)in.readObject();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
