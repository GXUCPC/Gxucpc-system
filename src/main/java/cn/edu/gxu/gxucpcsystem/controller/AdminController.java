package cn.edu.gxu.gxucpcsystem.controller;

import cn.edu.gxu.gxucpcsystem.Service.PlayerService;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-06-24 7:14 PM
 */

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/information")
    public List<Player> turnToPage(@RequestParam("page") int index){
        return playerService.turnToPage(index);
    }
}
