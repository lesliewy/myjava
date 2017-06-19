package api.org.junit4;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(value = org.junit.internal.runners.JUnit38ClassRunner.class)
public class TestJunit3 extends TestCase{

	@Test
	public void test1(){
		System.out.println("This is test1...");
	}

}
