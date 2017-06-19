/**
 * Project Name:MyJava  
 * File Name:TestArray1.java  
 * Package Name:api.util.Arrays  
 * Date:Jan 16, 201310:18:05 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Arrays;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * ClassName: TestArray1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 16, 2013 10:18:05 AM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestArray1 {

    /**
     * setUpBeforeClass:(这里用一句话描述这个方法的作用). <br/>  
     * TODO(这里描述这个方法适用条件 – 可选).<br/>  
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
     *  
     * @author Leslie  
     * @throws java.lang.Exception  
     * @since 1.0.0
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * tearDownAfterClass:(这里用一句话描述这个方法的作用). <br/>  
     * TODO(这里描述这个方法适用条件 – 可选).<br/>  
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
     *  
     * @author Leslie  
     * @throws java.lang.Exception  
     * @since 1.0.0
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * setUp:(这里用一句话描述这个方法的作用). <br/>  
     * TODO(这里描述这个方法适用条件 – 可选).<br/>  
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
     *  
     * @author Leslie  
     * @throws java.lang.Exception  
     * @since 1.0.0
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * tearDown:(这里用一句话描述这个方法的作用). <br/>  
     * TODO(这里描述这个方法适用条件 – 可选).<br/>  
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
     *  
     * @author Leslie  
     * @throws java.lang.Exception  
     * @since 1.0.0
     */
    @After
    public void tearDown() throws Exception {
    }
    
    /*
     * 初始化
     */
    @Test
    public void test8(){
        int[] arr1 = {1,2,3};  //  直接赋值. {}只能用在直接赋值中.
        int[] arr2 = new int[3]; // 预分配空间
        arr2[0]=10;
        arr2[1]=11;
        arr2[2]=12;
        int[] arr3 ;   // 动态分配.
        arr3 = new int[]{20,21,22};
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }
    
    /*
     * asList:  相当于调用new ArrayList(array):   此class是private的, 其中有属性private final E[] a;  所以不能使用 add remove等方法.
     * 
     */
    @Test
    public void test1(){
        List<String> liststr = Arrays.asList("leslie1","leslie2","leslie3");
        List<Integer> listInteger = Arrays.asList(1,2,3);
//        liststr.add(e);    // 不可以再更改该list.
        
        String[] a= {"1","2"};
        List<String> liststr2 = Arrays.asList(a);
        System.out.println("liststr:"+liststr);
        System.out.println("listInteger:"+listInteger);
        System.out.println("liststr2:"+liststr2);
        
        System.out.println("size:"+liststr.size());
        System.out.println("index 2:"+liststr.get(2));
        liststr.set(2, "leslie4");
        System.out.println("liststr after set:"+liststr);
        System.out.println("indexOf:"+liststr.indexOf("leslie4"));
        System.out.println("contains:"+liststr.contains("leslie4"));
    }

    /*
     * binarySearch
     */
    @Test
    public void test2(){
        String[] arr1 = {"a","b","c","d","e","f"};
        System.out.println(Arrays.binarySearch(arr1, "d"));
        System.out.println(Arrays.binarySearch(arr1, 2,arr1.length,"d"));
    }
    
    /*
     * copyOf: 从第0个开始
     * copyOfRange: 
     */
    @Test
    public void test3(){
        int[] arr1 = {1,2,3,4,5,6,7,8,};
        int[] arr2 = Arrays.copyOf(arr1,10);
        for(int a : arr2){
            System.out.println("arr2:"+a);
        }
        int[] arr3 = Arrays.copyOfRange(arr1, 2,10);
        for(int a : arr3){
            System.out.println("arr3:"+a);
        }
    }
    /*
     * equals:  同一个位置的值相同.
     * deepEquals: 参数必须是Object
     * 
     */
    @Test
    public void test4(){
//        double[] arr1 = {1.1, 2.1, 3.1, 4.1, 5.2};
//        double[] arr2 = {1.1, 2.1, 3.1, 4.1, 5.1};
//        System.out.println(Arrays.equals(arr1, arr2));

//        String a="a";
//        String b="a";
//        String[] arr1 = {"a","b","c","d"};
//        String[] arr2 = {"a","b","c","d"};
//        String[] arr3 = {a,"b","c","d"};
//        System.out.println(Arrays.deepEquals(arr1, arr3));
        
//        Integer a = new Integer(1);
//        Integer b = new Integer(1);
//        Integer[] arr1 = {a,2};
//        Integer[] arr2 = {b,2};
//        System.out.println(Arrays.equals(arr1, arr2));
//        System.out.println(Arrays.deepEquals(arr1, arr2));
        
        TestClass1 a = new TestClass1();
        TestClass1 b = new TestClass1();
        TestClass1[] arr1 = {a};
        TestClass1[] arr2 = {b};
        System.out.println(Arrays.equals(arr1, arr2));
        System.out.println(Arrays.deepEquals(arr1, arr2));
        System.out.println(a.equals(b));
    }
    
    /*
     * 如果是 array,则不可以直接System.out.println(array)
     * deepToString: 参数必须是Object;   String.valueOf(Object) Arrays.toString(e)
     * Arrays.toString: 该Array的String形式.
     */
    @Test
    public void test5(){
//        Integer[] arr1 = {1,2,3,4};
//        System.out.println(arr1);
//        System.out.println(Arrays.deepToString(arr1));
        
//        String[] arr1 = {"a","b","c"};
//        System.out.println(arr1);
//        System.out.println(arr1.toString());
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.deepToString(arr1));
        
        int[] arr1 = {1,2,3};
        System.out.println(arr1);
        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.deepToString(arr1));
    }
    
    /*
     * fill: 可以指定range,  不能越界.
     */
    @Test
    public void test6(){
//        int[] arr1 = {1,2,3,4};
//        Arrays.fill(arr1, 3);
//        System.out.println(Arrays.toString(arr1));
        
//        String[] arr1 = {"a","b","c"};
//        Arrays.fill(arr1, "aaa");
//        System.out.println(Arrays.toString(arr1));
        
        float[] arr1 = {1.1f,2.1f,3.1f,4.1f};
        Arrays.fill(arr1, 2,arr1.length,3.3f);
        System.out.println(Arrays.toString(arr1));
    }
    
    /*
     * sort: The sorting algorithm is a tuned quicksort, adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function"
     * 默认是自然排序(ASCII)，不忽略大小写.
     */
    @Test
    public void test7(){
//        int[] arr1 = {2,8,9,1};
//        Arrays.sort(arr1);
//        System.out.println(Arrays.toString(arr1));
        
        String[] arr1 = {"ab1","aB2","cEf","8A"};
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));
        
        Arrays.sort(arr1,Collections.reverseOrder());
        System.out.println(Arrays.toString(arr1));
        
        Arrays.sort(arr1,String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(arr1));
    }
    
    /*
     * 数组与泛型: 不能创建泛型数组，原因见Effective Java.
     */
    @Test
    public void test9(){
        TestClass1[] arr1 = new TestClass1[]{new TestClass1()};
        Integer[] arr2 = new Integer[]{1,2,3};
        Integer[] arr3 = new TestClass2<Integer>().f(arr2);          // 可以使用泛型的方法返回, 
        String[] arr4 = {"a","b","c"};
        String[] arr5 = new TestClass2<String>().f(arr4);
//        TestClass2<Integer>[] arr4 = new TestClass2<Integer>[10];  // 无法创建泛型式的array.
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr5));
    }
}
