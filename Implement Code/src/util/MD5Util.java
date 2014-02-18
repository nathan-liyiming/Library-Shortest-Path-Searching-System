package util;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;


public class MD5Util {

	private static final int SALT_LENGTH = 12;
	private static final String HEX_NUMS_STR = "0123456789ABCDEF";

	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1)
				hex = '0' + hex;
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] hexChars = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
					.indexOf(hexChars[pos + 1]));
		}
		return result;
	}

	public static byte[] getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		random.nextBytes(salt);
		return salt;
	}

	public static String getEncryptedPwd(String password, byte[] salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] pwd = null;
		MessageDigest md = null;
		md = MessageDigest.getInstance("MD5");
		md.update(salt);
		md.update(password.getBytes("UTF-8"));
		byte[] digest = md.digest();
		pwd = new byte[digest.length + SALT_LENGTH];
		System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
		System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
		md.update(pwd);
		return byteToHexString(md.digest());
	}

	public static void addNewStaff(String name, int ID,String email,String password,String position)
			throws NoSuchAlgorithmException, UnsupportedEncodingException,
			FileNotFoundException {
		    byte[] salt = getSalt();
			Connection con = MySQLConnection.connection();
			try {
				Statement state = con.createStatement();
					String query = "INSERT INTO staff VALUES('" + name + "','" + ID
							+ "','" + email + "','" + getEncryptedPwd(password, salt) + "','" + byteToHexString(salt)
							+ "','" + position + "')";
					state.executeUpdate(query);
					state.close();
					con.close();
			} catch (SQLException e) {
				
			}
	}
	
	public static void addNewUser(String name, int ID,String email,String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException,
			FileNotFoundException {
		    byte[] salt = getSalt();
			Connection con = MySQLConnection.connection();
			try {
				Statement state = con.createStatement();
					String query = "INSERT INTO general_user VALUES('" + name + "','" + ID
							+ "','" + email + "','" + getEncryptedPwd(password, salt) + "','" + byteToHexString(salt)
							+ "')";
					state.executeUpdate(query);
					state.close();
					con.close();
			} catch (SQLException e) {
				
			}
	}

	public static boolean validStaffPassword(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException,
			FileNotFoundException {
			Connection con = MySQLConnection.connection();
			try {
				Statement state = con.createStatement();
				String query = "SELECT salt,password FROM staff WHERE name='" + username + "'";
				ResultSet rs = state.executeQuery(query);
				while (rs.next()) {
					if (getEncryptedPwd(password, hexStringToByte(rs.getString("salt")))
							.equals(rs.getString("password")))
						return true;
				}
					state.close();
					con.close();
			} catch (SQLException e) {
				
			}
		return false;
	}
	
	public static boolean validUserPassword(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException,
			FileNotFoundException {
			Connection con = MySQLConnection.connection();
			try {
				Statement state = con.createStatement();
				String query = "SELECT salt,password FROM general_user WHERE name='" + username + "'";
				ResultSet rs = state.executeQuery(query);
				while (rs.next()) {
					if (getEncryptedPwd(password, hexStringToByte(rs.getString("salt")))
							.equals(rs.getString("password")))
						return true;
				}
					state.close();
					con.close();
			} catch (SQLException e) {
				
			}
		return false;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException,
			UnsupportedEncodingException, FileNotFoundException {
		addNewStaff("Staff1",200800000,"staff1@liv.ac.uk","123456","Curator");
		addNewStaff("Staff",20080000,"staff1@liv.ac.uk","123456","Curator");
		System.out.println(validStaffPassword("Staff1", "123456"));
		
		addNewUser("User1",2008654321,"user1@liv.ac.uk","123456");
		System.out.println(validUserPassword("User1", "123456"));
	}
}
