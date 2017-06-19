package examples.crypto.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

import examples.utils.MyConvertUtils;
/**
 * RSA:  第一个既能用于用于数字签名,也能用于加解密的算法; 非对称的, 公钥加密,私钥解密; 用私钥进行签名可实现防抵赖,MD5 SHA-1是做不到的.
 *       速度慢,一般用于少量数据加密.
 * 
 * key 的产生方式:  KeyPairGenerator
 */
public class RSAEncrypt{
	/** *//**
	* Main method for RSAEncrypt.
	* @param args
	*/
	public static void main(String[] args){
		try{
			RSAEncrypt encrypt = new RSAEncrypt();
			String encryptText = "encryptText";
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);  // keysize
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			System.out.println("privateKey size:"+privateKey.getFormat().length());
			byte[] e = encrypt.encrypt(publicKey, encryptText.getBytes());
			byte[] de = encrypt.decrypt(privateKey,e);
			System.out.println("encrypt:"+MyConvertUtils.bytesToString(e));
			System.out.println("deencrypt:"+MyConvertUtils.bytesToString(de));
			}catch (Exception e){
				e.printStackTrace();
			}
	}

	/** *//**
	* Encrypt String.  公钥加密
	* @return byte[]
	*/
	protected byte[] encrypt(RSAPublicKey publicKey, byte[] obj){
		if (publicKey != null){
			try{
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				return cipher.doFinal(obj);
				}catch (Exception e){
					e.printStackTrace();
				}
		}
		return null;
	}
	/** *//**
	* Basic decrypt method 私钥解密.
	* @return byte[]
	*/
	protected byte[] decrypt(RSAPrivateKey privateKey, byte[] obj){
		if (privateKey != null){
			try{
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				return cipher.doFinal(obj);
				} catch (Exception e){
					e.printStackTrace();
				}
		}
		return null;
	}
}
