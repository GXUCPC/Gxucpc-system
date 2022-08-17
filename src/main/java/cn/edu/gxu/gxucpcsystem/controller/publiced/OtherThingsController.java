package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.Service.RedisService;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static cn.edu.gxu.gxucpcsystem.controller.Code.STATUS_OK;

/**
 * @Author Sct
 * @Date 2022/7/2 0:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class OtherThingsController {
    @Autowired
    RedisService redisService;
    /**
     * 获取请求IP
     *
     * @param request HTTP请求头
     * @return
     */
    @GetMapping("/ip")
    public Re getReferIp(HttpServletRequest request) {
        return new Re(STATUS_OK, request.getRemoteAddr(), "获取成功");
    }

    /**
     * 获取首页图片链接
     * @return
     */
    @GetMapping("/images")
    public Re getImagesURL() {
        return new Re(STATUS_OK, redisService.getImagesURL(), "成功");
    }

    /**
     * 获取网站ICP编号
     *
     * @return
     */
    @GetMapping("/icp")
    public Re getICP() {
        return new Re(STATUS_OK, redisService.getICP(), "成功");
    }
}
