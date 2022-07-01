package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Sct
 * @Date 2022/7/2 0:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class OtherThingsController {
    private final Code code = new Code();
    @GetMapping("/ip")
    public Re getReferIp(HttpServletRequest request) {
        return new Re(code.STATUS_OK, request.getRemoteAddr(), "获取成功");
    }
}
