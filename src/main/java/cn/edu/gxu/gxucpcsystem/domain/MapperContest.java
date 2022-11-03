package cn.edu.gxu.gxucpcsystem.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapperContest {
    private Integer id;
    private Integer registerContest;
    private Boolean isSpider;
    private Long updateTime;
    private String url;
    private Integer faultTime;
    private Long contestTime;
    private String name;
}
