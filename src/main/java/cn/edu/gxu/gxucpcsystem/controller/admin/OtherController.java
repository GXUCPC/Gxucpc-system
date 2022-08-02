package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sct
 * @date 2022/8/1
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class OtherController {

    /**
     * 验证Token是否有效(通过admin的拦截器即为有效)
     *
     * @param request 请求头
     * @return
     */
    @GetMapping("/checkToken")
    public Re checkToken(HttpServletRequest request) {
        return new Re(Code.STATUS_OK, null, "验证成功");
    }
}
