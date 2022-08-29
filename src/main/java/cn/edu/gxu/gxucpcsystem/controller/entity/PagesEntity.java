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
/**
 * 分页实体
 * TableData: 页面数据
 * total: 数据总量
 */
public class PagesEntity {
    private Object tableData;
    private Integer total;
}
