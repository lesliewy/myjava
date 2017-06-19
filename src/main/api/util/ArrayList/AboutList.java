package api.util.ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class AboutList {
    /*
     * 1,ArrayList 中add同Vector中,但是没有addElement(Object o)method
     * 2,Iterator 基本同Enumeration,但是存在remove方法.
     * 3,相比Vector，ArrayList不是多线程同步，没有监听多线程的开销.
     * 4, ArrayList底层实现是Object[]:this.elementData = new Object[initialCapacity];
     * 5, ArrayList的remove()方法,效率比较低.
     */
    public static void main(String[] args) {
        ArrayList<Comparable> a = new ArrayList();
        a.add(new Integer(1));
        a.add(0, "abc");
        a.add('c');
        a.add(2, "我们的祖国");
        System.out.println("ArrayList size:" + a.size());
        //		System.out.println("remove element:"+a.remove(1));

        System.out.println("1:" + (Integer) a.get(1)); // 必须进行强制类型转换为放进去的值.

        Iterator<Comparable> i = a.iterator();

        System.out.println("iterator 1:" + i.next());
        //		i.remove();
        System.out.println("iterator 2:" + i.next());
        //此时将会从第3个数开始
        while (i.hasNext()) {
            System.out.println("iterator :" + i.next());
        }

        a.add(null);
        System.out.println("indexOf(null): " + a.indexOf(null));

        System.out.println("display a: " + a);

        //
        System.out.println("get(): " + a.get(-3));
        /*
         * Set && List
         * 
         */
        //		Set s = null;
        //		s.add(e);

    }

}
