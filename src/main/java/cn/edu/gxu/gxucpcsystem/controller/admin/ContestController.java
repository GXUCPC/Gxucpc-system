package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.service.*;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.AuthenticationFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sct
 * @date 2022/7/31
 */
@RestController
@RequestMapping("/api/admin/contest")
@CrossOrigin
public class ContestController {
    @Autowired
    ContestService contestService;

    @Autowired
    PlayerService playerService;

    @Autowired
    DomjudgeService domjudgeService;

    @Autowired
    MapperContestService mapperContestService;

    @Autowired
    LanQiaoService lanQiaoService;
    @Autowired
    NanNingService nanNingService;

    /**
     * 添加比赛
     *
     * @param contest 比赛信息
     * @return
     */
    @PostMapping
    public Re addContest(HttpServletRequest request, @RequestBody Contest contest) throws AuthenticationFailedException {
        String msg = contest.checkIntegrityCreate();
        if (msg != null) {
            return new Re(Code.RESOURCE_DISABLE, null, msg);
        }
        if (contest.getType() != 1) {
            contest.setContestBeginTime(0L);
            contest.setContestEndTime(0L);
        }
        if (contestService.addContest(contest)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "新增了比赛《" + contest.getName() + "》");
            Integer id = contestService.queryIdByName(contest);
            contest.setId(id);
            mapperContestService.addPython(contest);
            return new Re(Code.STATUS_OK, null, "添加成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "修改失败!请检查是否是以下原因: 1.重复比赛名称 2.邮箱账号与密码不匹配");
    }

    /**
     * 删除比赛
     *
     * @param id 比赛ID
     * @return
     */
    @PutMapping("/delete")
    public Re delContest(HttpServletRequest request, Integer id, String name) {
        if (contestService.delContest(id)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "删除了比赛《" + name + "》");
            return new Re(Code.STATUS_OK, null, "删除成功");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "删除失败");
    }

    /**
     * 修改比赛
     *
     * @param contest 比赛信息
     * @return
     */
    @PutMapping
    public Re updateContest(HttpServletRequest request, @RequestBody Contest contest) throws AuthenticationFailedException {
        String msg = contest.checkIntegrityUpdate();
        if (msg != null) {
            return new Re(Code.RESOURCE_DISABLE, null, msg);
        }
        if (contest.getType() != 1) {
            contest.setContestEndTime(0L);
            contest.setContestBeginTime(0L);
        }
        if (contestService.updateContest(contest)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "修改了比赛《" + contest.getName() + "》");
            return new Re(Code.STATUS_OK, null, "修改成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "修改失败!请检查是否是以下原因: 1.重复比赛名称 2.邮箱账号与密码不匹配");
    }

    /**
     * 分页查找
     *
     * @param currentPage   当前页
     * @param numberPerPage 每页个数
     * @return
     */
    @GetMapping
    public Re getContestByPage(Integer currentPage, Integer numberPerPage) {
        return new Re(Code.STATUS_OK, contestService.getByPages(currentPage, numberPerPage), "查询成功");
    }

    /**
     * 修改下载状态
     *
     * @param contest id，isDownload 有效参数
     * @return
     */
    @PutMapping("/download")
    public Re updateDownload(HttpServletRequest request, @RequestBody Contest contest) {
        if (contestService.updateDownload(contest)) {
            LogsUtil.logOfOperation(request.getHeader("username"), (contest.getIsDownload() ? "打开了" : "关闭了") + "比赛《" + contest.getName() + "》的奖状下载权限");
            return new Re(Code.STATUS_OK, null, "修改成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "查询失败");
    }

    /**
     * 修改查询状态
     *
     * @param request
     * @param contest
     * @return
     */
    @PutMapping("/query")
    public Re updateQuery(HttpServletRequest request, @RequestBody Contest contest) {
        if (contest.getId() == null || contest.getIsQuery() == null || contest.getName() == null) {
            return new Re(Code.RESOURCE_DISABLE, null, "异常请求");
        }
        if (contestService.modifyIsQuery(contest)) {
            LogsUtil.logOfOperation(request.getHeader("username"), (contest.getIsQuery() ? "打开了" : "关闭了") + "比赛《" + contest.getName() + "》的账密查询权限");
            return new Re(Code.STATUS_OK, null, "修改成功");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "修改失败，请重试");
    }

    /**
     * 下载比赛表单
     *
     * @param id 比赛id
     * @return
     */
    @GetMapping("/download/{id}")
    public byte[] downloadForms(HttpServletResponse request, @PathVariable Integer id) {
        Contest contest = contestService.queryById(id);
        if (contest.getType() == 1) {
            try {
                return playerService.downloadForms(request, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (contest.getType() == 2) {
            try {
                return lanQiaoService.downloadForms(request, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (contest.getType() == 3) {
            try {
                return nanNingService.downloadForms(request, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 下载Domjudge账号密码
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/pwd/{id}")
    public byte[] downloadPwd(HttpServletResponse request, @PathVariable Integer id) {
        try {
            return domjudgeService.downloadPassUser(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
