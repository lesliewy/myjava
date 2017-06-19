package api.io.example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import junit.framework.TestCase;

public class TestWrite2 extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	/*
	 * 200000: 1140
	 */
	public void test1() throws IOException{
		long start = System.currentTimeMillis();
		InputStream in = new FileInputStream("test1.dat");
		int b=0,i=0;
		byte[] ba = new byte[210000];
		while((b=in.read())!=-1){
			ba[i++]=(byte)b;
		}
		long end = System.currentTimeMillis();
		System.out.println("byte inputStream length:"+i);
		System.out.println("byte inputStream elapsed time:"+(end-start));
	}
	/*
	 * 200000: 1
	 */
	public void test2() throws IOException{
		long start = System.currentTimeMillis();
		InputStream in = new FileInputStream("test1.dat");
		int b=0,i=0;
		byte[] ba = new byte[210000];
//		while((b=in.read(ba))!=0){
//			ba[i++]=(byte)b;
//		}
		b=in.read(ba);
		long end = System.currentTimeMillis();
		System.out.println("byte[] inputStream length:"+b);
		System.out.println("byte[] inputStream elapsed time:"+(end-start));
	}
	

}
