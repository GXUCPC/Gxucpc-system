package cn.edu.gxu.gxucpcsystem.controller;


import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
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
    private Re re;
    @PostMapping
    public Re login (@RequestBody Map<String,String> loginMap){
        re = null;
        return re;
    }
}
