package cn.edu.gxu.gxucpcsystem.controller.admin;

import cn.edu.gxu.gxucpcsystem.Service.OperationService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Operation;
import cn.edu.gxu.gxucpcsystem.utils.DateUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    OperationService operationService;

    @GetMapping("/operationlog")
    public Re getOperationLogs(Integer num) {
        List<Operation> operationList =operationService.getOperations(num);
        List<String> logs = new ArrayList<>();
        for(Operation i: operationList) {
            logs.add(DateUtil.convertTimestamp2Date(i.getOperationTime(), "yyyy-MM-dd HH:mm:ss") + ": " + i.getUser() + " " + i.getDetails());
        }
        return new Re(STATUS_OK, logs, "查询成功");
    }
}
