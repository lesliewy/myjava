/**
 * 
 */
package api.org.junit3;

import junit.framework.TestCase;

/**
 * Junit3 class 必须extends junit.framework.TestCase, 才可以使用.
 * 执行顺序是: setUp -> Your Test Method -> tearDown.
 */
public class TestJunit3 extends TestCase {

	/**
	 * @param name
	 */
	public TestJunit3(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("This is setUp...");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("This is tearDown...");
	}
	
	public void test1(){
		System.out.println("aaa");
	}

}
