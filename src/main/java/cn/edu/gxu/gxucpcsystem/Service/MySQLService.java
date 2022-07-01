package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.AdminDao;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-06-21 6:34 PM
 */

@Service("MySQLService")
public class MySQLService {
    @Autowired
    private AdminDao adminDao;
    public List<Admin> queryAll() throws RuntimeException {
        return adminDao.queryAll();
    }

}

