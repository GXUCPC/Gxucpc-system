package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Email;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Sct
 * @date 2022/8/2
 */
@Service
public class EmailService {
    @Autowired
    ContestDao contestDao;
    @Autowired
    PlayerDao playerDao;

    public Boolean sendEmailByContest(Email email) {
        List<Contest> contestList = contestDao.getById(email.getId());
        if (contestList.isEmpty()) return false;
        Contest contest = contestList.get(0);
        List<Player> list = playerDao.queryByPage(0, email.getId(), 999999999);
        List<String> errMail = new ArrayList<>();
        MailUtil mailUtil = new MailUtil(contest.getEmail(), contest.getSmtpPassword());
        mailUtil.init();
        for (Player p : list) {
            try {
                mailUtil.sendHtmlEmail(p.getUserMail(), email.getEmailSubject(), email.getEmailData());
            } catch (Exception e) {
                errMail.add(p.getUserMail());
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        try {
            mailUtil.sendHtmlEmail(contest.getEmail(), "群发邮件结果", "<p>" + formatter.format(date) + " 系统完成对《" + contest.getName() + "》报名人员的群发邮件</p><p>共:" + list.size() + " 成功:" + (list.size()-errMail.size()) + " 失败:" + errMail.size() + "</p>" + "<p>失败名单如下：</p><p>" + errMail.toString() + "</p>");
            mailUtil.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
