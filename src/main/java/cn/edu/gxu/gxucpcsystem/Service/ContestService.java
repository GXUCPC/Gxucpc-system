package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sct
 * @date 2022/7/31
 */
@Service
public class ContestService {
    @Autowired
    ContestDao contestDao;

    /**
     * 添加比赛
     *
     * @param contest 比赛信息
     * @return 是否添加成功
     */
    public Boolean addContest(Contest contest) {
        try {
            MailUtil mailUtil = new MailUtil(contest.getEmail(), contest.getSmtpPassword());
            mailUtil.init();
            mailUtil.sendHtmlEmail(contest.getEmail(), "邮箱可用性测试", "收到此邮件表示邮箱信息填写正确");
            mailUtil.close();
            return contestDao.addContest(contest.getName(), contest.getSignUpBeginTime(), contest.getSignUpEndTime(), contest.getEmail(), contest.getSmtpPassword(), contest.getContestBeginTime(), contest.getContestEndTime()) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除比赛
     *
     * @param id 比赛ID
     * @return 是否删除成功
     */
    public Boolean delContest(Integer id) {
        boolean flag = true;
        //TODO 删除mongoDB的奖状
        //TODO 删除相关的报名表
        flag &= contestDao.delContest(id) == 1;
        return flag;
    }

    /**
     * 修改比赛
     * @param contest 比赛信息
     * @return 是否修改成功
     */
    public Boolean updateContest(Contest contest) {
        try {
            MailUtil mailUtil = new MailUtil(contest.getEmail(), contest.getSmtpPassword());
            mailUtil.init();
            mailUtil.sendHtmlEmail(contest.getEmail(), "邮箱可用性测试", "收到此邮件表示邮箱信息填写正确");
            mailUtil.close();
            return contestDao.updateContest(contest.getId(), contest.getName(), contest.getSignUpBeginTime(), contest.getSignUpEndTime(), contest.getEmail(), contest.getSmtpPassword(), contest.getContestBeginTime(), contest.getContestEndTime()) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 分页查找
     * @param currentPage 当前页
     * @param numberPerPage 每页个数
     * @return [总数，该页元素]
     */
    public PagesEntity getByPages(int currentPage, int numberPerPage) {
        return new PagesEntity(contestDao.queryByPage((currentPage - 1) * numberPerPage, numberPerPage), contestDao.getCount());
    }

    /**
     * 修改下载状态
     * @param contest id，isDownload 有效参数
     * @return 是否修改成功
     */
    public Boolean updateDownload(Contest contest) {
        return contestDao.updateDownload(contest.getId(), contest.getIsDownload()) == 1;
    }
}
