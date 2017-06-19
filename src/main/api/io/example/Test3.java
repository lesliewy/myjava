package api.io.example;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import junit.framework.TestCase;


public class Test3 extends TestCase{
	public void test1() throws FileNotFoundException{
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream("test1.dat"));
	}
	public void test2()throws Exception{
		long start = System.currentTimeMillis();
		Reader reader = new FileReader("test1.dat");
		String aa = "";
		int a = 0;
		while((a=reader.read())!=-1){
			aa+=a;
		}
		long end = System.currentTimeMillis();
		System.out.println("Reader length:"+aa.length());
		System.out.println("Reader elapsed time:"+(end-start));
	}
	public void test3() throws IOException{
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("test1.dat"));
		String aa ="";
		String line="";
		while((line=br.readLine()) != null)
		   aa+=line;
		long end = System.currentTimeMillis();
		System.out.println("BufferedReader length:"+aa.length());
		System.out.println("BufferedReader elapsed time:"+(end-start));
		System.out.println("aa:"+aa);
	}
}
