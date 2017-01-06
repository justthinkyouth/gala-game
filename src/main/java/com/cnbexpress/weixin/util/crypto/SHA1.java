package com.cnbexpress.weixin.util.crypto;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA1 {

	/**
	 * 串接arr参数，生成sha1 digest
	 * @param arr
	 * @return
	 */
	public static String gen(String... arr) throws NoSuchAlgorithmException{
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (String a:arr) {
			sb.append(a);
		}
		return DigestUtils.sha1Hex(sb.toString());
	}
}
