package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.utils.MD5Utils;
import cn.edu.gxu.gxucpcsystem.dao.mysql.AdminDao;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Sct
 * @Date 2022-06-22 7:56 PM
 * @Version 1.0
 */

@Service("AdminService")
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    /**
     * 添加用户
     *
     * @param admin 用户实体类
     * @return 是否添加成功
     */
    public Boolean addAdmin(Admin admin) {
        Long createTime = System.currentTimeMillis();
        admin.setPassword(MD5Utils.string2MD5(admin.getPassword()));
        int userType;
        if(admin.getUserType().equals("Super Admin")) {
            userType = 1;
        } else {
            userType = 2;
        }
        return adminDao.addAdmin(admin.getUsername(), admin.getPassword(), userType, admin.getRealName(), createTime, admin.getEmail()) == 1;
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    public Boolean delAdmin(Integer id) {
        return adminDao.delAdmin(id) == 1;
    }

    /**
     * 判断登录密码或用户名是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户名与密码是否匹配
     */
    public Admin checkPassword(String username, String password) {
        List<Admin> userList = adminDao.getByUsername(username);
        if(userList.isEmpty()) return null;
        Admin user = userList.get(0);
        if(!MD5Utils.passwordIsTrue(password, user.getPassword())) return null;
        return user;
    }

    /**
     * 修改用户信息
     *
     * @param admin 用户实体类
     * @param isChangePassword 是否修改密码
     * @return 是否修改成功
     */
    public Boolean updateAdmin(Admin admin, Boolean isChangePassword) {
        int userType;
        // 防止SQL注入
        if(admin.getUserType().equals("Super Admin")) userType = 1;
        else if(admin.getUserType().equals("Admin")) userType = 2;
        else return false;

        if(isChangePassword) {
            admin.setPassword(MD5Utils.string2MD5(admin.getPassword()));
            return adminDao.updateAdminWithPassword(admin.getId(), admin.getUsername(), admin.getPassword(), userType, admin.getRealName(), admin.getEmail()) == 1;
        }
        return adminDao.updateAdminWithoutPassword(admin.getId(), admin.getUsername(), userType, admin.getRealName(), admin.getEmail()) == 1;
    }

    /**
     * 分页获取
     *
     * @param currentPage 当前页数
     * @param numberPerPage 每页有多少
     * @return 第currentPage页的管理员
     */
    public PagesEntity getByPage(Integer currentPage, Integer numberPerPage) {
        return new PagesEntity(adminDao.queryByAge((currentPage - 1) * numberPerPage, numberPerPage), adminDao.getCount());
    }

    /**
     * 按用户名查找
     * @param username 用户名
     * @return 不存在返回NULL
     */
    public Admin getByUsername(String username) {
        List<Admin> adminList = adminDao.getByUsername(username);
        if(adminList.isEmpty())  return null;
        return adminDao.getByUsername(username).get(0);
    }

    public Boolean updateLastLogin(Integer id, Long lastLogin) {
        return adminDao.updateAdminLogin(id, lastLogin) == 1;
    }
}
