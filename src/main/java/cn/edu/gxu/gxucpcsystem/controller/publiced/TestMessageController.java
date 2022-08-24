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
import javax.websocket.server.PathParam;
import java.util.List;

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

    @PostMapping("/{id}")
    public Re application(@PathVariable("id") Integer contestId, @RequestBody Player player) {
        boolean num = playerService.addPlayer(player);
        if (num) {
            return new Re(Code.STATUS_OK, 111, player.getUserName() + "操作成功");
        }
        return new Re(Code.STATUS_OK, 111, "操作异常，请联系管理员");
    }

    //    /**
//     * 分页查找
//     * @param currentPage 当前页
//     * @param numberPerPage 每页个数
//     * @param contestId 比赛id
//     * @return
//     */
    @GetMapping("/query")
    public Re getContestByPage() {
        Integer currentPage = 1, numberPerPage = 2, contestId = 111;

        return new Re(Code.STATUS_OK, playerService.getByPages(currentPage, numberPerPage, contestId), "查询成功");
    }
}
