package cn.edu.gxu.gxucpcsystem.utils;
/**
 * @author MaoMao
 * @Description MD5加密算法
 * @create 2022-06-22 7:59 PM
 */
import java.security.MessageDigest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MD5Utils {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        return getString(byteArray, md5);
    }
    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
    /**
     * 判断输入的密码和数据库中保存的MD5密码是否一致
     * @param inputPassword 输入的密码
     * @param md5DB 数据库保存的密码
     * @return
     */
    public static boolean passwordIsTrue(String inputPassword,String md5DB) {
        String md5 = string2MD5(inputPassword);
        return md5DB.equals(md5);
    }

public static String string2MD5(byte[] byteArray) {
    MessageDigest md5 = null;
    try {
        md5 = MessageDigest.getInstance("MD5");
    } catch (Exception e) {
        log.info(e.toString());
        e.printStackTrace();
        return "";
    }
    return getString(byteArray, md5);
}
    private static String getString(byte[] byteArray, MessageDigest md5) {
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}