package api;

import java.util.Enumeration;
import java.util.Properties;

public class AboutSystem {
	public static void main(String[] args){
//		System.exit(1);
		/*
		 * 
		 * 
		 */
		long beginTime=System.currentTimeMillis();
		System.out.println("now");
		long endTime=System.currentTimeMillis();
		System.out.println("endTime-begTime:"+(endTime-beginTime));
		
		Properties sp = System.getProperties();
		Enumeration e = sp.propertyNames();
		while(e.hasMoreElements()){
			String key=(String)e.nextElement();
			System.out.println("key:"+key+"   value:"+sp.getProperty(key));
		}
	}

}
