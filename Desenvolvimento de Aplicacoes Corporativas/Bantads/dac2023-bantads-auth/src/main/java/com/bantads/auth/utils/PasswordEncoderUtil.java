package com.bantads.auth.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderUtil {

	private static final int SALT_LENGTH = 16;

	public static byte[] saltGenerator() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		secureRandom.nextBytes(salt);

		return salt;
	}

	public static String encodePassword(String password, byte[] salt) {
		// with messageDigest
		String passwordDigest = "";

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			passwordDigest = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return passwordDigest;
	}
	
	//gera senha com texto até 12 char
	public static String generatePassword() {
		UUID random = UUID.randomUUID();
		return random.toString().replaceAll("_", "").substring(0, 4);
	}
}