package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.exception.EmailException;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

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

    @ExceptionHandler(NullPointerException.class)
    public Re doNullPointerException() {
        return new Re(Code.RESOURCE_DISABLE, null, "缺少必要参数");
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Re doSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        String msg = e.getMessage();
        if(msg.contains("username")) {
            return new Re(Code.RESOURCE_DISABLE, null, "用户名已存在");
        }
        if(msg.contains("name")) {
            return new Re(Code.RESOURCE_DISABLE, null, "比赛名称已存在");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "为止SQL约束异常:" + msg);
    }
}
