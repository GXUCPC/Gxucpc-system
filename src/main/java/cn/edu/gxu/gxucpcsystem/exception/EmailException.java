package cn.edu.gxu.gxucpcsystem.exception;

import lombok.Data;

/**
 * @author Sct
 * @date 2022/9/27
 */
@Data
public class EmailException extends RuntimeException{
    private Integer code;
    private String message;
    public EmailException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
