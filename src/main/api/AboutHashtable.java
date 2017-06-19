package api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public class AboutHashtable {
	public static void main(String[] args){
		/*
		 * 1,Hashtable<K,V> 第一个是key，可以Enumeration keyEnum = ht.keys();获得每一个值.
		 *                  第二个是value,Enumeration eleEnum = ht.elements();获得每一个值.
		 * 2,ht.get(key): 此处相当于执行 key.equals(k),其中k是ht这个hashtable中的key，此时如果key中没有覆写equals()和hashCde()方法，调用Object.equals()和Object.hashCode()
		 *                默认都是根据地址来计算的,所以ht.get(new MyKey("zhangsan",18)，即使Hashtable中存在也找不到.
		 * 
		 */
		Hashtable ht=new Hashtable();
		ht.put(new MyKey("zhangsan",18), 1);
		ht.put(new MyKey("lisi",19), 2);
		ht.put(new MyKey("wangwu",20), 3);
     	Enumeration keyEnum = ht.keys();
     	Enumeration eleEnum = ht.elements();
     	while(keyEnum.hasMoreElements()){
     		MyKey key=(MyKey)keyEnum.nextElement();
     		System.out.println("key="+key+"   value="+ht.get(key));     // 此处key.toString()方法.
     		
//     		System.out.println("element="+eleEnum.nextElement());
     	}
     	System.out.println("new key:"+ht.get(new MyKey("zhangsan",18)));
     	
     	System.out.println("==============================");
     	/*
     	 * 1,Properties 是Hashtable的子类，专门用来处理key value是String类型的情况.
     	 * 
     	 */
     	Properties settings = new Properties();
     	try {
			settings.load(new FileInputStream("count.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			settings.setProperty("count", String.valueOf(0));
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		settings.get("count");    用 Properties 来处理String类型的key value 信息.此处要求count是Object.
		int c = Integer.parseInt(settings.getProperty("count"))+1;
		System.out.println("这是第"+c+"次运行.");
//		settings.put(count, new Integer(c).toString());  用 Properties 来处理String类型的key value 信息.
		settings.setProperty("count", new Integer(c).toString());
		try {
			settings.store(new FileOutputStream("count.txt"), "leslie1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
class MyKey{
	private String name=null;
	private int age=0;
	MyKey(String name,int age){
		this.name=name;
		this.age=age;
	}
	public boolean equals(Object obj){
		if(obj instanceof MyKey){
			MyKey objtmp = (MyKey)obj;
			if((name.equals(objtmp.name) && age==objtmp.age)){
				return true;
			}else {
				return false;
			}
		}else{
			return false;
		}
	}
//	public int hashCode(){
//		return name.hashCode()+this.age;
//	}
	public String toString(){
		return name+","+age;
	}
}
