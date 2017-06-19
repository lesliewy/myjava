/**
 * Project Name:MyJava  
 * File Name:MyComparator1.java  
 * Package Name:api.util.TreeMap  
 * Date:Aug 26, 20135:06:17 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.TreeMap;

import java.util.Comparator;

public class MyComparator1<T> implements Comparator<T> {

    @Override
    public int compare(T object1, T object2) {
        String a = (String)object1;
        String b = (String)object2;
        int result1 = b.compareTo(a);
        return result1;
    }

}
