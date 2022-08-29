package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sct
 * @date 2022/7/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contest {
    private Integer id;
    private String name;
    private Long signUpBeginTime;
    private Long signUpEndTime;
    private String email;
    private String smtpPassword;
    private Long contestBeginTime;
    private Long contestEndTime;
    private Boolean isDownload;
}
