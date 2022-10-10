package cn.edu.gxu.gxucpcsystem.controller.admin;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 4:55 PM
 */


import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.MapContest;
import cn.edu.gxu.gxucpcsystem.extend.RunnableSpider;
import cn.edu.gxu.gxucpcsystem.schedule.ScheduleRegister;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.AuthenticationFailedException;
import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/start")
    public Re startSchedule(){
        if (runnableSpider.status == 1){
            return new Re(Code.STATUS_OK,null,"已经存在");
        }
        runnableSpider.status = 1;
        scheduleRegister.addCronTask(runnableSpider,"0 0/1 * * * ?");
        return new Re(Code.STATUS_OK,null,"增加成功");
    }

    @GetMapping("/stop")
    public Re stopSchedule(){
        if (runnableSpider.status == 0){
            return new Re(Code.STATUS_OK,null,"尚未启动");
        }
        runnableSpider.status = 0;
        scheduleRegister.removeCronTask(runnableSpider);
        return new Re(Code.STATUS_OK,null,"停止成功");
    }

}
