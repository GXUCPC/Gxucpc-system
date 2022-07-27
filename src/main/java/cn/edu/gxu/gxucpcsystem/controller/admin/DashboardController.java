package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.edu.gxu.gxucpcsystem.controller.Code.STATUS_OK;

/**
 * @Author Sct
 * @Date 2022/7/1 22:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin
public class DashboardController {

    @GetMapping("/operationlog")
    public Re getOperationLogs(HttpServletRequest request) {
        return new Re(STATUS_OK, null, "获取成功", (String) request.getAttribute("token"));
    }
}
