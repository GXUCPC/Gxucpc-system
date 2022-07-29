package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "AdminDao") //解决Service层 @AutoWire 提示错误
public interface AdminDao {
    /**
     * 查询所有
     * @return all
     */
    List<Admin> queryAll();


    /**
     * 添加管理员账户
     *
     * @param userName 用户名
     * @param password 密码
     * @param privilege 权限
     * @param realName 真实姓名
     */
    void addAdmin(String userName,String password, Integer privilege, String realName);


    /**
     * 删除管理员账户
     *
     * @param userName
     */
    void delAdmin(String userName);


    /**
     * 修改管理员信息
     *
     * @param userName
     * @param password
     * @param privilege
     * @param realName
     */
    void updateAdmin(String userName,String password, Integer privilege, String realName);
    Admin verify (String userName);
}
