package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sct
 * @date 2022/8/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Text {
    private Integer id;
    private String type;
    private String title;
    private String author;
    private Long time;
    private String content;
}
