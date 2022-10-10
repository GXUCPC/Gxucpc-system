package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.Service.MapContestService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.MapContest;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.AuthenticationFailedException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-10 2:39 PM
 */

@RestController
@RequestMapping("/api/admin/mapContest")
@CrossOrigin
public class MapContestController {
    @Autowired
    MapContestService mapContestService;
    @GetMapping
    public Re getContestByPage(Integer currentPage, Integer numberPerPage) {
        return new Re(Code.STATUS_OK, mapContestService.selectByPage(currentPage, numberPerPage), "查询成功");
    }
    @PutMapping
    public Re updateMapContest(HttpServletRequest request, @RequestBody MapContest mapContest) throws AuthenticationFailedException {
//        String msg = contest.checkIntegrityUpdate();
//        if(msg != null) {
//            return new Re(Code.RESOURCE_DISABLE, null, msg);
//        }
        if(mapContestService.updateMapContest(mapContest)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "修改了比赛爬虫映射关系《" + mapContest.getContestName() + "》");
            return new Re(Code.STATUS_OK, null, "修改成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "修改失败!请检查是否是以下原因: 1.重复比赛名称 ");
    }

    @DeleteMapping
    public Re delMapContest(HttpServletRequest request, Integer id, String name) {
        if(mapContestService.delMapContest(id)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "删除了比赛映射《" + name + "》");
            return new Re(Code.STATUS_OK, null, "删除成功");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "删除失败");
    }
    @PostMapping
    public Re addMapContest(HttpServletRequest request, @RequestBody MapContest mapContest) throws AuthenticationFailedException {
//        String msg = contest.checkIntegrityCreate();
//        if(msg != null) {
//            return new Re(Code.RESOURCE_DISABLE, null, msg);
//        }
        if(mapContestService.addMapContest(mapContest)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "新增了比赛《" + mapContest.getContestName() + "》");
            return new Re(Code.STATUS_OK, null, "添加成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "修改失败!请检查是否是以下原因: 1.重复比赛名称");
    }
    @PutMapping("/spider")
    public Re updateSpider(HttpServletRequest request, @RequestBody MapContest mapContest) {
        if(mapContestService.updateSpider(mapContest.getId(), mapContest.is_spider())) {
            LogsUtil.logOfOperation(request.getHeader("username"), ( mapContest.is_spider()?  "打开了":"关闭了" ) + "比赛《" + mapContest.getContestName() + "》的爬虫权限");
            return new Re(Code.STATUS_OK, null, "修改成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "查询失败");
    }
}
