package com.claimplat.serviceapi.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**标准RSA256withRSA加解签
 * @author Joker
 *
 */
public class DlzSignatureUtils {

	public static String SIGNATURE_ALGO = "SHA256withRSA";
	public static String SIGNATURE_KEY_ALGO = "RSA";
	public static String SYMM_KEY_ALGO_NAME = "DESede";
	public static String SYMMETRIC_ENCRYPTION_ALGO = "DESede/ECB/PKCS5Padding";
	public static String PUBLIC_KEY = "PUBLIC_KEY";
	public static String PEIVATE_KEY = "PEIVATE_KEY";
	
	static {
		if(Security.getProvider("BC") == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
	}
	
	public static byte[] base64Decode(String publicKey4RSA) {
		return Base64.decodeBase64(publicKey4RSA.getBytes());
	}
	
	public static String base64Encode(byte[] sign) {
		return new String(Base64.encodeBase64(sign));
	}
	
	public static byte[] sign(byte[] data, byte[] privateKey, String olgorithm) {
		try {
			
			PrivateKey Key = recoverPrivate(privateKey);
			Signature signature = Signature.getInstance(olgorithm);
			signature.initSign(Key);
			signature.update(data);
			return signature.sign();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static PrivateKey recoverPrivate(byte[] privateKey) {
		try {
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
			KeyFactory keyFactory = KeyFactory.getInstance(SIGNATURE_KEY_ALGO);
			return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}
