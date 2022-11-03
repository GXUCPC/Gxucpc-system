package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private List<AcMessage> acMessages;
    private Integer contestId;
    private String content;
    private Integer rank;
    private String name;
    private Integer numSolved;
    private Integer totalTime;
    private Integer teamId;
}
