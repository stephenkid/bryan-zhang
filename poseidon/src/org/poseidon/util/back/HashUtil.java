package org.poseidon.util.back;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;
import org.poseidon.util.LoggerUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * HashUtil
 * 
 * @author 何晓滨
 * @version 创建时间：Sep 1, 2008 3:00:29 PM
 * last modify by weidong_zhao
 * add DES
 */
public abstract class HashUtil {
	
	private static final Logger LOGGER = Logger.getLogger(HashUtil.class);
	static{
		LoggerUtil.initLogging(LOGGER);
	}
	
    /**
     * 计算字符串的MD5值
     * @param source
     * @return MD5值
     */
    public static String getMD5(String source) {
        try {
            return hash(source.getBytes(), null, "MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 哈希算法
     * @param source
     * @param update
     * @param hashType
     * @return 哈希值
     * @throws NoSuchAlgorithmException
     */
    public static String hash(byte[] source, byte[] update, String hashType) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(hashType);
        md.update(source);
        if (update != null) {
            return toHexString(md.digest(update));
        } else {
            return toHexString(md.digest());
        }
    }
    
    /** 
     * 加密 
     * @param src 加密的字符串 
     * @param key 密钥，长度必须是8的倍数 
     * @return 返回加密后的数据 | "null"当失败时候的返回字符串,调用者需要验证返回值,因为该方法不抛异常
     * @throws Exception 
     */
    public static String encryptDES(String src, String key) {
    	try {
    		/**
    		 * 分步步骤:
    		 * 1 将要解密的字符串转化成字节码
    		 * 	byte[] strByte = src.getBytes();
    		 * 2 密钥也转化成字节码
    		 * 	byte[] keyByte = key.getBytes();
    		 * 3 调用内部DES的加密方法,返回的也是字节码
    		 * 	byte[] result = encrypt(strByte,keyByte);
    		 * 4 将字节码用BASE64加密,以字符串形式返回
    		 * 	return new BASE64Encoder().encode(result);
    		 */
			return new BASE64Encoder().encode(encrypt(src.getBytes(), key.getBytes()));
		} catch (Exception e) {
			LOGGER.warn("[DES 加密出现异常][source:"+src+"][key:"+key+"]");
			LOGGER.error(e.getMessage(), e);
			return "null";
		}
    }
    
    /** 
     * 解密 
     * @param src 解密的字符串 
     * @param key 密钥，长度必须是8的倍数 
     * @return 返回解密后的原始数据 | "null"当失败时候的返回字符串,调用者需要验证返回值,因为该方法不抛异常
     * @throws Exception 
     */
    public static String decryptDES(String str,String key) {
    	try {
    		/**
    		 * 分步步骤:
    		 * 1 将要解密的字符串通过BASE64解密成字节码
    		 * 	byte[] strByte = new BASE64Decoder().decodeBuffer(str);
    		 * 2 密钥也转化成字节码
    		 * 	byte[] keyByte = key.getBytes();
    		 * 3 调用内部DES的解密方法,返回的也是字节码
    		 * 	byte[] result = decrypt(strByte,keyByte);
    		 * 4 将字节码封装成 String 类型返回
    		 * 	return new String(result);
    		 */
			return new String(decrypt(new BASE64Decoder().decodeBuffer(str),key.getBytes()));
		} catch (Exception e) {
		    LOGGER.warn("[DES 解密出现异常][source:"+str+"][key:"+key+"]");
		    LOGGER.error(e.getMessage(), e);
			return "null";
		}
    }

    /**
     * 得到字节序列的可见字符表现格式
     * @param bytes
     *            字节序列
     * @return 可见字符表现格式
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(HexChar[(bytes[i] & 0xf0) >>> 4]);
            sb.append(HexChar[bytes[i] & 0x0f]);
        }
        return sb.toString();
    }
    
    private static final char[] HexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    
    private static final byte[] DESIV = {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0x90,
        (byte) 0xAB, (byte) 0xCD, (byte) 0xEF
    };//设置向量，略去
    
    //设置向量;//加密算法的参数接口，IvParameterSpec是它的一个实现
    private static AlgorithmParameterSpec iv = new IvParameterSpec(DESIV);

    /** 
     * DES加密 
     * @param src 数据源 
     * @param key 密钥，长度必须是8的倍数 
     * @return 返回加密后的数据 
     * @throws Exception 
     */
    private static byte[] encrypt(byte[] src, byte[] key) throws Exception {

        Key securekey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key));

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        
        cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

        return cipher.doFinal(src);
    }

    /** 
     * DES解密 
     * @param src 数据源 
     * @param key 密钥，长度必须是8的倍数 
     * @return 返回解密后的原始数据 
     * @throws Exception 
     */
    private static byte[] decrypt(byte[] src, byte[] key) throws Exception {

        Key securekey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key));

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv);

        return cipher.doFinal(src);
    }

}
