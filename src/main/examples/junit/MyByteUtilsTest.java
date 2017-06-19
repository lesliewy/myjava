package examples.junit;

import examples.utils.MyConvertUtils;
import junit.framework.TestCase;

public class MyByteUtilsTest extends TestCase {
	public void testByte2Binary(){
		String a="123AaEF我";
		String b="",c="";
		b=MyConvertUtils.byteToBinary(a.getBytes());
		c=MyConvertUtils.byte2hex(a.getBytes());
		System.out.println("binary:"+b);
		System.out.println("hex:"+c);
	}
	public void testByte2Hex(){
		String a="123AaEF";
		String b="";
		b=MyConvertUtils.byte2hex2(a.getBytes());
		System.out.println("hex:"+b);
	}
	public void testbcd2Ascii(){
		String a="123";
		String b = new String(MyConvertUtils.bcd2Ascii(a.getBytes()));
		System.out.println("a:"+a);
		System.out.println("b:"+b);
	}
    public void testascii2Str()
    {
    	String a="123";
    	String b = new String(MyConvertUtils.bcd2Ascii(a.getBytes()));
    	String c = MyConvertUtils.ascii2Str(MyConvertUtils.bcd2Ascii(a.getBytes()));
    	System.out.println("a:"+a);
    	System.out.println("b:"+b);
    	System.out.println("c:"+c);
    }
}
