package api.org.junit4;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 * 参数化的Runner: 用不同的参数执行同一个 @Test
 * 1,class上使用@RunWith
 * 2,定义一个@Parameters, 用来获取参数.
 * 3,对于每一次的getTestParameters,执行public constructor来赋值，然后执行@Test
 *
 */
@RunWith(value = Parameterized.class)
public class TestParameterized {

	private double expected;
	private double valueOne;
	private double valueTwo;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * 固定格式的 public static Collection
	 */
	@Parameters
	public static Collection<Integer[]> getTestParameters(){
		return Arrays.asList(new Integer[][]{
			{2,1,1},
			{3,2,1},
			{4,3,1}
		});
	}
	/*
	 * public constructor
	 */
	public TestParameterized(double expected,double valueOne,double valueTwo){
		this.expected = expected;
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}
	
	@Test
	public void sum(){
		Calculator calc = new Calculator();
		assertEquals(expected,calc.add(valueOne, valueTwo),0);
	}

	
}
