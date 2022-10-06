package cn.edu.gxu.gxucpcsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long createTime;
    private Long lastLogin;
    private String email;
    @JsonIgnore
    private boolean isChangePassword;
    /**
     * 数据库中用户名长度
     */
    @JsonIgnore
    private final int usernameLength = 30;


    /**
     * 数据库中邮件的长度
     */
    @JsonIgnore
    private final int emailLength = 40;

    /**
     * 数据库中真实姓名的长度
     */
    @JsonIgnore
    private final int realNameLength = 40;


    /**
     * 检验数据完整性(用户名、密码非空，邮箱格式正确)
     *
     * @return
     */
    public String checkIntegrityLogin() {
        if (username == null || username.isEmpty()) {
            return "用户名为空";
        }
        if (username.length() > usernameLength) {
            return "用户名过长";
        }
        if(isChangePassword) {
            if (password == null || password.isEmpty()) {
                return "密码为空";
            }
        }
        return null;
    }
    public String checkIntegrityCreate() {

        if(email == null || email.isEmpty()) {
            return "邮箱为空";
        }
        if (!(email.contains("@") && email.contains(".") && email.lastIndexOf(".") > email.lastIndexOf("@"))) {
            return "邮箱格式错误";
        }
        if (email.length() > emailLength) {
            return "邮件过长";
        }
        if(userType == null || userType.toString().isEmpty()) {
            return "用户权限为空";
        }
        if(!("Admin".equals(userType.toString()) || "Super Admin".equals(userType.toString()))) {
            return "用户权限错误";
        }
        if(realName == null || realName.isEmpty()) {
            return "用户真实姓名为空";
        }
        if(realName.length() > realNameLength) {
            return "用户真实姓名过长";
        }

        return checkIntegrityLogin();
    }
    public String checkIntegrityUpdate(boolean isChangePassword) {
        if(id == null || id < 0) {
            return "编号错误";
        }
        this.isChangePassword = isChangePassword;
        return checkIntegrityCreate();
    }
}
