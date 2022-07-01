package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Sct
 * @Date 2022/7/1 22:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin
public class DashboardController {
    private final Code code = new Code();

    @GetMapping("/operationlog")
    public Re getOperationLogs() {
        return new Re(code.STATUS_OK, null, "获取成功");
    }
}
