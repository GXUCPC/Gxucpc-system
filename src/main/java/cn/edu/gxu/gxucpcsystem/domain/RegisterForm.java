package cn.edu.gxu.gxucpcsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Sirius1145
 * @Date: 2024/10/10/13:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
    private Integer countPerUser;
    private String title;
    private String text;
    private String formItemList;
    private String footer;

    /**
     * 数据库中表单标题的最大长度
     */
    @JsonIgnore
    private final int titleLength = 100;

    /**
     * 数据库中表单说明的最大长度
     */
    @JsonIgnore
    private final int textLength = 500;

    /**
     * 数据库中自定义表格对象的最大长度
     */
    @JsonIgnore
    private final int jsonLength = 65536;

    /**
     * 数据库中文末按钮文本的最大长度
     */
    @JsonIgnore
    private final int footerLength = 30;


    /**
     * 检验数据完整性(用户可填写数量、标题、表单说明、自定义表格对象、文末按钮文本格式正确)
     *
     * @return
     */
    public String checkIntegrityCreate() {
        if (countPerUser < 0) {
            return "每人可填写数量有误";
        }
        if (title == null || title.isEmpty()) {
            return "表单标题为空";
        }
        if (title.length() > titleLength) {
            return "表单标题过长，不得超过100字符";
        }
        if (text.length() > textLength) {
            return "表单说明过长，不得超过500字符";
        }
        if (formItemList == null || formItemList.isEmpty()) {
            return "表单项为空";
        }
        if (formItemList.length() > jsonLength) {
            return "表单项目过多";
        }
        if (footer == null || footer.isEmpty()) {
            return  "按钮文本为空";
        }
        if (footer.length() > footerLength) {
            return "按钮文本过长，不得超过30字符";
        }
        return null;
    }
}

