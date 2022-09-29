package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sct
 * @date 2022/8/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private Integer id;
    private String emailData;
    private String emailSubject;

    public String checkIntegrityCreate() {
        if(emailData == null || emailData.isEmpty()) {
            return "邮件内容为空";
        }
        if(emailSubject == null || emailSubject.isEmpty()) {
            return "邮件主题为空";
        }
        return null;
    }
}
