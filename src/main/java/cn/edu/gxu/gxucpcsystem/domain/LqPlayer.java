package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LqPlayer {
    private Integer id;
    private String userName;
    private String userSex;
    private String userCourse;
    private String userClass;
    private String userQQ;
    private String userMail;
    private String userPhone;
    private String userId;
    private Integer contestId;
    private Integer isDiscount;
    private String imgURI;
}
