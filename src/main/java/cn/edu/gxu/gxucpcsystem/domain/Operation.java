package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sct
 * @date 2022/8/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private Integer id;
    private String user;
    private Long operationTime;
    private String details;
}
