package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "AdminDao") //解决Service层 @AutoWire 提示错误
public interface AdminDao {
    /**
     * 查询所有
     *
     * @return all
     */
    List<Admin> queryAll();

    int addAdmin(String userName, String password);

    int delAdmin(String userName);

    int updateAdmin(String userName, String password);

    Admin verify(String userName);
}
