package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sct
 * @Description 管理员数据型
 * @create 2022-06-21 1:11 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private Object userType;
    private String realName;
    private long createTime;
    private long lastLogin;
    private String email;

    /**
     * 数据库中用户名长度
     */
    private final int usernameLength = 30;

    /**
     * 数据库中密码的长度
     */
    private final int passwordLength = 40;

    /**
     * 数据库中邮件的长度
     */
    private final int emailLength = 40;


    /**
     * 检验数据完整性(用户名、密码非空，邮箱格式正确)
     *
     * @return
     */
    String checkIntegrity() {
        if (username.isEmpty()) {
            return "用户名为空";
        }
        if (username.length() > usernameLength) {
            return "用户名过长";
        }
        if (password.isEmpty()) {
            return "密码为空";
        }
        if (password.length() > passwordLength) {
            return "密码过长";
        }
        if (!(email.contains("@") && email.contains(".") && email.lastIndexOf(".") > email.lastIndexOf("@"))) {
            return "邮箱格式错误";
        }
        if (email.length() > emailLength) {
            return "邮件过长";
        }
        return null;
    }
}
