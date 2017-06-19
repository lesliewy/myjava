package examples.crypto.des;

import   java.security.Key;  
import   java.security.SecureRandom;  
import   javax.crypto.Cipher;  
import   javax.crypto.KeyGenerator;  
/*
 * DES: 单钥加密机制,对称的.
 *    
 * 思路: 因为任意一个字符串,都是由若干字节表示的,每个字节就是一个8位的二进制数.
 *   又因为一个8位二进制数,可由两个16进制字符串表示.因此任意一个字符串可以由两个16进制字符串表示.
 *   而DES是对8位二进制数进行加密,解密,所以用DES加解密时，可以把加密所得的8位二进制数,转成两位16进制数保存,传输.
 *   
 *   为什么通过两位16进制数字符串保存密文呢?
 *   一个字符串加密后所得的8位二进制数,通常不再是字符串了,如果直接把这种密文所得的8为二进制数强制转成字符串,有许多信息因为
 *   异常而丢失,导致解密失败. 因此要把这个8位二进制数,直接以数的形式保存下来,而通常是用两位16进制数表示.
 *  
 */

import examples.utils.MyConvertUtils;

/**  
  *    
  */  
public   class   DesEncrypt  
{  
	Key   key;  
	/**
	 * DES 用KeyGenerator 产生密钥: 可以调用init来初始化,也可以不调用.
	 *   不调用: 即不使用key, 每次产生的密文都不一样, 但是加解密没有问题, 这个很神奇.
	 *   _generator.init(new SecureRandom()):  用random来init keygenerator,与上面的一样，每次产生的都不一样.
	 *   _generator.init(new SecureRandom(strKey.getBytes())):  这个使用了key, 每次执行密文都一样.
	 * 
	 * @param strKey
	 */
	public void getKey(String strKey)  
	{
		try{
			KeyGenerator   _generator   =   KeyGenerator.getInstance("DES");  
//			_generator.init(new SecureRandom(strKey.getBytes()));  
//			_generator.init(new SecureRandom());
			this.key =_generator.generateKey();  
			_generator=null;  
		}catch(Exception e){  
			e.printStackTrace();  
		}
	}
/**  
  *   加密String明文输入,String密文输出  
  *   @param   strMing  
  *   @return  
  */  
	public String getEncString(String strMing){  
		String   strMi   =   "";  
//		BASE64Encoder base64en   =   new   BASE64Encoder();    // 不能使用，不知道为什么
		try{  
			return MyConvertUtils.byte2hex(getEncCode(strMing.getBytes()));
		}catch(Exception   e){  
			e.printStackTrace();  
		}  
		return   strMi;  
}  
	/**  
	  *   解密   以String密文输入,String明文输出  
	  *   @param   strMi  
	  *   @return  
	  */  
	public   String   getDesString(String   strMi)  
	{
//		BASE64Decoder   base64De   =   new   BASE64Decoder();  
		String   strMing   =   "";  
		try{  
			return new String(getDesCode(MyConvertUtils.hex2byte(strMi.getBytes())));
		}catch(Exception   e){
			e.printStackTrace();  
		}
		return   strMing;  
	}  
	/**  
	  *   加密以byte[]明文输入,byte[]密文输出  
	  *   @param   byteS  
	  *   @return  
	  */  
	private   byte[]   getEncCode(byte[]   byteS){
		byte[]   byteFina   =   null;  
		Cipher   cipher;  
		try{
			cipher = Cipher.getInstance("DES");  
			// 用密钥初始化Cipher对象,没有用SecureRandom.
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);  
		}catch(Exception e){
			e.printStackTrace();  
		}finally{
			cipher   =   null;  
		}
		return  byteFina;  
	}
	
	/**  
	  *   解密以byte[]密文输入,以byte[]明文输出  
	  *   @param   byteD  
	  *   @return  
	  */  
	private   byte[]   getDesCode(byte[]   byteD)  
	{  
		Cipher   cipher;  
		byte[]   byteFina=null;  
		try{  
			cipher   =   Cipher.getInstance("DES");  
			cipher.init(Cipher.DECRYPT_MODE,key);  
			byteFina   =   cipher.doFinal(byteD);  
		}catch(Exception   e){
			e.printStackTrace();  
		}finally{  
			cipher=null;  
		}  
		return   byteFina;  
	}  
	
	
	public   static   void   main(String[]   args){
		DesEncrypt des=new DesEncrypt();                    //实例化一个对像  
		des.getKey("aadd");//生成密匙 

		String   strEnc   =   des.getEncString("钟汉康");   //加密字符串,返回String的密文  
		System.out.println(strEnc);

		String   strDes   =   des.getDesString(strEnc);    //把String   类型的密文解密  
		System.out.println(strDes);
	}
}
