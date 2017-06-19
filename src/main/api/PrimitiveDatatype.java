package api;

public class PrimitiveDatatype {
	public static void main(String[] args){
		int inta=new Integer("2");                 // 返回Integer对象.
		int intb=new Integer("2").intValue();      // 返回int值.
		int intc=Integer.valueOf(3);               // 返回Integer对象.
		int intd=Integer.parseInt("123");
		int inte=Integer.parseInt("123",6);        // 6进制  51
		System.out.println("inte:"+inte);
		String str=new String();
		StringBuffer sb=new StringBuffer();
		for (int i=0;i<inta;i++){
		}
	}
}
