package examples.crypto.des;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.TestCase;

public class DESJUnit extends TestCase{
	
	/* DES:
	 *                 明文长度  明文字节长度  明文HEX长度  密文字节长度   密文HEX长度
	 * key长度: 8            8      8          16            16         32     
	 *                      16     16          32            24         48
	 *                      24     24          48            32         64
	 * 
	 * key长度: 16           8      8          16            16         32
	 *                      16     16          32            24         48
	 * 这种方式与下面的testCipherDES2运算结果不同，银联的用2.
	 */
	
	public void testCipherDES() throws Exception{
		try{
			String strKey="12345678";
			String key_hex=byte2hex(strKey.getBytes());
			String plain="abcdefgh";
			byte[] plainbyte=plain.getBytes();
			String plain_hex=byte2hex(plain.getBytes());
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(strKey.getBytes()));
			Key key = generator.generateKey();
			generator=null;
			Cipher cipher   =   Cipher.getInstance("DES");
			// 用密钥初始化Cipher对象,没有用SecureRandom.
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] b1=cipher.doFinal(plain.getBytes());
			System.out.println("key_hex:"+key_hex);
			System.out.println("plain byte length:"+plainbyte.length);
			System.out.println("plain hex length:"+plain_hex.length()+"   plain hex:"+plain_hex);
			System.out.println("cipher byte length:"+b1.length);
			System.out.println("cipher hex length:"+byte2hex(b1));
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	public void testCipherDES2() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		String plain="abcdefgh";
		byte[] plainbyte=plain.getBytes();
		String strKey="12345678";
		String key_hex=byte2hex(strKey.getBytes());
		String plain_hex=byte2hex(plain.getBytes());
		SecureRandom sr = new SecureRandom();
		// byte rawKeyData[] = /* 用某种方法获取原始密匙数据 */;
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(strKey.getBytes());
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(dks);
		// using DES in ECB mode
//		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
		byte[] b1=cipher.doFinal(plain.getBytes());
		System.out.println("key_hex:"+key_hex);
		System.out.println("plain byte length:"+plainbyte.length);
		System.out.println("plain hex length:"+plain_hex.length()+"   plain hex:"+plain_hex);
		System.out.println("cipher byte length:"+b1.length);
		System.out.println("cipher hex length:"+byte2hex(b1));
		
	}
	
	/*
	 * 3DES:
	 * key长度: 必须24                       明文字节长度/明文长度/密文字节长度/密文HEX长度: 8/8/16/32  16/16/24/48 
	 * 
	 */
	public void testCipher3DES(){
		String plain="1234567812345678";
		byte[] p1=plain.getBytes();
		String key="123456781234567812345678";
		String algorithm = "DESede";
		try {
			//生成密钥
			SecretKey deskey = new SecretKeySpec(key.getBytes(), algorithm);
			//加密
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] b1 = c1.doFinal(plain.getBytes());
		    String hexstr = byte2hex(b1);
		    System.out.println("plain byte length:"+p1.length);
			System.out.println("byte length:"+b1.length);
			System.out.println("string:"+hexstr);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}catch(javax.crypto.NoSuchPaddingException e2){
			e2.printStackTrace();
		}catch(java.lang.Exception e3){
			e3.printStackTrace();
		}
	}
	
	private  String byte2hex(byte[] b){
		//转成16进制字符串
		String hs="";
		String stmp="";
		for(int n=0;n<b.length;n++){
			// 整数转成16进制表示.
			stmp=(Integer.toHexString(b[n]&0xFF));
			if(stmp.length()==1){
				hs=hs+"0"+stmp;
			}else{
				hs=hs+stmp;
			}
		}
		return hs.toUpperCase();  // 转大写.
	}
	
	public void testDES1(){
		String source="1234567812345678";
		String encStr="";
		String key="1234567812345678";
		int type=0;
		encStr = DESImpl1.DES_1(source, key, type);
		System.out.println("encStr:"+encStr);
	}
}
