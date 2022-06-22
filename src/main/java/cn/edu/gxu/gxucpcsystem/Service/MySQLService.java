package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-06-21 6:34 PM
 */

@Service
public class MySQLService {
    @Autowired
    private MysqlDao mysqlDao;

    public List<Admin> queryAll() throws RuntimeException {
        return mysqlDao.queryAll();
    }
    public Admin queryAdminLogin(String userName)throws RuntimeException{
        return mysqlDao.queryAdminLogin(userName);
    }
}

