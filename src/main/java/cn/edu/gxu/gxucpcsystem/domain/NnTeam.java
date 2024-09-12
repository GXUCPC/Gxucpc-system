package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NnTeam {
    private Integer id;
    private String school;
    private String team;
    private String member1;
    private String member2;
    private String member3;
    private String subject;
    private String grade;
    private String email;
    private String teacher;
    private Integer contestId;
}
