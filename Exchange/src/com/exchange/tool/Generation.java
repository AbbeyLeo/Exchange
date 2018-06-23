package com.exchange.tool;

import java.util.Date;
import java.util.Random;

public class Generation {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";
	public static final String BLETTERCHARAndNUMBERCHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		System.out.println(generateCaptcha());
	}

	// 生成验证码
	public static String generateCaptcha() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			sb.append(BLETTERCHARAndNUMBERCHAR.charAt(random
					.nextInt(BLETTERCHARAndNUMBERCHAR.length())));
		}
		return sb.toString();
	}

	// 生成验证码标识码
	public static String generateCid() {
		Date date = new Date();
		long times = date.getTime();
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString() + times;
	}
}
