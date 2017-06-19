/**
 * Project Name:MyJava  
 * File Name:TestArrayList.java  
 * Package Name:api.util.ArrayList  
 * Date:Aug 27, 20135:18:11 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestArrayList {

    /**
     * clone() shallow copy:  基本类型copy值，其他都是引用;   
     *    内部调用的是 super.clone(), 该方法是shallow copy
     * 
     */
    @Test
    public void test1(){
       ArrayList list1 = new ArrayList();
       list1.add(1);
       list1.add(8);
       list1.add(5);
       list1.add(9);
       list1.add(null);
       list1.add(null);
       ArrayList list2 = (ArrayList) list1.clone();
       list2.set(2, 7);
       System.out.println("list1: " + list1);
       System.out.println("list2: " + list2);
       
       ArrayList list3 = new ArrayList();
       list3.add(Integer.valueOf(1));
       list3.add(Integer.valueOf(8));
       list3.add(Integer.valueOf(5));
       list3.add(Integer.valueOf(9));
       ArrayList list4 = (ArrayList) list3.clone();
       list3.set(2, Integer.valueOf(7));
       System.out.println("list3: " + list3);
       System.out.println("list4: " + list4);
       
       ArrayList list5 = new ArrayList();
       list5.add("a");
       list5.add("b");
       list5.add("g");
       ArrayList list6 = (ArrayList)list5.clone();
       list5.set(2, "f");
       System.out.println("list5: " + list5);
       System.out.println("list6: " + list6);
       
       // 修改list8中的Inner, 同样会影响list7中的;
       ArrayList list7 = new ArrayList();
       list7.add(new Inner("h"));
       list7.add(new Inner("k"));
       list7.add(new Inner("o"));
       list7.add(new Inner("s"));
       ArrayList list8 = (ArrayList) list7.clone();
       ((Inner)list8.get(2)).setStr("zzz");
       
       System.out.println("list7: " + list7);
       System.out.println("list8: " + list8);
       
       Inner[] i1 = new Inner[4];
       for(int i = 0; i < i1.length; i ++){
           i1[i] = new Inner("");
       }
       i1[0].setStr("h");
       i1[1].setStr("k");
       i1[2].setStr("o");
       i1[3].setStr("s");
       Inner[] i2 = Arrays.copyOf(i1, i1.length);
       i2[2].setStr("fff");
       System.out.println("i1: " + i1[2]);
       System.out.println("i2: " + i2[2]);
       
       try {
        super.clone();
    } catch (CloneNotSupportedException e) {
          
        // TODO Auto-generated catch block  
        e.printStackTrace();  
              
    }
    }
    
    
    private class Inner{
        String str;
        public Inner(String str){
            this.str = str;
        }
        
        public void setStr(String str){
            this.str = str;
        }
        
        public String toString(){
            return "str: " + str;
        }
    }
}
