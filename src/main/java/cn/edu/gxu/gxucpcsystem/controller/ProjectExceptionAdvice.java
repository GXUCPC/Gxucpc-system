package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.exception.EmailException;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.AuthenticationFailedException;
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
        if(msg.contains("information.check_user_id_contest_id")) {
            String userid = msg.substring(msg.lastIndexOf("-") + 1, msg.lastIndexOf("' for"));
            return new Re(Code.RESOURCE_DISABLE, null, "学号" + userid + "已注册！如要修改信息，请在报名机器上修改或联系管理员");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "未知SQL约束异常:" + msg);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public Re doAuthenticationFailedException() {
        return new Re(Code.RESOURCE_DISABLE, null, "邮箱密钥错误");
    }

    @ExceptionHandler(NumberFormatException.class)
    public Re doNumberFormatException() {
        return new Re(Code.RESOURCE_DISABLE, null, "无效的输入格式");
    }

    @ExceptionHandler(RuntimeException.class)
    public Re doRuntimeException(RuntimeException e) {
        return new Re(Code.RESOURCE_DISABLE, null, e.getMessage());
    }
}
