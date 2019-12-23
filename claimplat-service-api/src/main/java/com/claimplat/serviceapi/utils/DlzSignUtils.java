package com.claimplat.serviceapi.utils;

public class DlzSignUtils {

	private static byte[] privateKey;	//私钥
	
	private static byte[] publicKey;	//公钥
	
	private static String algorithm;	//算法

	public static void init(String publicKey4RSA, String privateKey4RSA, String algorithm) {
		privateKey = DlzSignatureUtils.base64Decode(publicKey4RSA);		
		publicKey = DlzSignatureUtils.base64Decode(privateKey4RSA);	
		algorithm = "SHA256withRSA";
	}

	/*签名*/
	public static String sign(String unisigned) {
		String signed = null;
		if(unisigned != null ) {
			try {
				signed = DlzSignatureUtils.base64Encode(DlzSignatureUtils.sign(unisigned.getBytes(),privateKey,"SHA256withRSA"));
			} catch (Exception e) {
			}
		}
		return signed;
	}
	
	
}
