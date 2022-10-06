package cn.edu.gxu.gxucpcsystem.utils;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.exception.EmailException;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
/**
 * @author Sct
 * @date 2022/8/2
 */
public class MailUtil {

    // SMTP邮件服务器
    private String HOST;

    // SMTP邮件服务器默认端口
    private String PORT;

    // 是否要求身份认证
    private final String IS_AUTH = "true";


    //发件人邮箱授权码
    private final String AUTHORIZATION_CODE;

    // 发件人
    private final String from;


    private Session session;


    // 初始化邮件服务
    public MailUtil(String from, String authorizationCode) {
        this.from = from;
        this.AUTHORIZATION_CODE = authorizationCode;
    }

    public void init() throws Exception{
        this.PORT = this.getPort(this.from);
        this.HOST = this.getHost(this.from);
        // 初始化连接邮件服务器的会话信息
        Properties props = new Properties();

        props.setProperty("mail.smtp.auth", IS_AUTH);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.host", HOST);

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.transport.protocol", "smtps");
        props.put("mail.smtp.starttls.enable", "true");


        this.session = Session.getInstance(props, new MyAuthenticator(this.from, this.AUTHORIZATION_CODE));

    }
    /**
     * 发送邮件
     *
     * @param to 接收方
     * @param subject 主题
     * @param content 内容
     * @return
     * @throws Exception
     */
    public void sendHtmlEmail(String to, String subject, String content) throws Exception{
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setFrom(new InternetAddress(this.from, "广西大学程序设计竞赛组委会", "UTF-8"));
        message.setSentDate(new Date());
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
        message.setContent(content, "text/html;charset=utf-8");
        message.saveChanges();
        Transport.send(message);
    }


    public String getHost(String username) {
        if(username.contains("163.com")) {
            return "smtp.163.com";
        } else if(username.contains("126.com")) {
            return "smtp.126.com";
        } else if(username.contains("qq.com")) {
            return "smtp.qq.com";
        } else if(username.contains("gmail.com")) {
            return "smtp.gmail.com";
        } else if(username.contains("foxmail.com")) {
            return "smtp.qq.com";
        } else {
            // 无效邮箱异常

            throw new EmailException(Code.RESOURCE_DISABLE, username + "为未适配的邮箱");
        }
    }
    public String getPort(String username) {
        if(username.contains("163.com")) {
            return "465";
        } else if(username.contains("126.com")) {
            return "465";
        } else if(username.contains("qq.com")) {
            return "465";
        } else if(username.contains("gmail.com")) {
            return "465";
        } else if(username.contains("foxmail.com")) {
            return "465";
        } else {
            // 无效邮箱异常
            throw new EmailException(Code.RESOURCE_DISABLE, username + "为未适配的邮箱");
        }
    }


    /**
     * 向邮件服务器提交认证信息
     */
    class MyAuthenticator extends Authenticator {

        private String username;

        private String password;


        public MyAuthenticator(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(username, password);
        }
    }

}
