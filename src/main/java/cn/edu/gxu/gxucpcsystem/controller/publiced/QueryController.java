package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.service.DomjudgeService;
import cn.edu.gxu.gxucpcsystem.service.PlayerService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Domjudge;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/query")
@CrossOrigin
public class QueryController {
    @Autowired
    DomjudgeService domjudgeService;
    @Autowired
    PlayerService playerService;

    @GetMapping("/{contestID}/{userID}/{key}")
    public Re queryUsernameAndPassword(@PathVariable Integer contestID, @PathVariable String userID, @PathVariable String key) {
        Domjudge domjudge = domjudgeService.queryIDAndUserAndPass(contestID, userID, key);
        if(domjudge == null) {
            return new Re(Code.DATABASE_ERROR, null, "没有查询到结果，请确认所填信息正确无误！");
        }
        if(!domjudge.getIsQuery()) {
            return new Re(Code.RESOURCE_DISABLE, null, "该比赛还未开启查询服务！");
        }
        Player player = playerService.queryNameAndUserIdById(domjudge.getId());
        if(player == null) {
            return new Re(Code.DATABASE_ERROR, null, "没有查询到结果，请确认所填信息正确无误！");
        }
        Player player1 = new Player();
        player1.setUserName(player.getUserName());
        player1.setUserId(player.getUserId());
        domjudge.setPlayer(player1);
        domjudge.setIsQuery(null);
        domjudge.setId(null);
        return new Re(Code.STATUS_OK, domjudge, "查询成功");
    }

}
