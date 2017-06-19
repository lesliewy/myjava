/**
 * Project Name:MyJava  
 * File Name:TestHashMap2.java  
 * Package Name:api.util.HashMap  
 * Date:Jan 22, 20137:44:17 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.HashMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.junit.Test;

/**.
 * ClassName: TestHashMap2 <br/>  
 * Function:
 * Reason:   
 * date: Jan 22, 2013 7:44:17 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestHashMap2 {
    private static final Logger log = Logger.getLogger(TestHashMap2.class.getName());

    /*
     * 1,null 可以强转成任何类型.
     * 2,key 和 value 都可以是null, 如果key是null,则新的会覆盖旧的.
     * 
     */
    @Test
    public void test1() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(null, "leslie1");
        map.put(null, "leslie2");
        map.put("name1", null);
        Integer b = (Integer) map.get("a");
        log.info("b:" + b);
        log.info((String) map.get("a"));
        log.info("null:" + map.get(null));
        log.info("name1:" + map.get("name1"));
    }

    /**
     * 遍历map时使用entrySet, 不用keySet, 这样可以避免多余的map.get()操作. 
     */
    @Test
    public void test2() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "1");
        map.put("b", "2");
        /*
         * 推荐方式
         */
        Set<Entry<String, String>> entryset = map.entrySet();
        Iterator<Entry<String, String>> iter = entryset.iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            log.info("key: " + entry.getKey() + " value:" + entry.getValue());
        }
        /*
         * 不推荐方式
         */
        Set<String> set = map.keySet();
        Iterator<String> keySetIter = set.iterator();
        while (keySetIter.hasNext()) {
            String key = keySetIter.next();
            log.info("key: " + key + " value:" + map.get(key));
        }
    }

    /**
     * toArray():
     * toArray(T[] a):  返回指定类型和长度的数组, 
     */
    @Test
    public void test3(){
        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("a", "1");
        m1.put("a", "8");
        m1.put("b", "2");
        m1.put("c", "3");
        System.out.println(m1);
//        Object[] arry1 =  m1.values().toArray();
//        for(Object a : arry1){
//            System.out.println(a);
//        }
        
        String[] arr2 = m1.values().toArray(new String[4]);
        System.out.println("arr2: " + Arrays.toString(arr2));
    }
    
    @Test
    public void test4(){
        int a = 3;
        System.out.println(a << 3);
        System.out.println(a);
        a <<= 3;   // 相当于 3 * 2^3
        
    }
    
    @Test
    public void test5(){
//        HashMap map = new HashMap();
        Map map = Collections.synchronizedMap(new HashMap());
        map.put("a","1");
        map.put("b", "2");
        Set entrySet = map.entrySet();
        Iterator iter = entrySet.iterator();
        while(iter.hasNext()){
            Entry entry = (Entry) iter.next();
            System.out.println(entry.getKey());
//            map.put("c", "3");  // 此时会抛出异常;
        }
        
    }
}
