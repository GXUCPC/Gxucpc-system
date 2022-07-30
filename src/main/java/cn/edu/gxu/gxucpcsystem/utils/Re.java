package cn.edu.gxu.gxucpcsystem.utils;

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
public class Re {
    private Integer statusCode;
    private Object data;
    private String message;
    private String token;


    /**
     * 带权的返回结构
     *
     * @param statusCode 状态码
     * @param data 数据
     * @param message 返回的备注信息
     * @param token 新的token
     */
    public Re(Integer statusCode, Object data, String message, String token) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.token = token;
    }


    /**
     * 不带权的返回结构
     *
     * @param statusCode 状态码
     * @param data 数据
     * @param message 返回的备注信息
     */
    public Re(Integer statusCode, Object data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }
}
