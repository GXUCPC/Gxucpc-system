package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.Service.utils.MD5Utils;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MaoMao
 * @Description
 * @create 2022-06-22 7:56 PM
 */

@Service("AdminService")
public class AdminService {
    @Autowired
    Admin admin;

    public Admin findByLoginNameAndPassword(String userName, String password){
        String passwordMD5 = MD5Utils.string2MD5(password);



        return null;
    }
}
