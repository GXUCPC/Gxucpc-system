package cn.edu.gxu.gxucpcsystem.utils;

import org.junit.jupiter.api.Test;

/**
 * @author Sct
 * @date 2022/8/2
 */
public class MailTest {
    @Test
    public void test1(){
        MailUtil mailUtil = new MailUtil("sunctao@qq.com", "qndscqjdoedqhggf");
        mailUtil.init();
        try {
            mailUtil.sendHtmlEmail("sct.cn@outlook.com", "test", "<h1>test</h1>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
