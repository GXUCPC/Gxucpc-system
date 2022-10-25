package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Email;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.exception.EmailException;
import cn.edu.gxu.gxucpcsystem.utils.LogsUtil;
import cn.edu.gxu.gxucpcsystem.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sct
 * @date 2022/8/2
 */
@Service
@Slf4j
public class EmailService {
    @Autowired
    ContestDao contestDao;
    @Autowired
    PlayerDao playerDao;

    public Boolean sendEmailByContest(Email email) {
        List<Contest> contestList = contestDao.getById(email.getId());
        if (contestList.isEmpty()) {
            return false;
        }
        Contest contest = contestList.get(0);
        System.out.println(contest);
        List<Player> list = playerDao.queryByPage(0, email.getId(), 999999999);
        List<String> errMail = new ArrayList<>();
        MailUtil mailUtil = new MailUtil(contest.getEmail(), contest.getSmtpPassword());
        try {
            mailUtil.init();
            log.info("Email={}, Password={} 邮件工具类初始化完成", contest.getEmail(), contest.getSmtpPassword());
        } catch (Exception e) {
            log.error("Email={}, Password={} 邮箱工具类初始化失败 {}", contest.getEmail(), contest.getSmtpPassword(), e);
            return false;
        }
        for (Player p : list) {
            try {
                mailUtil.sendHtmlEmail(p.getUserMail(), email.getEmailSubject(), email.getEmailData());
                log.info("{} 成功给 {} 发送邮件", contest.getEmail(), p.getUserMail());
                // 防止每小时时间限制，每封邮件间做5s延迟限制
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                log.error("{} 失败给 {} 发送邮件, 原因 {}", contest.getEmail(), p.getUserMail(), e);

                errMail.add(p.getUserMail());
            }
        }

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(System.currentTimeMillis());
//TODO 群发结果发送给赛事邮箱报错javax.mail.AuthenticationFailedException: 535 Login Fail. Please enter your authorization code to login.
//        try {
//            mailUtil.sendHtmlEmail(contest.getEmail(), "群发邮件结果", "<p>" + formatter.format(date) + " 系统完成对《" + contest.getName() + "》报名人员的群发邮件</p><p>共:" + list.size() + " 成功:" + (list.size() - errMail.size()) + " 失败:" + errMail.size() + "</p>" + "<p>失败名单如下：</p><p>" + errMail + "</p>");
//        } catch (EmailException e) {
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }

        LogsUtil.logOfOperation("系统", contest.getName() + "的群发失败名单:" + errMail);
        return true;
    }
}
