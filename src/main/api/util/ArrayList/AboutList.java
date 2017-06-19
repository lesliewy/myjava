package api.util.ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class AboutList {
    /*
     * 1,ArrayList ��addͬVector��,����û��addElement(Object o)method
     * 2,Iterator ����ͬEnumeration,���Ǵ���remove����.
     * 3,���Vector��ArrayList���Ƕ��߳�ͬ����û�м������̵߳Ŀ���.
     * 4, ArrayList�ײ�ʵ����Object[]:this.elementData = new Object[initialCapacity];
     * 5, ArrayList��remove()����,Ч�ʱȽϵ�.
     */
    public static void main(String[] args) {
        ArrayList<Comparable> a = new ArrayList();
        a.add(new Integer(1));
        a.add(0, "abc");
        a.add('c');
        a.add(2, "���ǵ����");
        System.out.println("ArrayList size:" + a.size());
        //		System.out.println("remove element:"+a.remove(1));

        System.out.println("1:" + (Integer) a.get(1)); // �������ǿ������ת��Ϊ�Ž�ȥ��ֵ.

        Iterator<Comparable> i = a.iterator();

        System.out.println("iterator 1:" + i.next());
        //		i.remove();
        System.out.println("iterator 2:" + i.next());
        //��ʱ����ӵ�3������ʼ
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
