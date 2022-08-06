package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.Service.PlayerService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 8/6/2022 5:08 PM
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class TestMessageController {
    @Autowired
    public PlayerService playerService;

    @PostMapping("/id")
    public Re application() {
        int ff = 1;
        return new Re(Code.STATUS_OK, 111, "登陆成功");
    }

}
