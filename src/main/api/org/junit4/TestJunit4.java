/**
 * 
 */
package api.org.junit4;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 1,class 无需extends junit.framework.TestCase, 在要测试的方法上添加 @Test 就可以运行Junit测试了.
 * 2,执行顺序是: setUpBeforeClass -> setUp -> Your Test Method1 -> tearDown -> setUp -> Your Test Method2 -> tearDown -> tearDownAfterClass.
 *   setUpBeforeClass,setUp,tearDown,tearDownAfterClass 这些名字是可以任意定的，只是前面的 Annotation 要正确.
 */
public class TestJunit4 {
	int add1=2;

	/**
	 * 该方法只能被执行1次，和@Before不同.
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("This is setUpBeforeClass...");
	}

	/**
	 * 该方法只能执行1次，和@After不同.
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("This is tearDownAfterClass...");
	}

	/**
	 * 可以做一些复原操作，因为某些值肯能已经被修改了.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp2() throws Exception {
		System.out.println("This is setUp...");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown1() throws Exception {
		System.out.println("This is tearDown...");
	}
	
	@Test
	public void test1(){
		add1=8;
		assertEquals(8,add1);
		System.out.println("This is Junit4.");
	}
	
	/*
	 * 将会IGNORE test2
	 */
	@Ignore("test2 not finish.")
	@Test
	public void test2(){
		System.out.println("This is test2...");
	}
	
	@Test
	public void test3(){
		assertEquals(2,add1);
		System.out.println("This is test3...");
	}
	
	/*
	 * Junit 会报告该程序有没有timeout (millisecond)
	 */
	@Test(timeout=1000)
	public void test4(){
//		for(;;){
//			
//		}
		System.out.println("This is test4...");
	}
	
	@Test(expected = ArithmeticException.class)
	public void test5(){
		int a = 7/0;
		System.out.println("This is test5...");
	}

}
