package cn.edu.gxu.gxucpcsystem.controller.admin;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 4:55 PM
 */


import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.extend.RunnableSpider;
import cn.edu.gxu.gxucpcsystem.schedule.ScheduleRegister;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        scheduleRegister.addCronTask(runnableSpider,"0 0/1 * * * ?");
        return new Re(Code.STATUS_OK,null,"增加成功");
    }
}
