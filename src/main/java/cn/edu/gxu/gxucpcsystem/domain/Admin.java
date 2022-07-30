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
}
