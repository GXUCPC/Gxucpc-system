package cn.edu.gxu.gxucpcsystem.domain;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcMessage {
    @JSONField(name = "label")
    private String label;
    @JSONField(name = "num_judged")
    private Integer numJudge;
    @JSONField(name = "num_pending")
    private Integer numPending;
    @JSONField(name = "solved")
    private Boolean solved;
    @JSONField(name = "first_to_solve")
    private Boolean firstToSolve;
    @JSONField(name = "time")
    private Integer time;

}
