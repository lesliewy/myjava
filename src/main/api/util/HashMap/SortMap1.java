package api.util.HashMap;

import org.junit.Test;

import java.util.*;

import static java.util.Collections.*;

/**
 * Created by leslie on 2017/8/14.
 */
public class SortMap1 {

    @Test
    public void test1() {
        Map<String, String> map = new HashMap();
        map.put("c", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("f", "fffff");
        map.put("d", "ddddd");
        map.put("e", "eeeee");
        map.put("g", "ggggg");
        List<Map.Entry<String, String>> list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator1());
        printMap(map);

        System.out.println("============");

        Map<Integer, String> map2 = new HashMap<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            map2.put(random.nextInt(100), "aaa");
        }

        List<Map.Entry<Integer, String>> list2 = new ArrayList(map2.entrySet());
        Collections.sort(list2, new Comparator2());
        printMap(map2);

        System.out.println("============");
        // list 中的才是有序的.
        for(Map.Entry<Integer, String> entry : list2){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private <K, V> void printMap(Map<K, V> map){
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    class Comparator1 implements Comparator<Map.Entry<String, String>> {

        @Override
        public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    }

    class Comparator2 implements Comparator<Map.Entry<Integer, String>> {

        @Override
        public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
            return o1.getKey() - o2.getKey();
        }
    }
}
