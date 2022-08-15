package cn.edu.gxu.gxucpcsystem.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Sct
 * @date 2022/7/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagesEntity {
    private Object tableData;
    private Integer total;
}
