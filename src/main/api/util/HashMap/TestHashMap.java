package api.util.HashMap;

import java.util.HashMap;
import java.util.Iterator;

public class TestHashMap {

    /**
     * 与HashTable 不同: 
     *    key, value 可以是null;
     *    unsynchronized;  非同步的;
     *    resize() 不同;
     *                     
     */
    public static void main(String[] args) {
        HashMap hm = new HashMap();
        hm.put("name1", "wy1");
        hm.put("name2", "wy2");
        hm.put("name3", null);
        hm.put(null, "key is null."); // 这个也是可以的.
        hm.put(null, "key is null 2");
        hm.put(null, "key is null 3");
        /*
         * 遍历 keySet()    entrySet()    toString()
         */
        Iterator iter_map = hm.keySet().iterator();
        while (iter_map.hasNext()) {
            Object key = iter_map.next();
            System.out.println("key is:" + key);
            System.out.println("value is :" + hm.get(key));
        }
        System.out.println("entrySet:");
        Iterator iter_entryset = hm.entrySet().iterator();
        while (iter_entryset.hasNext()) {
            System.out.println(iter_entryset.next());
        }
        System.out.println("toString():");
        System.out.println(hm.toString());
    }
}
