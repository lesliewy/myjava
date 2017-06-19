package api.util.HashTable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class TestHashtable {

    @Test
    public static void test1() {
        Hashtable<String, String> hs1 = new Hashtable<String, String>();
        hs1.put("name1", "wy1");
        hs1.put("name2", "wy2");
        hs1.put("name3", "wy3");
        hs1.put("name4", "wy4");
        hs1.put("name4", "wy44"); // 同名key，会覆盖之前的value.
        //		hs1.put(null, "key is null"); // 禁止 null.
        Hashtable<String, Integer> hs2 = new Hashtable<String, Integer>();
        hs2.put("age", 24);
        System.out.println("name1:" + hs1.get("name1"));
        System.out.println("name2:" + hs1.get("name2"));
        System.out.println("age+1:" + (hs2.get("age") + 1)); // 如果后面整数相加不加(),会转成字符串先执行第一个"+".
        hs1.remove("name2"); // 参数需是key,而不是value.
        System.out.println("hs1 size:" + hs1.size());
        Hashtable hs0 = new Hashtable(0); // 如果capacity初始为0,内部会自动转为1,而size()即count仍然为0.
        //		hs0.put("name1", "wy1");
        System.out.println("hs0 size:" + hs0.size() + "   contents:" + hs0.toString());
        System.out.println("=======================================================================");
        /*
         * 遍历:  keys()    keySet()   toString()
         * 
         */
        System.out.println("keys():");
        Enumeration enum_hs1 = hs1.keys();
        while (enum_hs1.hasMoreElements()) {
            String key_hs1 = (String) enum_hs1.nextElement();
            String value_hs1 = (String) hs1.get(key_hs1);
            System.out.println("hs1 key:" + key_hs1 + ",value:" + value_hs1);
        }
        System.out.println("keySet():");
        Iterator iter_hs1 = hs1.keySet().iterator();
        while (iter_hs1.hasNext()) {
            String key_hs1 = (String) iter_hs1.next();
            String value_hs1 = (String) hs1.get(key_hs1);
            System.out.println("hs1 key:" + key_hs1 + ",value:" + value_hs1);
        }
        System.out.println("toString():");
        System.out.println(hs1.toString());
        //下面的会导致死循环: 不能使用hs1.values().iterator().
        //	    Iterator iter_values = hs1.values().iterator();
        //	    while(iter_values.hasNext()){
        //	    	System.out.println("all values:"+iter_values);
        //	    }
        Enumeration enum_values = hs1.elements();
        while (enum_values.hasMoreElements()) {
            System.out.println("hs1.elements():" + enum_values.nextElement());
        }
        /*
         * putAll(Map)
         */
        Map map = new HashMap();
        map.put("name6", "wy6");
        map.put("name7", "wy7");
        hs1.putAll(map);
        System.out.println("after putAll():" + hs1.toString());
    }

    @Test
    public void test2() {

        /*
         * 1,Hashtable<K,V> 第一个是key，可以Enumeration keyEnum = ht.keys();获得每一个值.
         *                  第二个是value,Enumeration eleEnum = ht.elements();获得每一个值.
         * 2,ht.get(key): 此处相当于执行 key.equals(k),其中k是ht这个hashtable中的key，此时如果key中没有覆写equals()和hashCde()方法，调用Object.equals()和Object.hashCode()
         *                默认都是根据地址来计算的,所以ht.get(new MyKey("zhangsan",18)，即使Hashtable中存在也找不到.
         * 
         */
        Hashtable ht = new Hashtable();
        ht.put(new MyKey("zhangsan", 18), 1);
        ht.put(new MyKey("lisi", 19), 2);
        ht.put(new MyKey("wangwu", 20), 3);
        Enumeration keyEnum = ht.keys();
        Enumeration eleEnum = ht.elements();
        while (keyEnum.hasMoreElements()) {
            MyKey key = (MyKey) keyEnum.nextElement();
            System.out.println("key=" + key + "   value=" + ht.get(key)); // 此处key.toString()方法.

            //      System.out.println("element="+eleEnum.nextElement());
        }
        System.out.println("new key:" + ht.get(new MyKey("zhangsan", 18)));

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
            //              e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //      settings.get("count");    用 Properties 来处理String类型的key value 信息.此处要求count是Object.
        int c = Integer.parseInt(settings.getProperty("count")) + 1;
        System.out.println("这是第" + c + "次运行.");
        //      settings.put(count, new Integer(c).toString());  用 Properties 来处理String类型的key value 信息.
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
//      public int hashCode(){
//              return name.hashCode()+this.age;
//      }
        public String toString(){
                return name+","+age;
        }
}
}
