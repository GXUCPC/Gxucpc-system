package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.apache.coyote.Request;
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
}
