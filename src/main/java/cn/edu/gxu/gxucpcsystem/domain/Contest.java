package cn.edu.gxu.gxucpcsystem.domain;

import lombok.Data;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 7/29/2022 5:15 PM
 * @Version: 1.0
 * @Description: 比赛类型
 */
@Data
public class Contest {
       int contestId;
       java.util.Date startTime;
       java.util.Date endTime;
       String contestName;
       boolean downloadSwitch;
}
