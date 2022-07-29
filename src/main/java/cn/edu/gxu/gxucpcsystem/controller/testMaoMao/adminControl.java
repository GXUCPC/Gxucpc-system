package cn.edu.gxu.gxucpcsystem.controller.testMaoMao;

import cn.edu.gxu.gxucpcsystem.Service.AdminService;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.edu.gxu.gxucpcsystem.controller.Code.STATUS_OK;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 7/29/2022 5:45 PM
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/public/test/admin")
@CrossOrigin
public class adminControl {
    @Autowired
    AdminService adminService;
    @GetMapping("/add")
    public Re addAdmin(){
//        admin.setUserName("123");
//        admin.setUserName("111");
        int f = adminService.addAdmin("MaoMao","123456");
        if(f > 0){
            return new Re(STATUS_OK,null,"ok","插入成功");
        }
        return new Re(4000,null,"false","插入失败");
    }
}
