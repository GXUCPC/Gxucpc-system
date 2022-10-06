package cn.edu.gxu.gxucpcsystem.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer informationId;
    private String userName;
    private String userSex;
    private String userCourse;
    private String userClass;
    private String userQQ;
    private String userMail;
    private Integer contestId;
    private Boolean star;
    private Boolean group;
    private String userId;
    private String userPhone;
    private String key;
    private String remark;
    @JsonIgnore
    private String clientNo;

    @JsonIgnore
    private final int usernameLength = 30;
    @JsonIgnore
    private final int userClassLength = 64;
    @JsonIgnore
    private final int userMailLength = 64;
    @JsonIgnore
    private final String[] courses = {
            "机械工程学院",
            "电气工程学院",
            "土木建筑工程学院",
            "化学化工学院",
            "资源环境与材料学院",
            "轻工与食品工程学院",
            "计算机与电子信息学院",
            "海洋学院",
            "生命科学与技术学院",
            "农学院",
            "动物科学技术学院",
            "林学院",
            "数学与信息科学学院",
            "物理科学与工程技术学院",
            "文学院",
            "新闻与传播学院",
            "外国语学院",
            "艺术学院",
            "公共管理学院",
            "工商管理学院",
            "法学院",
            "马克思主义学院",
            "体育学院",
            "医学院",
            "继续教育学院",
            "国际学院",
            "经济学院/中国—东盟金融合作学院",
            "非本校生"
    };

    public String checkIntegrityCreate() {
        if (userName == null || userName.isEmpty() || userName.length() > usernameLength) {
            return "姓名错误";
        }
        if (!("男".equals(userSex) || "女".equals(userSex))) {
            return "性别错误";
        }
        if (userCourse == null) {
            return "学院错误";
        }
        boolean isSafeCourse = false;
        for (String cours : courses) {
            if(cours.equals(userCourse)) {
                isSafeCourse = true;
               break;
            }
        }
        if(!isSafeCourse) {
            return "学院错误";
        }
        if(userClass == null || userClass.isEmpty() || userClass.length() > userClassLength) {
            return "班级错误";
        }
        if(userQQ == null || userQQ.length() < 5 || userQQ.length() > 11) {
            return "QQ错误";
        }
        if(userMail == null || userMail.isEmpty() || userMail.length() > userMailLength || !(userMail.contains("@") && userMail.contains(".") && userMail.lastIndexOf("@") < userMail.lastIndexOf("."))) {
            return "邮箱错误";
        }
        if(userId == null || userId.length() != 10) {
            return "学号错误";
        }
        if(userPhone == null || userPhone.length() != 11) {
            return "手机号错误";
        }
        if(key == null || key.isEmpty() || key.length() > 20) {
            return "密钥错误";
        }
        if(remark != null && remark.length() > 500) {
            return "备注过长";
        }
        if(clientNo == null || clientNo.isEmpty()) {
            return "无效的客户端";
        }
        return null;
    }

    public String checkIntegrityUpdate() {
        if(informationId < 0) {
            return "编号错误";
        }
        return checkIntegrityCreate();
    }
}

