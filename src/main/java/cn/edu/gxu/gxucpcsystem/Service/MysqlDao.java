package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-06-21 6:33 PM
 */
@Mapper
@Component(value = "mysqlDao") //解决Service层 @AutoWire 提示错误
public interface MysqlDao {
    /**
     * 查询所有
     * @return all
     */
    List<Admin> queryAll();
    Admin queryAdminLogin(String userName);
}
