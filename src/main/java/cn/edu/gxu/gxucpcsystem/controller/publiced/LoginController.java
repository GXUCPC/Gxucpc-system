package cn.edu.gxu.gxucpcsystem.controller.publiced;

import cn.edu.gxu.gxucpcsystem.Service.AdminService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import cn.edu.gxu.gxucpcsystem.utils.JwtUtil;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sct
 * @date 2022/8/1
 */
@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class LoginController {
    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public Re login(@RequestBody Admin admin, HttpServletRequest request, HttpServletResponse response) {
        Admin checkAdmin = adminService.checkPassword(admin.getUsername(), admin.getPassword());
        if(checkAdmin != null) {
            checkAdmin.setUserType((int)checkAdmin.getUserType() == 1 ? "Super Admin" : "Admin");
            String token = JwtUtil.createJWT(Code.JWT_KEY, Code.TTL_MILLIS, checkAdmin.getUsername(), request.getRemoteAddr(), (String) checkAdmin.getUserType(), checkAdmin.getPassword());
            response.addHeader("token", token);
            response.addHeader("Access-Control-Expose-Headers", "token");

            LogsUtil.logOfOperation(checkAdmin.getUsername(), "登录系统");
            return new Re(Code.STATUS_OK, checkAdmin, "登陆成功");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "用户名/密码无效");
    }
}
