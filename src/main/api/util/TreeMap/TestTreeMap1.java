/**
 * Project Name:MyJava  
 * File Name:TestTreeMap1.java  
 * Package Name:api.util.TreeMap  
 * Date:Aug 26, 20134:57:15 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.TreeMap;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.junit.Test;

public class TestTreeMap1 {

    @Test
    public void test1(){
//        Map m = new TreeMap();
        TreeMap m = new TreeMap(new MyComparator1());
        m.put("a", 3);
        m.put("h", 1);
        m.put("b", 8);
        m.put("ba", 5);
        m.put("g", 88);
        m.put("gg", 99);
        System.out.println(m);
        System.out.println("ceilingEntry: " + m.ceilingEntry("a2"));  // key >= "a2", 返回Entry  与TreeMap排序有关,即后一个;
        System.out.println("ceilingKey: " + m.ceilingKey("a2"));      // 返回 Key
        
        System.out.println("floorEntry(K key): " + m.floorEntry("a2")); // 返回Entry, key <= "a2", 与TreeMap排序有关, 即前一个;
        System.out.println("floorKey(K key): " + m.floorKey("a2"));
        
        System.out.println("descendingKeySet(): " + m.descendingKeySet());  // 返回Keys, 与 TreeMap 中的顺序相反;
        System.out.println("descendingMap(): " + m.descendingMap()); // 返回Entrys, 与 TreeMap 中的顺序相反;
        
     
        SortedMap sortedHead = m.headMap("g");             // TreeMap 中 key 是 "g" 之前的;
        sortedHead = m.headMap("g", true);
        System.out.println("sortedHead map: " + sortedHead);
        SortedMap sortedTail = m.tailMap("g");
        sortedTail = m.tailMap("g", true);
        System.out.println("sortedTail: " + sortedTail);
        
        Entry higher = m.higherEntry("g");                          // 与ceiling不同处在于没有 = , 只有 >
        Entry ce = m.ceilingEntry("g");
        System.out.println("higher: " + higher + "; ce: " + ce);
        
        Entry lower = m.lowerEntry("g");                            // 同样, 没有 = 
        System.out.println("lower: " + lower);
        
        Entry first = m.pollFirstEntry();
        Entry last = m.pollLastEntry();
        System.out.println("first: " + first + "; last: " + last);
        
        NavigableMap sub1 = m.subMap("h", false, "g", true); // 与TreeMap排序有关;
        SortedMap sort2 = m.subMap("i", "g");   // from: inclusive;  to: exclusive;  ??? 好像不是
        System.out.println("subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive): " + sub1);
        System.out.println("subMap(K fromKey, K toKey): " + sort2);  
    }
}
