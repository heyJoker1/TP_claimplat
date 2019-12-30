package com.claimplat.serviceapi.utils;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author simon.xxm
 * @version $Id: RSA.java, v 0.1 2016年1月25日 上午10:34:58 simon.xxm Exp $
 */
public class RSA {
	/** 指定key的大小 */
	private static int KEYSIZE = 2048;
	private static final String encoding = "UTF-8";
	private static final String RSA_ALGORITHM = "RSA";
	//		 public static final String RSA_publicKey =
	//		 "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqfuP7kHYZbFabUdrsIfRZAtSjj9BC8G5DA3CrcTNiOch81/2kkqSDAOEjATFrvBC+XiO3TEc5Rc2/oZ9vLF4p7tI30fkLzx8Cy1BOYetnyCBdG49ZiFTmU3y5gvpgPFaSBwtpIzwpo32tGqd9LGlILjKFJ8NWK0M59ilXaLL5ckYMsErtBsK6ItDZUGfQdcIp8eLhRa557+DlOvRbu8CRJRL0tW0ASW9kWqCWWLrQHVLspl8ieb2v6vfeK94YH5stn+pwKUzT23kEfjwpU1R9TyD7ySOielwHOECdbRbrKk7OAw781U5X3k0sOeNU5Nw6xMNJzuv8ftBY7hJBiRAkwIDAQAB";
	//		 public static final String RSA_privateKey =
	//		 "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCp+4/uQdhlsVptR2uwh9FkC1KOP0ELwbkMDcKtxM2I5yHzX/aSSpIMA4SMBMWu8EL5eI7dMRzlFzb+hn28sXinu0jfR+QvPHwLLUE5h62fIIF0bj1mIVOZTfLmC+mA8VpIHC2kjPCmjfa0ap30saUguMoUnw1YrQzn2KVdosvlyRgywSu0Gwroi0NlQZ9B1winx4uFFrnnv4OU69Fu7wJElEvS1bQBJb2RaoJZYutAdUuymXyJ5va/q994r3hgfmy2f6nApTNPbeQR+PClTVH1PIPvJI6J6XAc4QJ1tFusqTs4DDvzVTlfeTSw541Tk3DrEw0nO6/x+0FjuEkGJECTAgMBAAECggEBAIcqO3Q4tat/kKlO3oocJdvIyRfFoqKHo+66znBBCzLun+eYCkiftWyKK47viIYoFQms3OV0VUax5BAWv8sY0BmIalTqJL+O+BAnJzNo+R2MyoPb2UTqAUDpY9mb5UycHq8ygPTVAdNfFaq3EO1viR/w8Pfe1c0KpjWB51UCy+Hmbzz4twcON5r1ilNFdBt6w/Sntfsjf/S1vdcLx8ZNiCAPd1LHHZxtHlZs0IdFrPBtgl55c4Cl0ntFT/g7RQOjKVuObnPN84FXf23KbxRhMKVoRhKT8Q5PVjNqqMAYjDjfKrHF6Yw0IAR9Z6+A/xnos5DDUG+OM6c8ea9vJ6B7VykCgYEA1+Q8DVEJcSi+nyrkcjKccn/jRW7GeKXklxkACtXiWAJ/1THVybRM3cfbYSEHsIppTJLaBi1f/cvOgNXktrAcYbewNe4ob8EKWWqYrIuEtcRMDXgLu5ALnYGFTKfphSBh1ZfgnuEy/Pa3tj/wHqGHq5RK2GTPjDTChhovsw42Ni0CgYEAyY/owceOPp+QQICStuRZYBZ+XOVsQ9SPhUaKL9c6qaanTtUULAzONeOl6ysmnI+NE2rPbsQxKrrBEC/lCWfV9L0X4X7Ouc2790pIIw5P1WwxbowSpqo5hl9dH8aBeshAo7+JuUl/hDh4QktVjRHAF80bkEw8meyqOmVlwG2bSb8CgYAyXxsv1DeKwoHvazeP+YUNJg+l9Jm0LqiuJHQhExRTiom++XizLjE9EdN6zxUXOMQmzKC4DkA2XCYbY0yQ33hPyGcBvkaLBJRgloF2yLq3GkzQW7EJGyvKnRy37PmMSSjqiBwtlceqw/nLORHSY8fe3aO055iRUwIL/fIhKfC2JQKBgQClzYFz1cnG7c7loF4PoGt8xUQQ+pBCg9nDkjEeBXg2EebSzCiZy7bdUXQsrQRICTXNYTFdNnoTYihqPluzjvzLI7k/PuaipQAX/by1SZKWRzeqbgLxollLlaqu9sWP0KaLjIWoKzN/+kvCjOHE93MCoTApVO0M2Ud2Xe6DiiYRVQKBgQC6wTn8Epni+ekrWG0s6kkcWxGuN99PLPM7VfwtEr80GkC+sCJ6cM+ZeZdQadiCPzCFJUNwogD9fsnjzrPex4h68+Eup4nTBvdEJRwYa2PU5dviYrbQ8jrSACebAfX4xYIUgfQQM/bHA0YblGYguNFr1aamu6JqQv+JALfhSPCq5Q==";

	//		public static final String RSA_publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqfuP7kHYZbFabUdrsIfRZAtSjj9BC8G5DA3CrcTNiOch81/2kkqSDAOEjATFrvBC+XiO3TEc5Rc2/oZ9vLF4p7tI30fkLzx8Cy1BOYetnyCBdG49ZiFTmU3y5gvpgPFaSBwtpIzwpo32tGqd9LGlILjKFJ8NWK0M59ilXaLL5ckYMsErtBsK6ItDZUGfQdcIp8eLhRa557+DlOvRbu8CRJRL0tW0ASW9kWqCWWLrQHVLspl8ieb2v6vfeK94YH5stn+pwKUzT23kEfjwpU1R9TyD7ySOielwHOECdbRbrKk7OAw781U5X3k0sOeNU5Nw6xMNJzuv8ftBY7hJBiRAkwIDAQAB";
	//		public static final String RSA_privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCp+4/uQdhlsVptR2uwh9FkC1KOP0ELwbkMDcKtxM2I5yHzX/aSSpIMA4SMBMWu8EL5eI7dMRzlFzb+hn28sXinu0jfR+QvPHwLLUE5h62fIIF0bj1mIVOZTfLmC+mA8VpIHC2kjPCmjfa0ap30saUguMoUnw1YrQzn2KVdosvlyRgywSu0Gwroi0NlQZ9B1winx4uFFrnnv4OU69Fu7wJElEvS1bQBJb2RaoJZYutAdUuymXyJ5va/q994r3hgfmy2f6nApTNPbeQR+PClTVH1PIPvJI6J6XAc4QJ1tFusqTs4DDvzVTlfeTSw541Tk3DrEw0nO6/x+0FjuEkGJECTAgMBAAECggEBAIcqO3Q4tat/kKlO3oocJdvIyRfFoqKHo+66znBBCzLun+eYCkiftWyKK47viIYoFQms3OV0VUax5BAWv8sY0BmIalTqJL+O+BAnJzNo+R2MyoPb2UTqAUDpY9mb5UycHq8ygPTVAdNfFaq3EO1viR/w8Pfe1c0KpjWB51UCy+Hmbzz4twcON5r1ilNFdBt6w/Sntfsjf/S1vdcLx8ZNiCAPd1LHHZxtHlZs0IdFrPBtgl55c4Cl0ntFT/g7RQOjKVuObnPN84FXf23KbxRhMKVoRhKT8Q5PVjNqqMAYjDjfKrHF6Yw0IAR9Z6+A/xnos5DDUG+OM6c8ea9vJ6B7VykCgYEA1+Q8DVEJcSi+nyrkcjKccn/jRW7GeKXklxkACtXiWAJ/1THVybRM3cfbYSEHsIppTJLaBi1f/cvOgNXktrAcYbewNe4ob8EKWWqYrIuEtcRMDXgLu5ALnYGFTKfphSBh1ZfgnuEy/Pa3tj/wHqGHq5RK2GTPjDTChhovsw42Ni0CgYEAyY/owceOPp+QQICStuRZYBZ+XOVsQ9SPhUaKL9c6qaanTtUULAzONeOl6ysmnI+NE2rPbsQxKrrBEC/lCWfV9L0X4X7Ouc2790pIIw5P1WwxbowSpqo5hl9dH8aBeshAo7+JuUl/hDh4QktVjRHAF80bkEw8meyqOmVlwG2bSb8CgYAyXxsv1DeKwoHvazeP+YUNJg+l9Jm0LqiuJHQhExRTiom++XizLjE9EdN6zxUXOMQmzKC4DkA2XCYbY0yQ33hPyGcBvkaLBJRgloF2yLq3GkzQW7EJGyvKnRy37PmMSSjqiBwtlceqw/nLORHSY8fe3aO055iRUwIL/fIhKfC2JQKBgQClzYFz1cnG7c7loF4PoGt8xUQQ+pBCg9nDkjEeBXg2EebSzCiZy7bdUXQsrQRICTXNYTFdNnoTYihqPluzjvzLI7k/PuaipQAX/by1SZKWRzeqbgLxollLlaqu9sWP0KaLjIWoKzN/+kvCjOHE93MCoTApVO0M2Ud2Xe6DiiYRVQKBgQC6wTn8Epni+ekrWG0s6kkcWxGuN99PLPM7VfwtEr80GkC+sCJ6cM+ZeZdQadiCPzCFJUNwogD9fsnjzrPex4h68+Eup4nTBvdEJRwYa2PU5dviYrbQ8jrSACebAfX4xYIUgfQQM/bHA0YblGYguNFr1aamu6JqQv+JALfhSPCq5Q==";
	//		public static String message = "<head><version>1.0</version><function>ant.bxcloud.health.report.sync</function><transTime>20191218180755</transTime><transTimeZone>UTC+8</transTimeZone><reqMsgId>20191218110400030005200091812586758965</reqMsgId><format>XML</format><signType>RSA</signType><asyn>0</asyn><cid>TPCX</cid></head><body><instSerailNo>$instInfoSerialNo</instSerailNo><outBizNo>20191218110400030091200028247523</outBizNo><reportNo>201912171100300605890500816257</reportNo><outReportNo></outReportNo><policies><policy><policyNo>19111750228796110149</policyNo><outPolicyNo></outPolicyNo></policy></policies><action>1</action><actionTime>20191218180755</actionTime><actionDesc></actionDesc><actionContext><extendInfo key=\"accidentDesc\" value=\"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh\" /><extendInfo key=\"accidentPersonCertName\" value=\"卜滢馥\" /><extendInfo key=\"accidentPersonCertNo\" value=\"110103198511258027\" /><extendInfo key=\"accidentPersonCertType\" value=\"10\" /><extendInfo key=\"accidentPersonPhone\" value=\"\" /><extendInfo key=\"accidentPersonUid\" value=\"\" /><extendInfo key=\"accidentTime\" value=\"20191111000000\" /><extendInfo key=\"claimApplyAmount\" value=\"1100000000\" /><extendInfo key=\"claimApplyTime\" value=\"20191217103537\" /><extendInfo key=\"reportChannel\" value=\"1\" /><extendInfos key=\"claimPayeeList\"></extendInfos><extendInfo key=\"claimerAccountType\" value=\"1\" /><extendInfo key=\"claimerCertName\" value=\"中国扶贫基金会\" /><extendInfo key=\"claimerCertNo\" value=\"422969520985414\" /><extendInfo key=\"claimerCertType\" value=\"10\" /><extendInfo key=\"claimerPhone\" value=\"13789076543\" /><extendInfo key=\"claimerUid\" value=\"2088301591076581\" /><extendInfo key=\"outBizNo\" value=\"3a4d25b5-7849-47ac-a8c4-4f70018fed09\" /><extendInfo key=\"insRisk\" value=\"\"/><extendInfo key=\"bizData\" value=\"{'confirmed_hospital':'浙江大学医学院附属第一医院'}\"/><extendInfos key=\"attachments\"><list><item><extendInfo key=\"status\" value=\"0\" /><extendInfo key=\"description\" value=\"身份证正面\" /><extendInfo key=\"name\" value=\"身份证\" /><extendInfo key=\"path\" value=\"attachment/insClaim/20191022/201910221100300609290500772923/vaugUvqrQEqa8pv5t3wCIAAAACMAARAD-1571736225555.jpg\" /><extendInfo key=\"type\" value=\"2\" /></item><item><extendInfo key=\"status\" value=\"0\" /><extendInfo key=\"description\" value=\"银行卡\" /><extendInfo key=\"name\" value=\"银行卡\" /><extendInfo key=\"path\" value=\"attachment/insClaim/20191022/201910221100300609290500772923/OYFAf-x2dTtSqGbDAEichZIKwAAACMAARAD-1571736199080.jpg\" /><extendInfo key=\"type\" value=\"14\" /></item><item><extendInfo key=\"status\" value=\"0\" /><extendInfo key=\"description\" value=\"病历本记录\" /><extendInfo key=\"name\" value=\"病历本记录\" /><extendInfo key=\"path\" value=\"attachment/insClaim/20191022/201910221100300609290500772923/OYFAf-x2dTtSqGbDAEichZIKwAAACMAARAD-1571736199080.jpg\" /><extendInfo key=\"type\" value=\"14\" /></item></list></extendInfos></actionContext></body>";
	//		public static String singure = "SBzektO0HON7o2ZIEIueUXUeWEilkglT5XfcUg2aK0hVIQcB0m3qHZRr890e0bHT2Ql7JjouOosJOphafOO2+ybnrSP7wESLgNDygA0mIoodWNhxa1IGbJSZhbDOpD+JQBkBwL9vU5AN9D9Ib1mim4VVafNG8HxrBgd7gqg8EGj+GCSRLHzBSApcU7LcHgSdrfN4MPWtghwe5TKB8SqwoXWAv4oNK7L9p4ze1yKUYCTVR8EkS/BGt0DnDN4bZB2H63NfiJeuKEqamq/ASSmVog6ZsuP6bmtFhpErzqS4ozTKGtNzKDuvAHizlp4NIdgqjW8oSk8Nrv+xwZ4ugOGsqQ==";

	//		public static final String RSA_publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqfuP7kHYZbFabUdrsIfRZAtSjj9BC8G5DA3CrcTNiOch81/2kkqSDAOEjATFrvBC+XiO3TEc5Rc2/oZ9vLF4p7tI30fkLzx8Cy1BOYetnyCBdG49ZiFTmU3y5gvpgPFaSBwtpIzwpo32tGqd9LGlILjKFJ8NWK0M59ilXaLL5ckYMsErtBsK6ItDZUGfQdcIp8eLhRa557+DlOvRbu8CRJRL0tW0ASW9kWqCWWLrQHVLspl8ieb2v6vfeK94YH5stn+pwKUzT23kEfjwpU1R9TyD7ySOielwHOECdbRbrKk7OAw781U5X3k0sOeNU5Nw6xMNJzuv8ftBY7hJBiRAkwIDAQAB";
	////				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqfuP7kHYZbFabUdrsIfRZAtSjj9BC8G5DA3CrcTNiOch81/2kkqSDAOEjATFrvBC+XiO3TEc5Rc2/oZ9vLF4p7tI30fkLzx8Cy1BOYetnyCBdG49ZiFTmU3y5gvpgPFaSBwtpIzwpo32tGqd9LGlILjKFJ8NWK0M59ilXaLL5ckYMsErtBsK6ItDZUGfQdcIp8eLhRa557+DlOvRbu8CRJRL0tW0ASW9kWqCWWLrQHVLspl8ieb2v6vfeK94YH5stn+pwKUzT23kEfjwpU1R9TyD7ySOielwHOECdbRbrKk7OAw781U5X3k0sOeNU5Nw6xMNJzuv8ftBY7hJBiRAkwIDAQAB";
	//		public static final String RSA_privateKey = "";
	//		public static String message = "<head><version>1.0</version><function>ant.bxcloud.health.report.sync</function><transTime>20191218180755</transTime><transTimeZone>UTC+8</transTimeZone><reqMsgId>20191218110400030005200091812586758965</reqMsgId><format>XML</format><signType>RSA</signType><asyn>0</asyn><cid>TPCX</cid></head><body><instSerailNo>$instInfoSerialNo</instSerailNo><outBizNo>20191218110400030091200028247523</outBizNo><reportNo>201912171100300605890500816257</reportNo><outReportNo></outReportNo><policies><policy><policyNo>19111750228796110149</policyNo><outPolicyNo></outPolicyNo></policy></policies><action>1</action><actionTime>20191218180755</actionTime><actionDesc></actionDesc><actionContext><extendInfo key=\\\"accidentDesc\\\" value=\\\"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh\\\" /><extendInfo key=\\\"accidentPersonCertName\\\" value=\\\"卜滢馥\\\" /><extendInfo key=\\\"accidentPersonCertNo\\\" value=\\\"110103198511258027\\\" /><extendInfo key=\\\"accidentPersonCertType\\\" value=\\\"10\\\" /><extendInfo key=\\\"accidentPersonPhone\\\" value=\\\"\\\" /><extendInfo key=\\\"accidentPersonUid\\\" value=\\\"\\\" /><extendInfo key=\\\"accidentTime\\\" value=\\\"20191111000000\\\" /><extendInfo key=\\\"claimApplyAmount\\\" value=\\\"1100000000\\\" /><extendInfo key=\\\"claimApplyTime\\\" value=\\\"20191217103537\\\" /><extendInfo key=\\\"reportChannel\\\" value=\\\"1\\\" /><extendInfos key=\\\"claimPayeeList\\\"></extendInfos><extendInfo key=\\\"claimerAccountType\\\" value=\\\"1\\\" /><extendInfo key=\\\"claimerCertName\\\" value=\\\"中国扶贫基金会\\\" /><extendInfo key=\\\"claimerCertNo\\\" value=\\\"422969520985414\\\" /><extendInfo key=\\\"claimerCertType\\\" value=\\\"10\\\" /><extendInfo key=\\\"claimerPhone\\\" value=\\\"13789076543\\\" /><extendInfo key=\\\"claimerUid\\\" value=\\\"2088301591076581\\\" /><extendInfo key=\\\"outBizNo\\\" value=\\\"3a4d25b5-7849-47ac-a8c4-4f70018fed09\\\" /><extendInfo key=\\\"insRisk\\\" value=\\\"\\\"/><extendInfo key=\\\"bizData\\\" value=\\\"{'confirmed_hospital':'浙江大学医学院附属第一医院'}\\\"/><extendInfos key=\\\"attachments\\\"><list><item><extendInfo key=\\\"status\\\" value=\\\"0\\\" /><extendInfo key=\\\"description\\\" value=\\\"身份证正面\\\" /><extendInfo key=\\\"name\\\" value=\\\"身份证\\\" /><extendInfo key=\\\"path\\\" value=\\\"attachment/insClaim/20191022/201910221100300609290500772923/vaugUvqrQEqa8pv5t3wCIAAAACMAARAD-1571736225555.jpg\\\" /><extendInfo key=\\\"type\\\" value=\\\"2\\\" /></item><item><extendInfo key=\\\"status\\\" value=\\\"0\\\" /><extendInfo key=\\\"description\\\" value=\\\"银行卡\\\" /><extendInfo key=\\\"name\\\" value=\\\"银行卡\\\" /><extendInfo key=\\\"path\\\" value=\\\"attachment/insClaim/20191022/201910221100300609290500772923/OYFAf-x2dTtSqGbDAEichZIKwAAACMAARAD-1571736199080.jpg\\\" /><extendInfo key=\\\"type\\\" value=\\\"14\\\" /></item><item><extendInfo key=\\\"status\\\" value=\\\"0\\\" /><extendInfo key=\\\"description\\\" value=\\\"病历本记录\\\" /><extendInfo key=\\\"name\\\" value=\\\"病历本记录\\\" /><extendInfo key=\\\"path\\\" value=\\\"attachment/insClaim/20191022/201910221100300609290500772923/OYFAf-x2dTtSqGbDAEichZIKwAAACMAARAD-1571736199080.jpg\\\" /><extendInfo key=\\\"type\\\" value=\\\"14\\\" /></item></list></extendInfos></actionContext></body>";
	////				"<head><version>1.0</version><function>ant.bxcloud.health.report.sync</function><transTime>20191218180755</transTime><transTimeZone>UTC+8</transTimeZone><reqMsgId>20191218110400030005200091812586758965</reqMsgId><format>XML</format><signType>RSA</signType><asyn>0</asyn><cid>TPCX</cid></head><body><outBizNo>20191218110400030091200028247523</outBizNo><reportNo>201912171100300605890500816257</reportNo><outReportNo></outReportNo><policies><policyNo>19111750228796110149</policyNo><outPolicyNo></outPolicyNo></policies><action>1</action><actionTime>20191218180755</actionTime><actionDesc></actionDesc><actionContext><extendInfo key=\"claimerAccountType\" value=\"1\"/><extendInfo key=\"claimerCertName\" value=\"中国扶贫基金会\"/><extendInfo key=\"claimerCertNo\" value=\"422969520985414\"/><extendInfo key=\"claimerCertType\" value=\"10\"/><extendInfo key=\"claimerPhone\" value=\"13789076543\"/><extendInfo key=\"claimerUid\" value=\"2088301591076581\"/><extendInfo key=\"outBizNo\" value=\"3a4d25b5-7849-47ac-a8c4-4f70018fed09\"/><extendInfo key=\"insRisk\" value=\"\"/><extendInfo key=\"bizData\" value=\"{'confirmed_hospital':'浙江大学医学院附属第一医院'}\"/><extendInfos><key>attachments</key><list><item><extendInfo key=\"status\" value=\"0\"/><extendInfo key=\"description\" value=\"身份证正面\"/><extendInfo key=\"name\" value=\"身份证\"/><extendInfo key=\"path\" value=\"attachment/insClaim/20191022/201910221100300609290500772923/vaugUvqrQEqa8pv5t3wCIAAAACMAARAD-1571736225555.jpg\"/><extendInfo key=\"type\" value=\"2\"/></item><item><extendInfo key=\"status\" value=\"0\"/><extendInfo key=\"description\" value=\"银行卡\"/><extendInfo key=\"name\" value=\"银行卡\"/><extendInfo key=\"path\" value=\"attachment/insClaim/20191022/201910221100300609290500772923/OYFAf-x2dTtSqGbDAEichZIKwAAACMAARAD-1571736199080.jpg\"/><extendInfo key=\"type\" value=\"14\"/></item><item><extendInfo key=\"status\" value=\"0\"/><extendInfo key=\"description\" value=\"病历本记录\"/><extendInfo key=\"name\" value=\"病历本记录\"/><extendInfo key=\"path\" value=\"attachment/insClaim/20191022/201910221100300609290500772923/OYFAf-x2dTtSqGbDAEichZIKwAAACMAARAD-1571736199080.jpg\"/><extendInfo key=\"type\" value=\"14\"/></item></list></extendInfos></actionContext></body>";
	//		public static String singure = "SBzektO0HON7o2ZIEIueUXUeWEilkglT5XfcUg2aK0hVIQcB0m3qHZRr890e0bHT2Ql7JjouOosJOphafOO2+ybnrSP7wESLgNDygA0mIoodWNhxa1IGbJSZhbDOpD+JQBkBwL9vU5AN9D9Ib1mim4VVafNG8HxrBgd7gqg8EGj+GCSRLHzBSApcU7LcHgSdrfN4MPWtghwe5TKB8SqwoXWAv4oNK7L9p4ze1yKUYCTVR8EkS/BGt0DnDN4bZB2H63NfiJeuKEqamq/ASSmVog6ZsuP6bmtFhpErzqS4ozTKGtNzKDuvAHizlp4NIdgqjW8oSk8Nrv+xwZ4ugOGsqQ==";
	////				"SBzektO0HON7o2ZIEIueUXUeWEilkglT5XfcUg2aK0hVIQcB0m3qHZRr890e0bHT2Ql7JjouOosJOphafOO2+ybnrSP7wESLgNDygA0mIoodWNhxa1IGbJSZhbDOpD+JQBkBwL9vU5AN9D9Ib1mim4VVafNG8HxrBgd7gqg8EGj+GCSRLHzBSApcU7LcHgSdrfN4MPWtghwe5TKB8SqwoXWAv4oNK7L9p4ze1yKUYCTVR8EkS/BGt0DnDN4bZB2H63NfiJeuKEqamq/ASSmVog6ZsuP6bmtFhpErzqS4ozTKGtNzKDuvAHizlp4NIdgqjW8oSk8Nrv+xwZ4ugOGsqQ==";

	public static final String RSA_publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArFew8TOTshxWXnEGJ7LS/IMw9J1g79+495POZDqYLxXVlo9PTl6x/vH3o2mDisgdbS80FjPBP3Dg+TSNwUzVl6IyhzHakpNvUTSZm5+txCtmmcqH7n+3lZtUs/OytiFoAU0cxKrR+f/sS0YtQGa8kgCk8dcwShdigTopKAeoS2tmMvs9kafIw+9fXbNT9QCVj28u34YAh23JJtUsE5jJPT2sqaq24W2wJy7QcTRdH3SW9QOEk4L+/hreTa/HpG78O3AODcRhneC+o5+MlvZlGqojghU5twac8mu+fhRIMSbm2EHXCY2aBeGVt3oJvc/DmtNzbdnLRxiAFh9ZgusDlwIDAQAB";
	public static final String RSA_privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsV7DxM5OyHFZecQYnstL8gzD0nWDv37j3k85kOpgvFdWWj09OXrH+8fejaYOKyB1tLzQWM8E/cOD5NI3BTNWXojKHMdqSk29RNJmbn63EK2aZyofuf7eVm1Sz87K2IWgBTRzEqtH5/+xLRi1AZrySAKTx1zBKF2KBOikoB6hLa2Yy+z2Rp8jD719ds1P1AJWPby7fhgCHbckm1SwTmMk9PaypqrbhbbAnLtBxNF0fdJb1A4STgv7+Gt5Nr8ekbvw7cA4NxGGd4L6jn4yW9mUaqiOCFTm3Bpzya75+FEgxJubYQdcJjZoF4ZW3egm9z8Oa03Nt2ctHGIAWH1mC6wOXAgMBAAECggEAbNaEXA8iex0CPmpKZM++BtIjHJg3G8XB3jht40ZNzY6QcKDYiLms10CgD4cXF5IBY26oCk1tAU+cBEV4MFcBnN1F47NMQbzyU+pQB8PzzStLhFwvNCNcIRMTjwGcHwFB2XCFdn4aVTOWWoNbGIzJEbtub3xidqgGAW5lh24ujlGbDPTe8R51+3GY0JRR4az3+r79AMr1in9qlufuyVcStfphAlDh77ffXbKaxMkTvjNYuOFraIcJuuSXfoR14TlYMwjJQvLt9jfQ/i7ztHCYHfGVU4MFnlMxXdmLbMXlTiDJlB4T/Ruxe7p7axPttouQ53hUoA2LDw+EVcBRilsOAQKBgQD9ya0zy4sMyZdJ9KCrFOiCseZ6OSRcTh7d2xiiWtcKGWC+RZ17a0IZv9zeLjVQ9xnbP+1tzPe1mjcWNjEwfejdrud2MXRkURDbOoeow4tETNm4BZnHIceBUNt3JdxyMnN/0JvTc1t6kEHFWol4ZVZHw9XjUDeGBuXITmuyEyO5VwKBgQCt2EVUbmt3mv44lFBf2ddwUM+Xbn2cEyz96WxKeYGRI6Izg6KFT6lN+cCvr6lp+1Sg9d72kypo7HLFxYa6WOCqEFNGBHd4oRINSRQEwrD+nst54QeG3Nfjb9CylaScft+XsNUyAblkqjmQDOFfIT5hOVSHVIWTbhoz7MkCpFNfwQKBgQChwmYvWVEruWJAtTejVBRPGW06Ain226Q47GlrMlNWEI7lOCIEhH0BRJVW2EULcjR81uZS0g4hJkZuVeRMcDR49/54C1WQi0sfMNM+8kRWKFWtQYSetB0hg5QfAXbdIuMTWLe5sxFfsYr6a5arsoun+l7eIEcDmmnjnnhubFycyQKBgQCAy5wBw5TnFfOGo4swmEoesiA6hueUvDg3HbrXKlIt34KKO9oqfixm0sRI5RHUrRmz7rTzZXCkL5mQoJkCclpwZIvtcd25h0vCgAPOyxcqbSc+8tMKvY9Mt0idJnTAZNbuoVoyJEjgJaVj7ccDHbQDf9/6K0qTQZ00HxeFV41VgQKBgDKV+ADdjGcQ+of3lH5+OcHhFvFLVS473438NOjZHMBGCQIvTeP2wAaamnr3QKWTcq1Qd2/tbPTxZvwzybIRtYODigsGmgQaKTqPak+GHnyIqAeeGZdPT1qFBo3b04ZbY1DvFBqHGuWdJDmvXGHt5Pff8U+jkwqSgCGt3H+IR8pL";
	//		public static final String RSA_publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqfuP7kHYZbFabUdrsIfRZAtSjj9BC8G5DA3CrcTNiOch81/2kkqSDAOEjATFrvBC+XiO3TEc5Rc2/oZ9vLF4p7tI30fkLzx8Cy1BOYetnyCBdG49ZiFTmU3y5gvpgPFaSBwtpIzwpo32tGqd9LGlILjKFJ8NWK0M59ilXaLL5ckYMsErtBsK6ItDZUGfQdcIp8eLhRa557+DlOvRbu8CRJRL0tW0ASW9kWqCWWLrQHVLspl8ieb2v6vfeK94YH5stn+pwKUzT23kEfjwpU1R9TyD7ySOielwHOECdbRbrKk7OAw781U5X3k0sOeNU5Nw6xMNJzuv8ftBY7hJBiRAkwIDAQAB";
	//		 public static final String RSA_privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCp+4/uQdhlsVptR2uwh9FkC1KOP0ELwbkMDcKtxM2I5yHzX/aSSpIMA4SMBMWu8EL5eI7dMRzlFzb+hn28sXinu0jfR+QvPHwLLUE5h62fIIF0bj1mIVOZTfLmC+mA8VpIHC2kjPCmjfa0ap30saUguMoUnw1YrQzn2KVdosvlyRgywSu0Gwroi0NlQZ9B1winx4uFFrnnv4OU69Fu7wJElEvS1bQBJb2RaoJZYutAdUuymXyJ5va/q994r3hgfmy2f6nApTNPbeQR+PClTVH1PIPvJI6J6XAc4QJ1tFusqTs4DDvzVTlfeTSw541Tk3DrEw0nO6/x+0FjuEkGJECTAgMBAAECggEBAIcqO3Q4tat/kKlO3oocJdvIyRfFoqKHo+66znBBCzLun+eYCkiftWyKK47viIYoFQms3OV0VUax5BAWv8sY0BmIalTqJL+O+BAnJzNo+R2MyoPb2UTqAUDpY9mb5UycHq8ygPTVAdNfFaq3EO1viR/w8Pfe1c0KpjWB51UCy+Hmbzz4twcON5r1ilNFdBt6w/Sntfsjf/S1vdcLx8ZNiCAPd1LHHZxtHlZs0IdFrPBtgl55c4Cl0ntFT/g7RQOjKVuObnPN84FXf23KbxRhMKVoRhKT8Q5PVjNqqMAYjDjfKrHF6Yw0IAR9Z6+A/xnos5DDUG+OM6c8ea9vJ6B7VykCgYEA1+Q8DVEJcSi+nyrkcjKccn/jRW7GeKXklxkACtXiWAJ/1THVybRM3cfbYSEHsIppTJLaBi1f/cvOgNXktrAcYbewNe4ob8EKWWqYrIuEtcRMDXgLu5ALnYGFTKfphSBh1ZfgnuEy/Pa3tj/wHqGHq5RK2GTPjDTChhovsw42Ni0CgYEAyY/owceOPp+QQICStuRZYBZ+XOVsQ9SPhUaKL9c6qaanTtUULAzONeOl6ysmnI+NE2rPbsQxKrrBEC/lCWfV9L0X4X7Ouc2790pIIw5P1WwxbowSpqo5hl9dH8aBeshAo7+JuUl/hDh4QktVjRHAF80bkEw8meyqOmVlwG2bSb8CgYAyXxsv1DeKwoHvazeP+YUNJg+l9Jm0LqiuJHQhExRTiom++XizLjE9EdN6zxUXOMQmzKC4DkA2XCYbY0yQ33hPyGcBvkaLBJRgloF2yLq3GkzQW7EJGyvKnRy37PmMSSjqiBwtlceqw/nLORHSY8fe3aO055iRUwIL/fIhKfC2JQKBgQClzYFz1cnG7c7loF4PoGt8xUQQ+pBCg9nDkjEeBXg2EebSzCiZy7bdUXQsrQRICTXNYTFdNnoTYihqPluzjvzLI7k/PuaipQAX/by1SZKWRzeqbgLxollLlaqu9sWP0KaLjIWoKzN/+kvCjOHE93MCoTApVO0M2Ud2Xe6DiiYRVQKBgQC6wTn8Epni+ekrWG0s6kkcWxGuN99PLPM7VfwtEr80GkC+sCJ6cM+ZeZdQadiCPzCFJUNwogD9fsnjzrPex4h68+Eup4nTBvdEJRwYa2PU5dviYrbQ8jrSACebAfX4xYIUgfQQM/bHA0YblGYguNFr1aamu6JqQv+JALfhSPCq5Q==";
	public static String message = "<head><version>1.0</version><function>ant.bxcloud.health.report.sync</function><transTime>1577093360420</transTime><transTimeZone>UTC+8</transTimeZone><reqMsgId>20191220110400030005201048280990422448</reqMsgId><format>XML</format><signType>RSA</signType><asyn>0</asyn><cid>TPCX</cid></head><body><success>1</success><errorCode>200</errorCode><errorMessage>success!</errorMessage><reportNo>201912201100300600410500822229</reportNo></body>";
	public static String singure = "RcUquI6p8simEPOWIHzJFF0i3YFp3180/7iUtOCmqTt3vyI8/ess4wfAgan3Dj9WEY3UWtnE0GbAN3dRf4//kX/J4ihsbN9euSf5h4Gi9FwBHHAagSjWY0Vm4QLXLBdhPmKkir58sEChu3XQQ3KaJ4g4UFZ8tltGsZPZladLnFB3jDmndGEJWZVvgYTcqqg6bLwxIuQkKlmXbJUgaLuNmgV6PHuzRWCyPkF7fwIUM7fndyNvTtT9WPVpbgJrXBoaNlYc/v4Xqoc3VJJt/QzPFm6oDs2WCRw8NTnWbwE0k/Svtjzone7FKjQcbY86EzZRFbs3pxkY359l75V+Jzx4uQ==";

	String str = "PGhlYWQ+PHZlcnNpb24+MS4wPC92ZXJzaW9uPjxmdW5jdGlvbj5hbnQuYnhjbG91ZC5oZWFsdGgucmVwb3J0LnN5bmM8L2Z1bmN0aW9uPjx0cmFuc1RpbWU+MTU3NzA5MzM2MDQyMDwvdHJhbnNUaW1lPjx0cmFuc1RpbWVab25lPlVUQys4PC90cmFuc1RpbWVab25lPjxyZXFNc2dJZD4yMDE5MTIyMDExMDQwMDAzMDAwNTIwMTA0ODI4MDk5MDQyMjQ0ODwvcmVxTXNnSWQ+PGZvcm1hdD5YTUw8L2Zvcm1hdD48c2lnblR5cGU+UlNBPC9zaWduVHlwZT48YXN5bj4wPC9hc3luPjxjaWQ+VFBDWDwvY2lkPjwvaGVhZD48Ym9keT48c3VjY2Vzcz4xPC9zdWNjZXNzPjxlcnJvckNvZGU+MjAwPC9lcnJvckNvZGU+PGVycm9yTWVzc2FnZT5zdWNjZXNzITwvZXJyb3JNZXNzYWdlPjxyZXBvcnRObz4yMDE5MTIyMDExMDAzMDA2MDA0MTA1MDA4MjIyMjk8L3JlcG9ydE5vPjwvYm9keT4=";
	public static void main(String[] args) throws Exception {
		String miwen = new String(Base64.encodeBase64(message.getBytes("utf-8")));
		String sign = RSA.sign(miwen, RSA_privateKey);
		System.out.println("sign:" + sign);
		boolean flag = checkSign(miwen, singure, RSA_publicKey);
		System.out.println(flag);
	}

	/**
	 * 生成密钥对
	 */
	public static Map<String, String> generateKeyPair() throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = new String(Base64.encodeBase64(publicKeyBytes), encoding);
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = new String(Base64.encodeBase64(privateKeyBytes), encoding);

		Map<String, String> map = new HashMap<String, String>();
		map.put("publicKey", pub);
		map.put("privateKey", pri);
		RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		byte[] deBase64Value = Base64.encodeBase64(b);
		String retValue = new String(deBase64Value);
		map.put("modulus", retValue);
		return map;
	}

	/**
	 * 加密方法 source： 源数据
	 */
	public static String encrypt(String source, String publicKey) throws Exception {
		Key key = getPublicKey(publicKey);
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		return new String(Base64.encodeBase64(b1), encoding);
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public static String decrypt(String cryptograph, String privateKey) throws Exception {
		Key key = getPrivateKey(privateKey);
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	/**
	 * 得到公钥
	 * 
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * 
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public static String sign(String content, String privateKey) {
		String charset = encoding;
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes()));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance("SHA256withRSA");

			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean checkSign(String content, String sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decodeBase64(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature.getInstance("SHA256withRSA");

			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));

			boolean bverify = signature.verify(Base64.decodeBase64(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}



}
