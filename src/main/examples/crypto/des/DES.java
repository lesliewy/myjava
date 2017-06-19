package examples.crypto.des;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import examples.utils.MyConvertUtils;
/**
 * 
 * 各种算法名字:  包括algorithm/mode/padding  具体看technotes->guides->security->Java ™ Cryptography Architecture Standard Algorithm Name Documentation 
 * DES key可以用   KeyGenerator , KeyFactory ,SecretKeySpec 3种方式来产生,但是加密和解密的方式必须统一,   否则报: Given final block not properly padded
 *    后两种种方式产生的key和密文是一样的.
 *    KeyFactory 需要一个keyspecification, 依赖于provider,getInstance中指定provider,没有指定就找最匹配的.
 *    SecretKeySpec 独立于provider,
 * 发现只要key的产生方式一样,cipher.init(Cipher.ENCRYPT_MODE, secretKey); 还是 cipher.init(Cipher.ENCRYPT_MODE, secretKey,sr); 带不带sr，密文都是一样的，原因可能是key是一样的.
 * 
 * @author leslie
 */
public class DES {
	/**
	 * @return DES算法密钥
	 */
	public static byte[] generateKey() {
		try {
	        // DES算法要求有一个可信任的随机数源
		    SecureRandom sr = new SecureRandom();
		    // 生成一个DES算法的KeyGenerator对象
		    KeyGenerator kg = KeyGenerator.getInstance("DES");
		    kg.init(sr);
		    // 生成密钥
		    SecretKey secretKey = kg.generateKey();
		    // 获取密钥数据
		    byte[] key = secretKey.getEncoded();
		    return key;
		} catch (NoSuchAlgorithmException e) {
			System.err.println("DES算法，生成密钥出错!");
			e.printStackTrace();
		}
		return null;
	}
	/**
	　　* 加密函数:  init 中带有SecureRandom
	　　*
	　　* @param data 加密数据
	　　* @param key  密钥
	　　* @return 返回加密后的数据
	　　*/
	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			// 1,生成key: 2种方式产生的key不同.
			SecureRandom sr = new SecureRandom();           // DES算法要求有一个可信任的随机数源
			// begin1: 用SecretKeyFactory来产生key
			/*
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);
			*/
			// end1

			// begin2: KeyGenerator 来产生key
			/*
			KeyGenerator   _generator   =   KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(key));
			SecretKey secretKey =_generator.generateKey();
			*/
			// end2
			
			// begin3: SecretKeySpec 来产生key
			SecretKeySpec secretKey = new SecretKeySpec(key,"DES");
			// end 3
			
			// 2, 获取cipher，初始化，加密.
//			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");    // 如果provider是SunJCE ,DES/ECB/PKCS5Padding  和  DES是一样的.
			Cipher cipher = Cipher.getInstance("DES");		
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte encryptedData[] = cipher.doFinal(data);
			return encryptedData;
		} catch (Exception e) {
			System.err.println("DES算法，加密数据出错!");
			e.printStackTrace();
		}
		return null;
	}
	/**
	　　* 解密函数
	　　* @param data 解密数据
	　　* @param key  密钥
	　　* @return 返回解密后的数据
	　　*/
	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// begin1 SecretKeyFactory 来产生key
			/*
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);
			*/
			//end1
			
			// begin2: KeyGenerator 来产生key
			/*
			KeyGenerator   _generator   =   KeyGenerator.getInstance("DES");  
			_generator.init(new SecureRandom(key));  
			SecretKey secretKey =_generator.generateKey(); 
			*/
			// end2
			
			// begin3: SecretKeySpec 来产生key
			SecretKeySpec secretKey = new SecretKeySpec(key,"DES");
			// end 3
			
			// using DES in ECB mode
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
//			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			// 正式执行解密操作
			byte decryptedData[] = cipher.doFinal(data);
			return decryptedData;
		} catch (Exception e) {
			System.err.println("DES算法，解密出错。");
			e.printStackTrace();
		}
		return null;
	}
	/**
	　　* 加密函数
	　　*
	　　* @param data
	　　*            加密数据
	　　* @param key
	　　*            密钥
	　　* @return 返回加密后的数据
	　　*/
	public static byte[] CBCEncrypt(byte[] data, byte[] key, byte[] iv) {
		try {
			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//			Cipher cipher = Cipher.getInstance("DES");                     // ECB mode cannot use IV
			// 若采用NoPadding模式，data长度必须是8的倍数
			// Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
			// 用密匙初始化Cipher对象
			IvParameterSpec param = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);
			// 执行加密操作
			byte encryptedData[] = cipher.doFinal(data);
			return encryptedData;
		} catch (Exception e) {
			System.err.println("DES算法，加密数据出错!");
			e.printStackTrace();
		}
		return null;
	}
	/**
	　　* 解密函数
	　　*
	　　* @param data
	　　*            解密数据
	　　* @param key
	　　*            密钥
	　　* @return 返回解密后的数据
	　　*/
	public static byte[] CBCDecrypt(byte[] data, byte[] key, byte[] iv) {
		try {
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);
			// using DES in CBC mode
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// 若采用NoPadding模式，data长度必须是8的倍数
			// Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
			// 用密匙初始化Cipher对象
			IvParameterSpec param = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, param);
			// 正式执行解密操作
			byte decryptedData[] = cipher.doFinal(data);
			return decryptedData;
		} catch (Exception e) {
			System.err.println("DES算法，解密出错。");
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		try {
			byte[] key = "11111111".getBytes();
			byte[] iv = "22222222".getBytes();
			byte[] data = DES.encrypt("ebc mode test".getBytes(), key);
			System.out.print("EBC mode:");
			System.out.println("secret data HEX:"+MyConvertUtils.byte2hex(data));
			System.out.println("decrypt data:"+new String(DES.decrypt(data, key)));
			System.out.print("CBC mode:");
			data = DES.CBCEncrypt("cbc mode test".getBytes(), key, iv);
			System.out.println("secret data HEX:"+MyConvertUtils.byte2hex(data));
			System.out.println(new String(DES.CBCDecrypt(data, key, iv)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("all providers:");
		Provider[] pa = Security.getProviders();
		if(pa == null){
			System.out.println("pa is null.");
		}else{
			for (int i=0;i<pa.length;i++){
				System.out.println(pa[i]+": "+pa[i].getName()+":"+pa[i].getInfo()+":"+pa[i].propertyNames());
			}
		}
	}
}

