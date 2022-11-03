package cn.edu.gxu.gxucpcsystem.controller.admin;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 4:55 PM
 */


import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.service.ContestService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.MapperContest;
import cn.edu.gxu.gxucpcsystem.extend.RunnableSpider;
import cn.edu.gxu.gxucpcsystem.schedule.ScheduleRegister;
import cn.edu.gxu.gxucpcsystem.service.MapperContestService;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MaoMao
 * @Description 控制定时器的状态
 * @create 2022-10-09 4:53 PM
 */

@RestController
@RequestMapping("/api/admin/schedule")
@CrossOrigin
public class ScheduleController {
    @Autowired
    private ScheduleRegister scheduleRegister;
    @Autowired
    private RunnableSpider runnableSpider;

    @Autowired
    private ContestService contestService;

    @Autowired
    private MapperContestService mapperContestService;
    @PostMapping("/start")
    public Re startSchedule(@RequestBody MapperContest contest){
        if (runnableSpider.status == 1){
            return new Re(Code.RESOURCE_DISABLE,null,"已经存在一个正在运行的脚本");
        }
        if(contestService.getById(contest.getRegisterContest()) == null) {
            return new Re(Code.RESOURCE_DISABLE, null, "无效的比赛信息");
        }
        // 防止因为系统意外停机，导致脚本非正常退出导致数据不一致性
        mapperContestService.stopPython();
        contest.setIsSpider(true);
        mapperContestService.updatePython(contest);
        runnableSpider.status = 1;
        runnableSpider.mapperContest = contest;
        scheduleRegister.addCronTask(runnableSpider,"0 0/1 * * * ?");
        return new Re(Code.STATUS_OK,null,"增加脚本成功");
    }

    @GetMapping("/stop")
    public Re stopSchedule(){
        if (runnableSpider.status == 0){
            return new Re(Code.STATUS_OK,null,"尚未启动");
        }
        mapperContestService.stopPython();
        runnableSpider.status = 0;
        runnableSpider.mapperContest = null;
        scheduleRegister.removeCronTask(runnableSpider);
        return new Re(Code.STATUS_OK,null,"停止成功");
    }

    @GetMapping("/active")
    private Re querySchedule() {
        if(runnableSpider.status == 0) {
            return new Re(Code.RESOURCE_DISABLE, null, "没有正在运行的脚本");
        }
        MapperContest mapperContest = mapperContestService.queryPython();
        if(mapperContest == null) {
            runnableSpider.status = 0;
            return new Re(Code.RESOURCE_DISABLE, null, "没有正在运行的脚本");
        }
        Contest contest = contestService.getById(mapperContest.getRegisterContest());
        mapperContest.setName(contest.getName());
        return new Re(Code.STATUS_OK, mapperContest, "查询成功");
    }

}
