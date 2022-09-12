package cn.edu.gxu.gxucpcsystem.domain;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty(value = {"表单号"}, index = 0)
    int informationId;
    @ExcelProperty(value = {"姓名"}, index = 1)
    String userName;
    @ExcelProperty(value = {"性别"}, index = 3)
    String userSex;
    @ExcelProperty(value = {"专业"}, index = 4)
    String userCourse;
    @ExcelProperty(value = {"班级"}, index = 5)
    String userClass;
    @ExcelProperty(value = {"QQ"}, index = 6)
    String userQQ;
    @ExcelProperty(value = {"邮箱"}, index = 7)
    String userMail;
    int contestId;
    @ExcelProperty(value = {"是否打星"}, index = 8)
    boolean star;
    @ExcelProperty(value = {"组别"}, index = 9)
    boolean group;
    String userPhone;
    @ExcelProperty(value = {"学号"}, index = 2)
    String userId;
}
