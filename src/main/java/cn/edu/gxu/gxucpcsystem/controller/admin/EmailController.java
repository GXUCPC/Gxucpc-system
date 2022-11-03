package cn.edu.gxu.gxucpcsystem.controller.admin;



import cn.edu.gxu.gxucpcsystem.service.EmailService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Email;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sct
 * @date 2022/8/2
 */
@RestController
@RequestMapping("/api/admin/email")
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    /**
     * 根据比赛项目群发邮件
     *
     * @param request HTTP请求头
     * @param email 邮件类
     * @return
     */
    @PostMapping
    public Re sendMailByContest(HttpServletRequest request, @RequestBody Email email) {
        String msg = email.checkIntegrityCreate();
        if(msg != null) {
            return new Re(Code.RESOURCE_DISABLE, null, msg);
        }

        if(emailService.sendEmailByContest(email)) {
            LogsUtil.logOfOperation(request.getHeader("username"), "对编号为：" + email.getId() + " 的比赛报名人员群发了邮件");
            return new Re(Code.STATUS_OK, null, "发送成功");
        }
        return new Re(Code.RESOURCE_DISABLE, null, "邮件系统异常");
    }

}
