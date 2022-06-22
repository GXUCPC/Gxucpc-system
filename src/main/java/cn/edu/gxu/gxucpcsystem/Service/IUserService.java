package cn.edu.gxu.gxucpcsystem.Service;



/**
 * @author MaoMao
 * @Description
 * @create 2022-06-20 5:13 PM
 */

import cn.edu.gxu.gxucpcsystem.domain.User;

import java.util.List;


public interface IUserService {
    /**
     * 保存用户
     * @param user 返回保存的用户
     */
    void saveUser(User user) ;
    /**
     * 查询所有用户
     * @return 用户
     */
    List<User> findAll() ;

    /**
     * 根据名字查询用户
     * @param name 名字
     * @return 用户
     */
    List<User> findUserByName(String name) ;
}