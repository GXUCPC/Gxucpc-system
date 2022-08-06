package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 8/6/2022 4:13 PM
 * @Version: 1.0
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    String userName;
    String informationId;
    String userSex;
    String userCourse;
    String userClass;
    String userQQ;
    String userMail;
    int contestId;
    boolean star;
    boolean group;
    String userPhone;
    int userId;
}
