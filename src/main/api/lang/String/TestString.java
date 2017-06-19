package api.lang.String;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

public class TestString {
    /*
     * 1, byte b=66;  : System.out.println(b)   输出 66  占用1个字节.
          char c=66;  : System.out.println(c)   输出 B   占用2个字节,包括汉字，一个汉字是一个char, char数组的下标移动的是一个编码单位,
     */
    @Test
    public void test1() {
        byte[] buf_byte = { '1', '2', '3', '4', '5' };
        char[] buf_char = { 'a', '我', 'c', 'd', '们' };
        String strInfo_byte = new String(buf_byte, 1, 4); // 从0开始.
        String strInfo_char = new String(buf_char, 1, 4);
        System.out.println("strInfo_byte:" + strInfo_byte + "====length(strInfo_byte):" + strInfo_byte.length()); //strInfo_byte:2345====length(strInfo_byte):4
        System.out.println("strInfo_char:" + strInfo_char + "====length(strInfo_char):" + strInfo_char.length()); //strInfo_char:我cd们====length(strInfo_char):4
        System.out.println("indexof:" + strInfo_byte.indexOf('3'));
        System.out.println("indexof:" + strInfo_byte.indexOf("3"));
        System.out.println("substring:" + strInfo_byte.substring(1));
        System.out.println("substring:" + strInfo_byte.substring(2, 3)); // beginindex:inclusive   endindex:exclusive
    }

    /*
     * 对象必须先初始化(new String() or null)，否则不能使用.
     * 如果用null进行初始化，则没有为对象分配内存空间(没有hashCode). 但是可以进行str=str+"*";这样的操作，因为会自动转为StringBuffer.
     * 如果用new String()初始化，则为空字符串(hashCode为0)
     * 
     * String 是不可修改的, StringBuffer支持对内容的修改.
     * str=str+"*";   str+"*"  此时会产生一个新的String 对象.
     * 如果要对String 对象进行大量的操作，建议使用StringBuffer,该类可以修改内容,而不是生成新的对象.
     * 
     * String a = new String("abc")  这样是不必要的，会创建2个对象，一个是"abc"在常量空间中; 一个是new 在堆中;
     */
    @Test
    public void test2() {
        String str = new String();
        System.out.println("String begin hascode :" + str.hashCode()); // hashCode()值相同说明地址相同.
        //      StringBuffer sb = null;
        StringBuffer sb = new StringBuffer();
        System.out.println("StringBuffer hascode :" + sb.hashCode());

        for (int i = 0; i < 10; i++) {
            str = str + "*";
            System.out.println("String hascode:" + str.hashCode());
        }
        for (int i = 0; i < 10; i++) {
            sb = sb.append("*");
            System.out.println("StringBuffer hascode:" + sb.hashCode());
        }
        System.out.println("========================================");
        String str1 = "ab";
        str1 = str1 + "c";
        String str2 = "abc";
        System.out.println("str1 hashCode:" + str1.hashCode()); //96354  重写了hashCode，只要内容一样，哈希码就一样.
        System.out.println("str2 hashCode:" + str2.hashCode()); //96354
        System.out.println("str1.equals(str2):" + str1.equals(str2)); //true   比较的是内容
        System.out.println("str1==str2:" + str1 == str2); //false  比较的是地址.
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        sb1.append("abc");
        sb2.append("abc");
        System.out.println("sb1 hashCode:" + sb1.hashCode()); //14452073 StringBuffer.hashCode 不是根据内容来的,一般是内存地址. 和 object.equals 方法一样.
        System.out.println("sb2 hashCode:" + sb2.hashCode()); //29013258
        String a1 = "abc", b1 = "abc"; // String.hashCode 就是按照内容来计算的; 默认为0，空串的hashCode就是0;
        System.out.println("a1.hashCode: " + a1.hashCode() + "; b1.hashCode: " + b1.hashCode() + "; 空串的hashCode: "
                + "".hashCode());
        System.out.println("sb1.equals(sb2):" + sb1.equals(sb2)); //false，比较的不是内容.  String buffer 没有覆盖Object的equals(),所以 return (this == obj);
        System.out.println("sb1.toString.equals(sb2.toString):" + sb1.toString().equals(sb2.toString())); // true
        //System.out.println("sb1==sb2:"+sb1==sb2);

        System.out.println("=============================");
        byte[] by1 = { 'a', 'b', 'c', 'd' };
        char[] ch1 = { 'a', 'b', '我' };
        String str3 = new String(by1);
        System.out.println("str3 length:" + str3.length());
    }
    
    /**
     * 1,java中存在String pool(栈中): 先在String pool 中查找是否存在 abcd123 对像，如果不存在，则创建该对象，并返回地址,s4指向该对象;如果存在，则返回该对象.
     * 2, new 都会在堆中创建新对象: 查找String pool 中是否存在 abcd，如果不存在，则先在String Pool 中创建abcd对象，再在堆上创建新对象,并返回堆上的对象,s6指向该对象.
     *                             如果存在，则不再String pool 创建，直接在堆上创建新对象，并返回该堆上的对象，s6指向该对象.
     */
    @Test
    public void test3(){
        System.out.println("5======================");
        String s4 = "abcd123";
        String s5 = "abcd123";
        String s6 = new String("abcd");
        String s7 = "abcd";
        System.out.println("s4==s5:" + (s4 == s5)); // true
        System.out.println("s4==s6:" + (s4 == s6)); // false
        System.out.println("s6==s7:" + (s6 == s7)); // false
        System.out.println("s6.intern()==s7:" + (s6.intern() == s7)); // intern返回String pool中的对象,由于此时abcd已经存在于String pool中(s6 new时创建的),所以 true.
    }
    
    @Test
    public void test4(){
        /*
         * 1, 输出时会调用每个对象的toString(),如果不存在，则调用Object的toString(),输出类型：api.test1@1bc4459
         * 2，ArrayList类型会调用每一个元素的toString().
         */
        ArrayList al = new ArrayList();
        al.add("leslie");
        al.add(1, "wangyang");
        al.add(new Integer(3));
        al.add(new test1(24, "leslie"));
        System.out.println(al);
        System.out.println("api constructor:===========================");
    }
    /*
     * 2个.
     * a,String s1_api = new String(); 相当于 String s1_api = ""; 可以使用该对象,例如:s1_api.lastIndexOf(1)
     *   String s1_api = null;    不可以使用该对象,必须初始化.
     *   String s1_api;           与 String s1_api = null; 一样.
     * b,这两个new String 没必要, 因为String本来就是不可修改的.
     */    
    @Test
    public void test5(){
        String s1_apicon = new String();
        String s2_apicon = new String("abc");
    }
    
    /*
     * 4个
     * 将byte[]转为String, 可以带offset,charsetname
     * byte只占1个字节,ascii码在255以下,所以不包括汉字.
     */
    @Test
    public void test6() throws UnsupportedEncodingException{
        byte[] b3_apicon = { '1', '2', '&', 'b', '*', '(' };
        String s3_apicon = new String(b3_apicon);
        String s4_apicon = new String(b3_apicon, 3, 2); // offset 从0开始.
        System.out.println("s3_apicon:" + s3_apicon + "  length:" + s3_apicon.length());
        System.out.println("s4_apicon:" + s4_apicon + "  length:" + s4_apicon.length());
        String s5_apicon = new String(b3_apicon, 3, 2, "GBK"); // with charsetName
        String s6_apicon = new String(b3_apicon, "GBK");
        System.out.println("s5_apicon:" + s5_apicon);
        System.out.println("s6_apicon:" + s6_apicon);
        System.out.println("default charset:" + Charset.defaultCharset()); // UTF-8
    }
    
    /*
     * 2个.
     * 将char[]转为String,offset,  char 是不需要charset的.
     * char 占2个字节.
     */
    @Test
    public void test7(){
        char[] c7_apicon = { 'a', 'b', 'c', '1', '★', '我', '6', '*' };
        String s7_apicon = new String(c7_apicon);
        String s8_apicon = new String(c7_apicon, 3, 3); // 我  算一个char.
        System.out.println("s7_apicon:" + s7_apicon);
        System.out.println("s8_apicon:" + s8_apicon);
    }
    
    /*1个.
     * String(int[] codePoints, int offset, int count)  还不知道怎么用.
     */
    
    /*1个.
     *String(anotherString)相当于copy.
     */
    @Test
    public void test8(){
        String s9_apicon = "abc不";
        String s10_apicon = new String(s9_apicon);
        System.out.println("s9_apicon:" + s9_apicon);
        System.out.println("s10_apicon:" + s10_apicon);
    }
    
    /*2个
     * 从StringBuffer StringBuilder 与 String的相互转换.
     */
    @Test
    public void test9(){
        StringBuffer sb11_apicon = new StringBuffer("abc");
        String s11_apicon = new String(sb11_apicon);
        StringBuilder sb12_apicon = new StringBuilder("def");
        String s12_apicon = new String(sb12_apicon);
        System.out.println("s11_apicon:" + s11_apicon);
        System.out.println("s12_apicon:" + s12_apicon);

        System.out.println("api=============================");
    }

    /*
     * 取单个char, subString  subsequence
     * charAt codePointAt,   有了codePointAt 为什么还要codePointBefore?
     * subSequence subString
     * 
     * concat:  append在最后.
     */
    @Test
    public void test10(){
        String s1_api = "abc不★*&";
        char c1_api = s1_api.charAt(4); // 从0开始.
        System.out.println("c1_api:" + c1_api);
        int i1_api = s1_api.codePointBefore(2);
        int i2_api = s1_api.codePointAt(2);
        int i3_api = s1_api.codePointCount(3, 5);
        System.out.println("i1_api:" + i1_api + "  i2_api:" + i2_api + "  i3_api:" + i3_api);

        String s1a_api = "abc不★*&";
        String s1b_api = (String) s1a_api.subSequence(3, 5);
        String s1c_api = s1a_api.substring(3);
        String s1d_api = s1a_api.substring(3, 5);
        System.out.println("s1b_api:" + s1b_api + "   s1c_api:" + s1c_api + "   s1d_api:" + s1d_api);

        String s1e_api = "123";
        String s1f_api = s1e_api.concat("123");
        System.out.println("s1f_api:" + s1f_api);
    }

    /*
     * 比较
     * compare:    可以返回距离.
     * this.charAt(k)-anotherString.charAt(k); 如果只有长度不一样: this.length() - anotherString.length()
     * 例 :  "abcd".compareTo("ab") 返回  2;       "abcd".compareTo("aB") 返回  32;
     * contentEquals:   有了contentEquals(CharSequence),为什么又有contentEquals(StringBuffer).
     * equals:  Case.
     * endWith,startWith:   startWith可以指定offset,从offset位开始算起;endWith没有offset.
     */
    @Test
    public void test11(){
        String s2_api = "abcd";
        String s3_api = "ab";
        System.out.println("compare 1:" + s2_api.compareTo(s3_api));
        System.out.println("compare 2:" + s2_api.compareToIgnoreCase(s3_api));
        System.out.println("contentEquals CharSequence s2_api:" + s2_api.contentEquals(s3_api));
        String s21_api = "ab c123223a c";
        System.out.println("s21_api end with:" + s21_api.endsWith(" c"));
        System.out.println("s21_api equals:" + s21_api.equals("ab c"));
        System.out.println("s21_api equalsIgnoreCase:" + s21_api.equalsIgnoreCase("AB c"));
        System.out.println("s21_api start with:" + s21_api.startsWith("ab"));
        System.out.println("s21_api start with offset:" + s21_api.startsWith("123", 4)); // 从第4位开始算.  
    }

    /*格式化.
     *format: 格式很多. 类似　C 中springf.
     *trim: space,包括tab
     */
    @Test
    public void test12(){
        Calendar c = Calendar.getInstance();
        String s7_api = String.format("Duke's Birthday: %1$tB %1$te,%1$tY", c); // 1$: 1st args. t: prefix date/time.
        String s8_api = String.format("num format:%1$x", 1234); // 转成16进制.
        System.out.println("s41api format date/time:" + s7_api);
        System.out.println("s42api format num:" + s8_api);
        System.out.println(String.format("phone number: %04d - %7s", 518, "7508009"));

        String s7a_api = "   abc        ";
        String s7b_api = s7a_api.trim();
        System.out.println("s7b_api trim:" + s7b_api.trim() + "   length:" + s7b_api.length());        
    }

    /*转换
     *getBytes :  将 String 转为 byte[].   charsetName.
     *getChars :  取chars, 放到char[]的指定位置.    
     *toCharArray: 将String 转为 char[].
     *toLowerCase , toUpperCase: 
     */
    @Test
    public void test13(){
        String s9_api = "abcd123不";
        byte[] b9_api = s9_api.getBytes();
        //        byte[] b9_api = s9_api.getBytes("GBK");
        //        byte[] b9_api = s9_api.getBytes("UTF-8");
        // 不要使用 这个，(byte)char, 所以只取低8位，对于中文就会出现问题;
        //        byte[] b9_api = new byte[100];
        //        s9_api.getBytes(0, s9_api.length(), b9_api, 0);
        String s91_api = "";
        for (int i = 0; i < b9_api.length; i++) {
            s91_api = s91_api + " " + b9_api[i];
        }
        System.out.println("b9_api:" + s91_api);
        System.out.println("trans back s9_api: " + new String(b9_api));

        String s10_api = "abc不*^我123";
        char[] c10_api = new char[10];
        String s11_api = "";
        s10_api.getChars(3, 8, c10_api, 0);
        for (int i = 0; i < 5; i++) {
            s11_api += c10_api[i];
        }
        System.out.println("s11_api:" + s11_api);

        String s10a_api = "abc不i*我";
        String s10b_api = "";
        char[] c10a_api = s10a_api.toCharArray();
        for (int i = 0; i < c10a_api.length; i++) {
            s10b_api += " " + c10a_api[i];
        }
        System.out.println("c10a_api:" + s10b_api);

        String s10c_api = "abc123ABC";
        System.out.println("s10c_api lowcase:" + s10c_api.toLowerCase());
        System.out.println("s10c_api uppercase:" + s10c_api.toUpperCase());        
    }

    /* 查找,替换,匹配,分组
     * indexof:   char or String, offset :  如果带offset,返回的结果也是从第0位开始算的,而不是第offset位开始算. 作用觉得应该是:有多个出现在String中时，可以排除前面几个.
     * lastindexof: char or String, offset :与index一样,如果指定了offset,则排除后面的，即从第0位到第offset位中找最后出现的.
     *
     * replace  :  char , charsequence , regex 
     * 
     * match , regionmatch : 支持与另一个String的一部分进行匹配.
     * contains: 这个不支持regex.
     * 
     * split:   可以指定次数.
     */
    @Test
    public void test14(){
        String s12_api = "123老外俄方abc123 ";
        System.out.println("s12_api index int:" + s12_api.indexOf('3'));
        System.out.println("s12_api index int offset:" + s12_api.indexOf('俄', 3));
        System.out.println("s12_api index String:" + s12_api.indexOf("1", 3));
        System.out.println("s12_api index String offset:" + s12_api.indexOf("123", 4));

        String s13_api = "abc 321232哦爱恩奶粉 123  但是  saabd s ";
        System.out.println("s13_api lastindex int:" + s13_api.lastIndexOf('3'));
        System.out.println("s13_api lastindex int offset :" + s13_api.lastIndexOf('3', 17));
        System.out.println("s13_api lastindex String:" + s13_api.lastIndexOf("123"));
        System.out.println("s13_api lastindex String offset:" + s13_api.lastIndexOf("123", 20));

        String s14_api = "abc 321232哦爱恩奶粉* 123.*  但是  saabd s  ";
        String s14a_api = s14_api.replace('3', '2'); // not regex
        String s14b_api = s14_api.replace("3.*", "00"); // not regex 
        String s14c_api = s14_api.replaceAll("3.*2", "0"); // 最长匹配原则.
        String s14d_api = s14_api.replaceFirst("3.*2", "0"); // 最长匹配原则.
        String s14e_api = s14_api.replace((CharSequence) ".*", "2"); // replace(CharSequence, CharSequence) 是 literal, 即: Pattern.compile(target.toString(), Pattern.LITERAL)
        System.out.println("s14a_api repalce char:" + s14a_api);
        System.out.println("s14a_api repalce charsequence:" + s14b_api);
        System.out.println("s14a_api repalceAll regex:" + s14c_api);
        System.out.println("s14a_api repalceFirst regex:" + s14d_api);
        System.out.println("s14e_api replace(CharSequence, CharSequence): " + s14e_api);

        String s15_api = "abc 321232哦爱恩奶粉 123  但是  saabd s  ";
        System.out.println("s15_api match regex:" + s15_api.matches("sa")); //false ????

        String s15a_api = "ab c";
        System.out.println("s15a_api contains:" + s15a_api.contains("ab ")); // contains(CharSequence)  String,StringBuffer,StringBuilder,CharBuffer 都 implements CharSequence.

        String s16_api = "abc 321232哦爱恩奶粉 123 32 但是  saabd s  ";
        String[] s16a_api = s16_api.split("32");
        String s16b_api = "";
        for (int i = 0; i < s16a_api.length; i++) {
            s16b_api += "     " + s16a_api[i];
        }
        System.out.println("s16a_api split:" + s16b_api);
        String s17_api = "abc 321232哦爱恩奶粉 123 32 但是  saabd s  ";
        String[] s17a_api = s17_api.split("32", 3); // 只匹配2次. String中剩下的部分都放在split后的最后一个元素中.
        String s17b_api = "";
        for (int i = 0; i < s17a_api.length; i++) {
            s17b_api += "     " + s17a_api[i];
        }
        System.out.println("s17b_api split limit:" + s17b_api);
    }

    /*Static
     *  copyValueOf, offset:   将char[]转为String.  和String(char[]) 一样.
     *  ValueOf:  基本上能将所有的type转为String.
     */
    @Test
    public void test15(){
        char[] c18_api = { 'a', 'b', '不', '*', '为' };
        String s18_api = String.copyValueOf(c18_api);
        String s19_api = String.copyValueOf(c18_api, 2, 2);
        String s20_api = new String(c18_api);
        System.out.println("s18_api:" + s18_api + "   s19_api:" + s19_api + "    s20_api:" + s20_api);

        boolean b18a_api = false;
        int i18a_api = 338;
        long l18a_api = 238319986;
        char[] c18a_api = { '2', '喔', 'a' };
        System.out.println("b18a_api valueof boolean:" + String.valueOf(b18a_api));
        System.out.println("i18a_api valueof int :" + String.valueOf(i18a_api));
        System.out.println("l18a_api valueof long:" + String.valueOf(l18a_api));
        System.out.println("c18a_api valueof char[]:" + String.valueOf(c18a_api));
    }
    
    @Test
    public void test16(){
    	String a = "http://q.10jqka.com.cn/stock/gn/xgycxg/";
    	System.out.println(a.split("/")[5]);
    	
    	String b = "/home/leslie/MyProject/StockAnalyse/html/boardHot/2016/06/06/notionHot_4.json";
    	System.out.println(b.split("_")[1]);
    	System.out.println(Integer.valueOf(b.split("_")[1].split("\\.")[0]));
    }
    
    @Test
    public void test17(){
    	String a = "2016-06-09";
    	String b = "2016-06-08";
    	System.out.println(a.compareTo(b));
    }
}

class test1 {
    public int age;
    public String name;

    test1(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /*
     * 自动调用toString(),将object传给:  println , printf, string concatenation operator, assert, printted by a debugger.
     */
    @Override
    public String toString() {
        return "age=" + age + ", name=" + name;
    }
}
