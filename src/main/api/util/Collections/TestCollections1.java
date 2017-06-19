/**
 * Project Name:MyJava  
 * File Name:TestCollections1.java  
 * Package Name:api.util.Collections  
 * Date:Jan 17, 201310:32:12 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Collections;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ClassName: TestCollections1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 17, 2013 10:32:12 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestCollections1 {

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
     * addAll: 比 list.addAll(Collection)要快.
     */
    @Test
    public void test1(){
        List list1 = new ArrayList();
        System.out.println(Collections.addAll(list1, "a","b","c"));   // true for changed
        System.out.println(list1);
        
        List list2 = new ArrayList();
        list2.addAll(Arrays.asList("a","b","c"));
        System.out.println();
        
    }
    
    /*
     * asLifoQueue
     */
    @Test
    public void test2(){
        Deque deque = new ArrayDeque();
    }

}
