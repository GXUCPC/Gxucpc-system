package cn.edu.gxu.gxucpcsystem.utils;

import cn.edu.gxu.gxucpcsystem.Service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Sct
 * @date 2022/8/1
 */
@Component
public class LogsUtil {

    @Autowired
    private OperationService operationService;

    public static LogsUtil logsUtil;

    @PostConstruct
    public void init() {
        logsUtil = this;
        logsUtil.operationService = this.operationService;
    }

    /**
     * 记录用户操作
     *
     * @param user 用户名
     * @param details 操作
     */
    public static void logOfOperation(String user, String details) {
        logsUtil.operationService.addOperations(user, details);
    }
}
