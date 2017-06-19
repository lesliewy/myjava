package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AboutMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 1, HashMap 没有顺序：放入的顺序与取出的顺序没有关系.
		 * 2, 一个key，只能对应一个value，相同的key会以最新的value为准，覆盖之前的value.
		 * 3, HashMap 底层维护一个数组,向HashMap中所放置的对象实际上就是存储在该数组中.
		 * 4, 当向 HashMap 中 put 一对键值时，它会根据key的hashCode的值计算出一个位置,该位置就是准备向数组中存放的位置.
		 *    如果该位置没有对象存在，就将此对象直接放进数组中.
		 *    如果该位置有对象存在，则顺着此存在的对象的链开始寻找(Entry 类有一个Entry类型的next成员变量,指向了下一个对象),如果此链上有对象的话，再去使用equals方法进行比较，如果equals比较的结果为false，则将该对象放到数组中，然后将该位置以前存在的对象链到该对象的后面.
		 *    
		 *    数组    每一个元素是链表
		 * 
		 */
		HashMap hm = new HashMap();
		hm.put("a","张三");
		hm.put("b","李四");
		hm.put("b1", "李四");
		hm.put("c","王五");
		hm.put("a", "赵六");
		System.out.println(hm);
		System.out.println("a="+hm.get("a"));
		
		Set sm = hm.keySet();
		System.out.println("key set:"+sm);
		
		Collection cm = hm.values();
		System.out.println("values:"+cm);
		
		// 遍历HashMap
		Set set = hm.keySet();
		for(Iterator iter = set.iterator();iter.hasNext();){
			String key = (String)iter.next();
			String values = (String)hm.get(key);
			System.out.println(key+"="+values);
		}
	}
}
