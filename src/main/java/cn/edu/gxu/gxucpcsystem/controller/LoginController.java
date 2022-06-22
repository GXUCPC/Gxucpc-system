package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.Service.AdminService;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.Map;

/**
 * @author MaoMao
 * @Description 登录界面
 * @create 2022-06-22 7:39 PM
 */

@RestController
@RequestMapping( "/login")
public class LoginController {
    @PostMapping
    public Result login (@RequestBody Map<String,String> loginMap){
        Admin admin =
    }
}
