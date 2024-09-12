package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.domain.LqPlayer;
import cn.edu.gxu.gxucpcsystem.domain.NnTeam;
import cn.edu.gxu.gxucpcsystem.service.*;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.exception.EmailException;
import cn.edu.gxu.gxucpcsystem.utils.EmailTemplateUtil;
import cn.edu.gxu.gxucpcsystem.utils.MailUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sct
 * @date 2022/8/28
 */
@RestController
@RequestMapping("/api/public/signup")
@CrossOrigin
@Slf4j
public class SignUpController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private LanQiaoService lanQiaoService;
    @Autowired
    private NanNingService nanNingService;

    /**
     * 报名
     *
     * @param player 报名信息
     * @param itemID 比赛id
     * @return
     */
    @PostMapping("/{itemID}")
    public Re signUp(HttpServletRequest request, @RequestBody Player player, @PathVariable Integer itemID) {
        player.setContestId(itemID);
        player.setClientNo(request.getHeader("client"));
        String msg = player.checkIntegrityCreate();
        if (msg != null) {
            return new Re(Code.RESOURCE_DISABLE, null, msg);
        }
        Contest contest = contestService.getById(itemID);
        if (contest.getSignUpEndTime() < System.currentTimeMillis()) {
            return new Re(Code.RESOURCE_DISABLE, null, "报名已结束");
        }
        if (contest.getSignUpBeginTime() > System.currentTimeMillis()) {
            return new Re(Code.RESOURCE_DISABLE, null, "报名未开始");
        }
        MailUtil mailUtil = new MailUtil(contest.getEmail(), contest.getSmtpPassword());
        try {
            mailUtil.init();
        } catch (Exception e) {
            return new Re(Code.RESOURCE_DISABLE, null, "邮箱系统初始化失败");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy");
        Date date = new Date(System.currentTimeMillis());


        if (playerService.addPlayer(player)) {
            try {
                mailUtil.sendHtmlEmail(player.getUserMail(), "邮箱可用性测试",
                        String.format(EmailTemplateUtil.DongXinSignUpSuccessTem,
                                player.getUserName(),
                                player.getUserName(),
                                player.getUserCourse(),
                                player.getUserSex(),
                                player.getUserId(),
                                (player.getGroup() ? "正式组" : "新生组"),
                                (player.getStar() ? "打星参赛" : "正常参赛"),
                                formatter.format(date),
                                formatter2.format(date)));
            } catch (EmailException e) {
                throw e;
            } catch (Exception e) {
                log.error(e.toString());
                return new Re(Code.RESOURCE_DISABLE, null, e.toString());
            }
            return new Re(Code.STATUS_OK, null, "报名成功!请检查邮箱是否接收到信息确认邮件，并将赛事邮箱加入白名单，以防错过赛事通知！");
        } else {
            return new Re(Code.DATABASE_ERROR, null, "报名失败");
        }
    }

    @PostMapping("/lq/{itemID}")
    public Re lqSignUp(@RequestBody LqPlayer player, @PathVariable Integer itemID, Integer ok) {
        player.setContestId(itemID);
        return lanQiaoService.addPlay(player, ok);
    }

    @PostMapping("/nn/{itemID}")
    public Re nnSignUp(@RequestBody NnTeam team, @PathVariable Integer itemID) {
        team.setContestId(itemID);
        return nanNingService.addTeam(team);
    }

    /**
     * 修改报名表
     *
     * @param request HTTP请求头
     * @param player  修改信息
     * @param itemID  比赛ID
     * @return
     */
    @PutMapping("/{itemID}")
    public Re updateSubmitted(HttpServletRequest request, @RequestBody Player player, @PathVariable Integer itemID) {
        player.setClientNo(request.getHeader("client"));
        String msg = player.checkIntegrityUpdate();
        if (msg != null) {
            return new Re(Code.RESOURCE_DISABLE, null, msg);
        }
        Contest contest = contestService.getById(itemID);
        if (contest.getSignUpEndTime() < System.currentTimeMillis()) {
            return new Re(Code.RESOURCE_DISABLE, null, "报名已结束");
        }
        if (contest.getSignUpBeginTime() > System.currentTimeMillis()) {
            return new Re(Code.RESOURCE_DISABLE, null, "报名未开始");
        }
        if (playerService.updatePlayerCheckClient(player)) {
            return new Re(Code.STATUS_OK, null, "修改成功");
        } else {
            return new Re(Code.RESOURCE_DISABLE, null, "修改失败");
        }

    }

    /**
     * 查询比赛信息
     *
     * @param itemID
     * @return
     */
    @GetMapping("/{itemID}")
    public Re getContest(@PathVariable Integer itemID) {
        Contest contest = contestService.getById(itemID);
        if (contest == null) {
            return new Re(Code.DATABASE_ERROR, null, "没有该比赛信息");
        }
        Contest contest1 = new Contest();
        contest1.setSignUpBeginTime(contest.getSignUpBeginTime());
        contest1.setSignUpEndTime(contest.getSignUpEndTime());
        contest1.setName(contest.getName());
        return new Re(Code.STATUS_OK, contest1, "查询成功");
    }

    /**
     * 查询历史提交记录
     *
     * @param request HTTP请求头
     * @param id      比赛id
     * @return
     */
    @GetMapping("/history")
    public Re getHistorySubmit(HttpServletRequest request, Integer id) {
        String cli = request.getHeader("client");
        if (cli.length() < 5) {
            new Re(Code.STATUS_OK, null, "查询失败");
        }
        return new Re(Code.STATUS_OK, playerService.queryFormsByClientNo(cli, id), "查询成功");
    }

    /**
     * 取消报名
     *
     * @param request HTTP请求头
     * @param id      表单id
     * @return
     */
    @PutMapping("/history/cancel")
    public Re cancelRegistration(HttpServletRequest request, Integer itemID, Integer id) {
        Contest contest = contestService.getById(itemID);
        if (contest.getSignUpEndTime() < System.currentTimeMillis()) {
            return new Re(Code.RESOURCE_DISABLE, null, "报名已结束");
        }
        if (contest.getSignUpBeginTime() > System.currentTimeMillis()) {
            return new Re(Code.RESOURCE_DISABLE, null, "报名未开始");
        }
        if (playerService.cancelRegistration(request.getHeader("client"), id)) {
            return new Re(Code.STATUS_OK, null, "您已取消报名！");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "不存在该报名信息!");
    }
}
