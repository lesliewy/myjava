package api.io.example;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestWrite1 extends TestCase{

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
	 * 一个一个字节的写入文件: 
	 * 100000: 1301
	 * 200000: 2205
	 * 显然IO的开销太大了,一个一个字节的写入非常慢.
	 */
	public void test1() throws IOException{
		long numOfBytes = 200000;
		OutputStream out = new FileOutputStream("test1.dat");
		long start = System.currentTimeMillis();
		for(int i=0; i<numOfBytes; i++){
			out.write(99);
		}
		out.write('\r');
		out.write('\n');
		long end = System.currentTimeMillis();
		out.close();
		System.out.println("byte elapsed time:"+(end - start));
	}
	/*
	 * byte[] 写入文件: 
	 * 100000: 3
	 * 200000: 3
	 * 只需要一次IO.
	 */
	public void test2()throws IOException{
		long numOfBytes = 200000;
		byte[] b = new byte[(int) numOfBytes+2];
		OutputStream out = new FileOutputStream("test2.dat");
		long start = System.currentTimeMillis();
		int i=0;
		for( i=0; i<numOfBytes; i++){
			b[i]=(byte)99;
		}
		b[i]='\r';
		b[i+1]='\n';
		out.write(b);
		long end = System.currentTimeMillis();
		out.close();
		System.out.println("byte[] elapsed time:"+(end - start));
	}

}
