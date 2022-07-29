package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MaoMao
 * @Description 管理员数据型
 * @create 2022-06-21 1:11 PM
 */

@Data
@AllArgsConstructor
public class Admin {
    private String userName;
    private String password;
    private Integer privilege;
    private String realName;
}
