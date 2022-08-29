package cn.edu.gxu.gxucpcsystem.dao.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sct
 * @date 2022/7/30
 */
public class AdminDaoTest {
    @Autowired
    AdminDao adminDao;
    @Test
    public void getByUsernameTest() {
        System.out.println(adminDao.getByUsername("root").get(0));
    }
}
