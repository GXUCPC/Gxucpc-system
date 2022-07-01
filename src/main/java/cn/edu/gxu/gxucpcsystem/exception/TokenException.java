package cn.edu.gxu.gxucpcsystem.exception;

import lombok.Data;

/**
 * @author Sct
 * @date 2022/7/1
 */
@Data
public class TokenException extends RuntimeException{
    private Integer code;
    private String message;
    public TokenException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
