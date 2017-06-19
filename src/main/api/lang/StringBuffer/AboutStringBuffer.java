package api.lang.StringBuffer;

public class AboutStringBuffer {
	public static void main(String[] args){
		/*
		 * 支持多线程.
		 */
		System.out.println("api constructor ===========================");
		/*
		 * 默认capacity是16, 可以自定义.
		 * StringBuffer(CharSequence): 可以初始化一个String.
		 *   
		 * length:     取length.
		 * setLength : capacity与length值相同.
		 * trimToSize: 将capacity设置成length相同.
		 * 
		 * ensureCapacity: 确保capacity至少是多少.
		 */
		StringBuffer sb1_apicon = new StringBuffer();
		StringBuffer sb2_apicon = new StringBuffer(223);
		System.out.println("sb1_apicon StringBuffer:"+sb1_apicon.capacity());
		System.out.println("sb2_apicon StringBuffer capacity:"+sb2_apicon.capacity());
		sb2_apicon.trimToSize();           // 将capacity设置成length相同.
		System.out.println("sb2_apicon trimToSize length:"+sb2_apicon.length()+"   capacity:"+sb2_apicon.capacity());
		
		StringBuffer sb3_apicon = new StringBuffer("abc");
		System.out.println("sb3_apicon StringBuffer String:"+sb3_apicon.capacity());
		System.out.println("sb3_apicon StringBuffer String length:"+sb3_apicon.length());   // length() 与 capacity()不同. length是实际char的长度.
		sb3_apicon.setLength(80);
		System.out.println("sb3_apicon setLength:"+sb3_apicon.length()+"  capacity:"+sb3_apicon.capacity());  // 修改StringBuffer的长度,setLength后,capacity值与新的capacity相同.
		System.out.println("sb3_apicon setLength char:"+sb3_apicon.codePointAt(79));  // setLength 后新增的值是null.
		sb3_apicon.trimToSize();                                                      // setLength后,trimToSize就是length的大小.
		System.out.println("sb3_apicon trimToSize length:"+sb3_apicon.length()+"   capacity:"+sb3_apicon.capacity());
		
		StringBuilder sb3a_api = new StringBuilder("1234");
		System.out.println("sb3a_api capacity:"+sb3a_api.capacity());         // 20
		sb3a_api.ensureCapacity(80);
		System.out.println("sb3a_api ensureCapacity:"+sb3a_api.capacity());   // 80
		sb3a_api.ensureCapacity(60);
		System.out.println("sb3a_api ensureCapacity:"+sb3a_api.capacity());   // 80
		
		System.out.println("api ================================");
		/* 追加、插入、删除、查找、修改值、替换、调转(取反)
		 * append: all data types
		 * insert: all data types
		 * delete: 可以指定首尾位置;  deleteCharAt: 单个字符.
		 * 
		 * indexOf: String的位置.  可以从指定位置开始查,但是index还是从最开始的算起,即排除前面的.
		 * lastIndexOf: 最后出现的String的位置. 可以指定结尾的位置,即排除后面的.
		 * 
		 * setCharAt:  直接修改值,与insert不同.
		 * 
		 * replace(from,to,str):   指定的subString替换.  没有regex.
		 * 
		 * reverse()
		 */
		StringBuffer sb1_api = new StringBuffer("啊全额iiiss123*   dsa");
		boolean b1_api = true;
		char c1_api = 'a';
		char[] c1a_api ={'a','从','1'};
		double d1_api = 1239838772.322;
		float f1_api = 2323887.322f;
		StringBuffer sb1a_api = new StringBuffer("ddddddd");
		System.out.println("sb1_api append all types:"+sb1_api.append(b1_api).append(c1_api).append(c1a_api).append(d1_api).append(f1_api).append(sb1a_api));
	
		StringBuffer sb2_api = new StringBuffer("abcdefg");
		boolean b2_api = false;
		char c2_api = 'c';
		char[] c2a_api = {'c','h','a','r'};
		double d2_api = 223333322222222.111;
		float f2_api = 333332222221.133f;
		String s2_api = "string";
		System.out.println("sb2_api insert all types:"+sb2_api.insert(3, b2_api).insert(4, c2_api));  // 直接修改前面的.
		System.out.println("sb2_api insert all types:"+sb2_api.insert(5, c2a_api).insert(8, d2_api).insert(10, f2_api).insert(1, s2_api));
		
		StringBuffer sb3_api = new StringBuffer("123456789");
		System.out.println("sb3_api delete :"+sb3_api.delete(3, 6));
		System.out.println("sb3_api deleteCharAt:"+sb3_api.deleteCharAt(3));
		
		StringBuffer sb4_api = new StringBuffer("12345672892000");
		System.out.println("sb4_api indexOf:"+sb4_api.indexOf("892"));
		System.out.println("sb4_api indexOf fromIndex:"+sb4_api.indexOf("2", 5));
		System.out.println("sb4_api lastIndexOf:"+sb4_api.lastIndexOf("892"));
		System.out.println("sb4_api lastIndexOf fromIndex:"+sb4_api.lastIndexOf("2", 4));
		
		StringBuffer sb4a_api = new StringBuffer("123456789");
		sb4a_api.setCharAt(3, '3');
		System.out.println("sb4a_api setCharAt:"+sb4a_api);
		
		StringBuffer sb4b_api = new StringBuffer("1234567890000");
		StringBuffer sb4c_api = sb4_api.replace(3, 8, "aa");
		System.out.println("sb4c_api replace:"+sb4c_api);
		
		StringBuffer sb4d_api = new StringBuffer("12345678");
		System.out.println("sb4d_api reverse:"+sb4d_api.reverse());
		
		/*单个char,subSequence subString.
		 * charAt,codePointAt,codePointBefore,codePointCount
		 * subSequence subString
		 */
		StringBuffer sb5_api = new StringBuffer("123456789000");
		char c5_api = sb5_api.charAt(5);
		int  i5_api = sb5_api.codePointAt(5);
		int  i5a_api = sb5_api.codePointBefore(5);
		int  i5b_api = sb5_api.codePointCount(3, 7);
		System.out.println("sb5_api charAt:"+c5_api);
		System.out.println("sb5_api codePointAt:"+i5_api + "  codePointBefore:"+i5a_api);  // char 转换成 int
		System.out.println("sb5_api codePointCount:"+i5b_api);
		
		StringBuffer sb6_api = new StringBuffer("abcdefghigk");
		String s6_api = sb6_api.substring(3);
		String s6a_api = sb6_api.substring(3, 6);
		System.out.println("s6_api subString from:"+s6_api);
		System.out.println("s6a_api subString from to:"+s6a_api);
		
		/* 转换
		 * toString
		 * getChars:  从StringBuffer中取chars到指定char[]的指定位置
		 */
		StringBuffer sb7_api = new StringBuffer("abcdefg");
		String s7_api = sb7_api.toString();
		System.out.println("s7_api toString:"+s7_api);
		
		StringBuffer sb8_api = new StringBuffer("abcdefg");
		char[] c8_api = new char[10];
		sb8_api.getChars(3, 5, c8_api, 0);
		System.out.println("sb8_api getChars:"+c8_api[0]);
		
		
	}
}
