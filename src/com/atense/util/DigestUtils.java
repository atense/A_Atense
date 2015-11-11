package com.atense.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.annotation.SuppressLint;

/**
 * DigestUtil
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-04
 *
 */
public class DigestUtils {
	/**
     * Used to build output as Hex
     */
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    /**
     * encode By MD5
     * 
     * @param str
     * @return String
     */
    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return bytes2Hex(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * AES Encrypt
	 * 
	 * @param content
	 *            Need encrypted string
	 * @param key 
	 * 		      	密钥，长度必须是8的倍数
	 * @return Encrypted 16 hex string
	 */
	@SuppressLint("TrulyRandom")
	public static String AESEncrypt(String content, String key) throws RuntimeException {
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] byteContent = content.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] bytes = cipher.doFinal(byteContent);
			return bytes2Hex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * AES Decrypt
	 * 
	 * @param hexString
	 *            Encrypted 16 hex string
	 * @param key 
	 * 		      	Length must be in multiples of 8
	 * @return Decrypted string
	 * @exception
	 */
	public static String AESDecrypt(String hexString, String key) throws RuntimeException{
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] content = hex2Bytes(hexString);
			byte[] bytes = cipher.doFinal(content);
			return new String(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    /**
     * Converts an array of bytes into an array of characters representing the hexadecimal values of each byte in order.
     * The returned array will be double the length of the passed array, as it takes two characters to represent any
     * given byte.
     * 
     * @param data a byte[] to convert to Hex characters
     * @return A String containing hexadecimal characters
     */
    public static String bytes2Hex(byte[] data) {
    	if (data == null || data.length <= 0) {
			return null;
		}
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_UPPER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_UPPER[0x0F & data[i]];
        }
        return new String(out);
    }
    
    /**
	 * 16 hex string converted to byte array
	 * 
	 * @param hexString
	 *            16 hex string
	 * @return byte array
	 */
	public static byte[] hex2Bytes(String hex) {
		if (hex == null || hex.equals("")) {
			return null;
		}
		int length = hex.length() / 2;
		if (hex.length() % 2 != 0) {
			return null;
		}
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hex.charAt(pos)) << 4 | charToByte(hex
					.charAt(pos + 1)));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/**
	 * 
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

}
