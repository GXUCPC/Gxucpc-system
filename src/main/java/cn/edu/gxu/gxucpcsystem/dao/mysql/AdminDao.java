package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "AdminDao") //解决Service层 @AutoWire 提示错误
public interface AdminDao {
    /**
     * 分页查询
     *
     * @param offset 偏移量
     * @param count 最大行数
     * @return Page[offset+1, offset+count]
     */
    List<Admin> queryByAge(Integer offset, Integer count);

    /**
     * 添加管理员
     *
     * @param username 用户名
     * @param password 密码
     * @param userType 用户权限
     * @param realName 真实姓名
     * @param createTime 创建时间（时间戳）
     * @param email 邮箱
     * @return 影响行数
     */
    Integer addAdmin(String username,String password, Integer userType, String realName, Long createTime, String email);

    /**
     * 删除管理员
     *
     * @param id 用户编号
     * @return 影响行数
     */
    Integer delAdmin(Integer id);

    /**
     * 修改用户信息（需要修改密码）
     *
     * @param id 用户编号
     * @param userName 用户名
     * @param password 密码
     * @param userType 用户权限
     * @param realName 真实姓名
     * @param email 邮箱
     * @return 影响行数
     */
    Integer updateAdminWithPassword(Integer id, String userName, String password, Integer userType, String realName, String email);

    /**
     * 修改用户信息（不需要修改密码）
     *
     * @param id 用户编号
     * @param userName 用户名
     * @param userType 用户权限
     * @param realName 真实姓名
     * @param email 邮箱
     * @return 影响行数
     */
    Integer updateAdminWithoutPassword(Integer id, String userName, Integer userType, String realName, String email);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return Admin
     */
    List<Admin> getByUsername(String username);

    /**
     * 查询数据表共有几条记录
     *
     * @return 记录总数
     */
    Integer getCount();

    Integer updateAdminLogin(Integer id, Long lastLogin);
}
