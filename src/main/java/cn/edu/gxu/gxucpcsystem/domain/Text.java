package cn.edu.gxu.gxucpcsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /**
     * 数据库文章标题长度
     */
    @JsonIgnore
    private final int titleLength = 100;

    public String checkIntegrityCreate() {
        if(type == null || type.isEmpty() || !("news".equals(type) || "notice".equals(type) || "winners".equals(type) || "prize".equals(type))) {
            return "文章类型错误";
        }
        if(title == null || title.isEmpty() || title.length() > titleLength) {
            return "文章标题错误";
        }
        // Author 使用的token内置值,不需要验证
        // Time 后端生成,不需要验证
        if(content == null || content.isEmpty()) {
            return "文章内容错误";
        }
        return null;
    }

    public String checkIntegrityUpdate() {
        if(id == null || id < 0) {
            return "编号错误";
        }
        return checkIntegrityCreate();
    }
}
