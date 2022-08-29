package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Operation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sct
 * @date 2022/8/1
 */
@Mapper
public interface OperationDao {
    /**
     * 查询用户操作日志
     *
     * @param num 查询数量
     * @return
     */
    List<Operation> queryLogs(Integer num);

    /**
     * 添加操作日志
     *
     * @param user 用户名
     * @param details 操作内容
     * @param operationTime 时间
     * @return
     */
    int addLogs(String user, String details, Long operationTime);
}
