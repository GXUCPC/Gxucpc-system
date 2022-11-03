package cn.edu.gxu.gxucpcsystem.controller.publiced;


import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.service.BoardService;
import cn.edu.gxu.gxucpcsystem.service.ContestService;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/public/board")
@CrossOrigin
public class BoardController {
    @Autowired
    ContestService contestService;

    @Autowired
    BoardService boardService;

    @GetMapping("/{id}")
    public Re queryMessageByContestId(@PathVariable Integer id) {
        Contest contest = contestService.getById(id);
        if(contest == null) {
            return new Re(Code.RESOURCE_DISABLE, null, "无效的比赛ID");
        }
        contest.setIsQuery(null);
        contest.setEmail(null);
        contest.setCreateTime(null);
        contest.setIsDownload(null);
        contest.setSmtpPassword(null);

        return new Re(Code.STATUS_OK, contest, "查询成功");
    }

    @GetMapping("/data/{id}")
    public Re queryBoardData(@PathVariable Integer id) {
        return new Re(Code.STATUS_OK, boardService.queryBoardByContestId(id), "查询成功");
    }

}
