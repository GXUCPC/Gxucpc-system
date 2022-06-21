package cn.edu.gxu.gxucpcsystem.dao.mysql;

import lombok.Data;

/**
 * @author MaoMao
 * @Description 参赛选手数据型
 * @create 2022-06-21 12:59 PM
 */

@Data
public class Player {
    // 用户姓名
    String userName;
    // 用户编号
    int userId;
    boolean userSex;
    int userAge;
    // 比赛名称
    int contestId;
    String userCourse;
    String userClass;
    String userQQ;
    String userMail;
    String P_Group;
    boolean stat;
}
