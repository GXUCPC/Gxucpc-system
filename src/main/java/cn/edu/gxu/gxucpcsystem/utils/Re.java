package cn.edu.gxu.gxucpcsystem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Sct
 * @Date 2022/6/12 13:28
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Re {
    private Integer statusCode;
    private Object data;
    private String message;
}
