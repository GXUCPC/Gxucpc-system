package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sct
 * @date 2022/8/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Redis {
    private String key;
    private String value;


    public String checkIntegrityCreate() {
        if(key == null || key.isEmpty()) {
            return "键错误";
        }
        if(value == null || value.isEmpty()) {
            return "值错误";
        }
        return null;
    }
}
