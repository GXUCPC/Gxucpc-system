package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.service.OperationService;
import cn.edu.gxu.gxucpcsystem.service.RedisService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Operation;
import cn.edu.gxu.gxucpcsystem.domain.Redis;
import cn.edu.gxu.gxucpcsystem.utils.DateUtil;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author Sct
 * @Date 2022/7/1 22:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin
public class DashboardController {
    @Autowired
    OperationService operationService;
    @Autowired
    RedisService redisService;

    /**
     * 获取管理员操作日志
     *
     * @param num 前num条
     * @return
     */
    @GetMapping("/operationlog")
    public Re getOperationLogs(Integer num) {
        List<Operation> operationList =operationService.getOperations(num);
        List<String> logs = new ArrayList<>();
        for(Operation i: operationList) {
            logs.add(DateUtil.convertTimestamp2Date(i.getOperationTime(), "yyyy-MM-dd HH:mm:ss") + ": " + i.getUser() + " " + i.getDetails());
        }
        return new Re(Code.STATUS_OK, logs, "查询成功");
    }

    /**
     * 获取首页图片链接
     *
     * @return
     */
    @GetMapping("/images")
    public Re getImagesURL() {
        return new Re(Code.STATUS_OK, redisService.getImagesURL(), "成功");
    }

    /**
     * 删除首页图片链接
     *
     * @param request HTTP请求头
     * @param url 图片链接
     * @return
     */
    @DeleteMapping("/images")
    public Re deleteImagesURL(HttpServletRequest request, String url) {
        if(redisService.deleteImages(url)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "删除了首页图片：" + url);
            return new Re(Code.STATUS_OK, null, "删除成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "删除失败");
    }

    /**
     * 添加首页图片链接
     *
     * @param request HTTP请求头
     * @param url 图片链接
     * @return
     */
    @PostMapping("/images")
    public Re insertImagesURL(HttpServletRequest request, String url) {
        Redis redis = new Redis("url", url);
        String msg = redis.checkIntegrityCreate();
        if(msg != null) {
            return new Re(Code.RESOURCE_DISABLE, null, msg);
        }
        if(redisService.insert("URL", url)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "添加了首页图片：" + url);
            return new Re(Code.STATUS_OK, null, "成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "添加失败");
    }

    /**
     * 修改网站ICP编号
     *
     * @param request HTTP请求头
     * @param icp icp编号
     * @return
     */
    @PostMapping("/icp")
    public Re insertICP(HttpServletRequest request, String icp) {
        Redis redis = new Redis("icp", icp);
        String msg = redis.checkIntegrityCreate();
        if(msg != null) {
            return new Re(Code.RESOURCE_DISABLE, null, msg);
        }

        if(redisService.insert("ICP", icp)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "修改了网站ICP备案号：" + icp);
            return new Re(Code.STATUS_OK, null, "成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "添加失败");
    }
}
