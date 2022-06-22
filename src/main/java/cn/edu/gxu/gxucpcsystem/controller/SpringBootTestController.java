package cn.edu.gxu.gxucpcsystem.controller;
import cn.edu.gxu.gxucpcsystem.Service.MySQLService;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * @author MaoMao
 * @Description
 * @create 2022-06-21 6:36 PM
 */
@RestController
public class SpringBootTestController {
    @Autowired
    private MySQLService mySQLService;
    /**
     * MySQL 拉取测试表所有数据
     *
     * @return 测试表所有数据
     */
    @RequestMapping("/queryAll")
    public List<Admin> queryAll() {
        List<Admin> results = mySQLService.queryAll();
        return results;
    }
}