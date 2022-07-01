package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Sct
 * @date 2022/7/1
 */

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(TokenException.class)
    public Re doTokenException(TokenException tokenException) {
        return new Re(tokenException.getCode(), null, tokenException.getMessage());
    }
}
