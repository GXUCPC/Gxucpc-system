package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.OperationDao;
import cn.edu.gxu.gxucpcsystem.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sct
 * @date 2022/8/1
 */
@Service
public class OperationService {
    @Autowired
    OperationDao operationDao;

    /**
     * 查询num条操作记录
     *
     * @param num 查询数量
     * @return
     */
    public List<Operation> getOperations(Integer num) {
        return operationDao.queryLogs(num);
    }

    /**
     * 添加日志
     *
     * @param user 操作用户
     * @param details 操作日志
     * @return
     */
    public Boolean addOperations(String user, String details) {
        Long nowTime = System.currentTimeMillis();
        return operationDao.addLogs(user, details, nowTime) == 1;
    }
}
