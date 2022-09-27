package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.exception.EmailException;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Sct
 * @date 2022/7/1
 */

@RestControllerAdvice
public class ProjectExceptionAdvice {
    /**
     * TokenException处理
     *
     * @param tokenException 处理的Exception信息
     * @return 异常代码和异常信息
     */
    @ExceptionHandler(TokenException.class)
    public Re doTokenException(TokenException tokenException) {
        return new Re(tokenException.getCode(), null, tokenException.getMessage());
    }

    /**
     * EmailException处理
     * @param emailException 处理的Exception信息
     * @return 异常代码和异常信息
     */
    @ExceptionHandler(EmailException.class)
    public Re doEmailException(EmailException emailException) {
        return new Re(emailException.getCode(), null, emailException.getMessage());
    }
}
