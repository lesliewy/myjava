package examples.utils;

public class MyUtil3 {
	/*
	 * 两个十六进制字符串异或,先转为二进制字符串,再逐个异或,返回大写十六进制字符串.
	 * 如:EF13A8 ^ CBBBA6   :  24A80E
	 */
	public static String genxor(String value1,String value2) throws Exception{
		String binary1 = MyConvertUtils.hex2Binary(value1);
		String binary2 = MyConvertUtils.hex2Binary(value2);
		System.out.println("binary1:"+binary1);
		System.out.println("binary2:"+binary2);
		byte[] b1 = binary1.getBytes();
		byte[] b2 = binary2.getBytes();
		int[] b3 = new int[binary1.length()];
		String str="";
		for (int i=0;i<binary1.length();i++){
			b3[i] = b1[i] ^ b2[i];
			str += String.valueOf(b3[i]);
		}
		System.out.println("str:"+str);
		return MyConvertUtils.binary2hex(str).toUpperCase();
	}
	
	
	public static void main(String[] args) throws Exception {
		String a = genxor("06111111FFFFFFFF","0000763116237394");
		System.out.println("a:"+a);
	}
}
