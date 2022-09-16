package cn.edu.gxu.gxucpcsystem.utils;

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
    private final String PROTOCOL = "smtp";

    // SMTP邮件服务器
    private String HOST;

    // SMTP邮件服务器默认端口
    private String PORT;

    // 是否要求身份认证
    private final String IS_AUTH = "false";

    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final String IS_ENABLED_DEBUG_MOD = "true";

    //发件人邮箱授权码
    private final String AUTHORIZATION_CODE;

    // 发件人
    private final String from;


    // 初始化连接邮件服务器的会话信息
    private Properties props;

    private Transport transport = null;

    // 初始化邮件服务
    public MailUtil(String from, String authorizationCode) {
        this.from = from;
        this.AUTHORIZATION_CODE = authorizationCode;
    }

    public void init() {
        this.PORT = this.getPort(this.from);
        this.HOST = this.getHost(this.from);
        this.props = new Properties();
        this.props.setProperty("mail.transport.protocol", PROTOCOL);
        this.props.setProperty("mail.smtp.host", HOST);
        this.props.setProperty("mail.smtp.port", PORT);
        this.props.setProperty("mail.smtp.auth", IS_AUTH);
        this.props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD);
//        this.props.setProperty("mail.smtp.ssl.enable", "true");
//        this.props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props, new MyAuthenticator(this.from, this.AUTHORIZATION_CODE));
        try {
            // 获得Transport实例对象
            transport = session.getTransport();
            // 打开连接
            transport.connect(from, AUTHORIZATION_CODE);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
        Session session = Session.getInstance(props, new MyAuthenticator(this.from, this.AUTHORIZATION_CODE));
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setFrom(new InternetAddress(this.from));
        message.setSentDate(new Date());
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
        message.setContent(content, "text/html;charset=utf-8");
        message.saveChanges();
        transport.sendMessage(message, message.getAllRecipients());
    }

    /**
     * 断开连接
     *
     * @throws Exception
     */
    public void close() throws Exception{
        transport.close();
    }

    public String getHost(String username) {
        if(username.contains("163.com")) {
            return "smtp.163.com";
        } else if(username.contains("126.com")) {
            return "smtp.126.com";
        } else if(username.contains("10086.cn")) {
            return "smtp.139.com";
        } else if(username.contains("qq.com")) {
            return "smtp.qq.com";
        } else if(username.contains("gmail.com")) {
            return "smtp.gmail.com";
        } else if(username.contains("foxmail.com")) {
            return "smtp.foxmail.com";
        } else if(username.contains("outlook.com")) {
            return "smtp.office365.com";
        }
        else {
            // TODO 无效邮箱异常
            return null;
        }
    }
    public String getPort(String username) {
        if(username.contains("163.com")) {
            return "25";
        } else if(username.contains("126.com")) {
            return "25";
        } else if(username.contains("10086.cn")) {
            return "25";
        } else if(username.contains("qq.com")) {
            return "25";
        } else if(username.contains("gmail.com")) {
            return "25";
        } else if(username.contains("foxmail.com")) {
            return "25";
        } else if(username.contains("outlook.com")) {
            return "25";
        } else {
            // TODO 无效邮箱异常
            return null;
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
