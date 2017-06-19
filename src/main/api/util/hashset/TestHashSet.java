package api.util.HashSet;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

public class TestHashSet {
    @Test
    public void test1(String[] args) {
        HashSet hashset1 = new HashSet();
        hashset1.add("name1");
        hashset1.add("name2");
        hashset1.add(null); // 实际上就是 HashMap，所以允许null.
        int age = 24;
        hashset1.add(age);
        if (hashset1.contains("name2")) {
            System.out.println("hashset constains object name2");
        }
        if (hashset1.contains("name3")) {
            System.out.println("hashset constains object name3");
        }
        System.out.println("hashset1 size:" + hashset1.size());
        /*
         * 遍历  iterator
         */
        System.out.println("==============================================================================");
        Iterator iter = hashset1.iterator();
        while (iter.hasNext()) {
            System.out.println("elements:" + iter.next());
        }
    }

    @Test
    public void test2() {
        /*
         * 1,HashSet 中add 的值不可以重复,重复时 add 返回false，Set 内容保持不变.
         * 2,HashSet 底层用HashMap实现.当使用add方法时，实际上将该对象作为底层维护的Map对象的key,而value都是同一个对象(该对象没有用上).
         */
        HashSet hs = new HashSet();
        hs.add("a");
        hs.add("b");
        hs.add(new Integer(1));
        //              hs.add(new Integer(1));
        System.out.println(hs.add(new Integer(1))); // 返回false，Set 内容不变.

        System.out.println(hs);
        Iterator iter = hs.iterator();
        for (; iter.hasNext();) {
            System.out.println(iter.next());
        }
    }
}
