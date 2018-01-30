package com.eccl.common.util.codeutil;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * 使用AES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储. 
 * 方法: void getKey(String strKey)从strKey的字条生成一个Key 
 *           String getEncString(String strMing)对strMing进行加密,返回String密文 
 *           String getDesString(String strMi)对strMin进行解密,返回String明文 
 *           byte[] getEncCode(byte[] byteS)byte[]型的加密 
 *           byte[] getDesCode(byte[] byteD)byte[]型的解密
 */
public class CodeUtil {
	private byte[] byteMi = null;
	private byte[] byteMing = null;
	private String strMi = "";
	private String strM = "";

	/** 加密工具 */
	private static Cipher encryptCipher = null;

	/** 解密工具 */
	private static Cipher decryptCipher = null;

	static boolean isInited = false;

	/**
	 * 默认构造方法，使用默认密钥
	 * 
	 * @throws Exception
	 */
	public CodeUtil() throws Exception {
	}

	/**
	 * 初始化
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	private void init(String strKey) throws NoSuchAlgorithmException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException {
		// 初始化keyGen
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");

		keyGen.init(128, new SecureRandom(strKey.getBytes()));
		SecretKey secretKey = keyGen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

		// 初始化cipher
		encryptCipher = Cipher.getInstance("AES");// 创建密码器
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);// 初始化

		decryptCipher = Cipher.getInstance("AES");// 创建密码器
		decryptCipher.init(Cipher.DECRYPT_MODE, key);// 初始化

		isInited = true;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 根据参数生成KEY
	 * 
	 * @param strKey
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public void setKey(String strKey) throws NoSuchAlgorithmException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException {
		if (!isInited)// 如果没有初始化过,则初始化
		{
			init(strKey);
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 */
	public void setEncString(String strMing) {
		try {
			byte[] byteContent = strMing.getBytes();
			this.byteMi = this.getEncCode(byteContent);
			this.strMi = parseByte2HexStr(byteMi); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.byteMing = null;
			this.byteMi = null;
		}
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;

		try {
			byteFina = encryptCipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return byteFina;
	}

	/**
	 * 解密:以String密文输入,String明文输出
	 * 
	 * @param strMi
	 */
	public void setDesString(String strMi) {
		try {
			byte[] content = parseHexStr2Byte(strMi);
			byte[] result = this.getDesCode(content);
			this.strM = new String(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {

		byte[] byteFina = null;
		try {
			byteFina = decryptCipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteFina;
	}

	/**
	 * 返回加密后的密文strMi
	 */
	public String getStrMi() {
		return strMi;
	}

	/**
	 * 返回解密后的明文
	 */
	public String getStrM() {
		return strM;
	}

	/**
	 * 测试方法
	 */
	public static void main(String[] args) throws Exception {
		CodeUtil abc = new CodeUtil();
		String key = "ECCL"; // 初始化密钥。
		abc.setKey(key); // 调用set函数设置密钥。
		abc.setEncString("asdfs");// 将要加密的明文传送给Encrypt.java进行加密计算。
		String Mi = abc.getStrMi(); // 调用get函数获取加密后密文。
		System.out.println("加密后===" + Mi);

		abc.setDesString(Mi); // 将要解密的密文传送给Encrypt.java进行解密计算。
		String M = abc.getStrM(); // 调用get函数获取解密后明文。
		System.out.println("解密后===" + M);
	}

}
