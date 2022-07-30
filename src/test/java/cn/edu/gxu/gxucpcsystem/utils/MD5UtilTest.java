package cn.edu.gxu.gxucpcsystem.utils;

import org.junit.jupiter.api.Test;

/**
 * @author Sct
 * @date 2022/7/30
 */
public class MD5UtilTest {
    @Test
    public void MD5CreateTest() {
        System.out.println(MD5Utils.string2MD5("123456"));
    }
}
