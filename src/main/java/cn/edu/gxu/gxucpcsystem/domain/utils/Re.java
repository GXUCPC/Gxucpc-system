package cn.edu.gxu.gxucpcsystem.domain.utils;

import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

/**
 * @Author Sct
 * @Date 2022/6/12 13:28
 * @Version 1.0
 */
@Data
public class Re {
    private Integer statusCode;
    private Object data;
    private String message;


    public Re(Integer s, Object d, String m) {
        statusCode = s;
        data = d;
        message = m;
    }
}
