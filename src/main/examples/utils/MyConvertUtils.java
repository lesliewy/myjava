package examples.utils;

import java.security.InvalidParameterException;

import org.apache.commons.lang.StringUtils;

/*
 * 可以参考hicommon.jar 中的 HiConvHelper.java
 */
public class MyConvertUtils{
	/**
	 *  二进制转字符串: "12a" 转成了 "313261": 不是每个字节的转换，而是利用ASCII码做了中转.
	 *  @param b  :  待转换的字节数组
	 */
	public static String byte2hex(byte[] b) {
	     String hs=""; 
	     String stmp=""; 
	     for (int n=0;n<b.length;n++){ 
	       stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
	       if (stmp.length()==1)
	    	   hs=hs+"0"+stmp; 
	       else
	    	   hs=hs+stmp;
//	       if (n<b.length-1)  hs=hs+":"; 
	     }
	     return hs.toUpperCase();
	}
	/*
	 * 
	 * 
	 */
	public static String byte2hex2(byte[] b){
		StringBuffer buf = new StringBuffer();
		for (int offset =0;offset<b.length;offset++){
			int i;
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString().toUpperCase();
	}
	/*
	 * 字节转为二进制: 123Aa 二进制:  0011000100110010001100110100000101100001   16进制: 3132334161
	 * 二进制就是将字节转为对应的ASCII码,如果是汉字这种的,会转成32位的二进制串;
	 * 16进制就是将ASCII码转为16进制,可以认为是将一个字节二进制分成4位一组,每组转成一个16进制字符.
	 * 
	 */
	public static String byteToBinary(byte[] byteArray){
		String b="",a="";
		for(int i=0;i<byteArray.length;i++){
			a = Integer.toBinaryString(byteArray[i]);
			if(a.length()<8){
				b=b+":"+StringUtils.leftPad(a, 8,'0');
			}else{
				b=b+":"+a;
			}
		}
		return b;
	}
	
	/**
	 * 16进制转二进制
	 * @param args
	 */
	public static byte[] hex2byte(byte[] b){
		if((b.length%2)!=0){
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length/2];
		for (int n=0; n<b.length; n+=2){
			String item=new String(b,n,2);
			// 两位一组，表示一个字节
			b2[n/2]=(byte)Integer.parseInt(item,16);
		}
		return b2;
	}
	
	/**
	 * Change byte array to String.  可能出现很多乱码字符.因为不再ASCII内.
	 * @return String
	 */
	public static String bytesToString(byte[] encrytpByte){
		String result = "";
		for (Byte bytes : encrytpByte){
			result += (char) bytes.intValue();
		}
		return result;
	}
	/*
	 * BCD 码转为ASCII.
	 */
    public static byte[] bcd2Ascii(byte bytes[])
    {
        byte temp[] = new byte[bytes.length * 2];
        for(int i = 0; i < bytes.length; i++)
        {
            temp[i * 2] = (byte)(bytes[i] >> 4 & 15);
            temp[i * 2 + 1] = (byte)(bytes[i] & 15);
        }
        return temp;
    }
    /*
     * ASCII 转为 String
     */
    public static String ascii2Str(byte ascii[])
    {
        StringBuffer res = new StringBuffer();
        for(int i = 0; i < ascii.length; i++)
            res.append(strValue(ascii[i]));

        return res.toString();
    }
    static final char ascii[] = "0123456789ABCDEF".toCharArray();
    private static char strValue(byte asc)
    {
        if(asc < 0 || asc > 15)
            throw new InvalidParameterException();
        else
            return ascii[asc];
    }
    /*
     * String 转为ASCII:
     */
    public static byte[] str2Ascii(String s)
    {
        byte str[] = s.toUpperCase().getBytes();
        byte ascii[] = new byte[str.length];
        for(int i = 0; i < ascii.length; i++)
            ascii[i] = (byte)asciiValue(str[i]);
        return ascii;
    }
    private static int asciiValue(byte b)
    {
        if(b >= 48 && b <= 57)
            return b - 48;
        if(b >= 97 && b <= 102)
            return (b - 97) + 10;
        if(b >= 65 && b <= 70)
            return (b - 65) + 10;
        else
            throw new InvalidParameterException();
    }
	/*
	 *  十六进制转为二进制(字符串型的)  例如:  EF3:  111011110011
	 */
	public static String hex2Binary(String val)throws Exception{
	    StringBuffer ret = new StringBuffer();

	    for (int i = 0; i < val.length(); i++){
	    	ret.append(hex2binary(val.charAt(i)));
	    }
	    return ret.toString();
	}
	  public static String hex2binary(char hex) throws Exception
	  {
	    switch (hex)
	    {
	    case '0':
	      return "0000";
	    case '1':
	      return "0001";
	    case '2':
	      return "0010";
	    case '3':
	      return "0011";
	    case '4':
	      return "0100";
	    case '5':
	      return "0101";
	    case '6':
	      return "0110";
	    case '7':
	      return "0111";
	    case '8':
	      return "1000";
	    case '9':
	      return "1001";
	    case 'A':
	    case 'a':
	      return "1010";
	    case 'B':
	    case 'b':
	      return "1011";
	    case 'C':
	    case 'c':
	      return "1100";
	    case 'D':
	    case 'd':
	      return "1101";
	    case 'E':
	    case 'e':
	      return "1110";
	    case 'F':
	    case 'f':
	      return "1111";
	    case ':':
	    case ';':
	    case '<':
	    case '=':
	    case '>':
	    case '?':
	    case '@':
	    case 'G':
	    case 'H':
	    case 'I':
	    case 'J':
	    case 'K':
	    case 'L':
	    case 'M':
	    case 'N':
	    case 'O':
	    case 'P':
	    case 'Q':
	    case 'R':
	    case 'S':
	    case 'T':
	    case 'U':
	    case 'V':
	    case 'W':
	    case 'X':
	    case 'Y':
	    case 'Z':
	    case '[':
	    case '\\':
	    case ']':
	    case '^':
	    case '_':
	    case '`': } throw new Exception("十六进制转为二进制时, 有非法十六制字符:" + hex);
	  }
	  /*
	   * 二进制(字符串型)转为十六进制.   例如:  110000101111:  C2F
	   */
	  public static String binary2hex(String binary)
	  {
	    String hexString = "";
	    int binLen = binary.length();
	    if (binLen % 4 != 0)
	    {
	      binary = StringUtils.repeat("0", 4 - binLen % 4) + binary;
	      binLen = binary.length();
	    }
	    for (int i = 0; i < binLen; i += 4)
	    {
	      hexString = hexString + Integer.toHexString(Integer.valueOf(binary.substring(i, i + 4), 2).intValue());
	    }
	    return hexString;
	  }
	  
}
