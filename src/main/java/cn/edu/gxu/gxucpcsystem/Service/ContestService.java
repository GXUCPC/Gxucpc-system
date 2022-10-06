package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.DomjudgeDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.exception.EmailException;
import cn.edu.gxu.gxucpcsystem.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.AuthenticationFailedException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Sct
 * @date 2022/7/31
 */
@Service
public class ContestService {
    @Autowired
    ContestDao contestDao;

    @Autowired
    PlayerDao playerDao;

    @Autowired
    DomjudgeDao domjudgeDao;

    public List<Contest> queryContest(String query) {
        return contestDao.selectContestIdAndNameByLikeName(query);
    }

    /**
     * 添加比赛
     *
     * @param contest 比赛信息
     * @return 是否添加成功
     */
    public Boolean addContest(Contest contest) throws AuthenticationFailedException {
        try {
            MailUtil mailUtil = new MailUtil(contest.getEmail(), contest.getSmtpPassword());
            mailUtil.init();
            mailUtil.sendHtmlEmail(contest.getEmail(), "邮箱可用性测试", "收到此邮件表示邮箱信息填写正确");
            return contestDao.addContest(contest.getName(), contest.getSignUpBeginTime(), contest.getSignUpEndTime(), contest.getEmail(), contest.getSmtpPassword(), contest.getContestBeginTime(), contest.getContestEndTime(), System.currentTimeMillis()) == 1;
        } catch (EmailException | AuthenticationFailedException e) {
            throw e;
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
    @Autowired
    MedalService medalService;
    public Boolean delContest(Integer id) {
        boolean flag;
        medalService.delMedal(id);
        flag = contestDao.delContest(id) == 1;
        return flag;
    }

    /**
     * 修改比赛
     * @param contest 比赛信息
     * @return 是否修改成功
     */
    public Boolean updateContest(Contest contest) throws AuthenticationFailedException {
        try {
            if(contestDao.updateContest(contest.getId(), contest.getName(), contest.getSignUpBeginTime(), contest.getSignUpEndTime(), contest.getEmail(), contest.getSmtpPassword(), contest.getContestBeginTime(), contest.getContestEndTime()) == 1) {
                List<Player> list = playerDao.selectIdByContestId(contest.getId());
                for (Player player : list) {
                    String username = "GXUCPC" + String.format("%03d", player.getInformationId());
                    domjudgeDao.updateUsernameById(player.getInformationId(), username);
                }
                MailUtil mailUtil = new MailUtil(contest.getEmail(), contest.getSmtpPassword());
                mailUtil.init();
                mailUtil.sendHtmlEmail(contest.getEmail(), "邮箱可用性测试", "收到此邮件表示邮箱信息填写正确");
                return true;
            }
            return false;
        } catch (EmailException | AuthenticationFailedException e) {
            throw e;
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
    public PagesEntity getByPages(Integer currentPage, Integer numberPerPage) {
        return new PagesEntity(contestDao.queryByPage((currentPage - 1) * numberPerPage, numberPerPage), contestDao.getCount());
    }

    /**
     * 分页查找
     * @param currentPage 当前页
     * @param numberPerPage 每页个数
     * @return [总数，该页元素:[id, name, createTime]
     */
    public PagesEntity queryContestOfIdAndNameAndCreatTimeBuPages(Integer currentPage, Integer numberPerPage) {
        return new PagesEntity(contestDao.selectIdAndNameAndCreateTimeByPage((currentPage - 1) * numberPerPage, numberPerPage), contestDao.getCount());
    }

    /**
     * 修改下载状态
     * @param contest id，isDownload 有效参数
     * @return 是否修改成功
     */
    public Boolean updateDownload(Contest contest) {
        return contestDao.updateDownload(contest.getId(), contest.getIsDownload()) == 1;
    }

    public Contest getById(Integer id) {
        List<Contest> list = contestDao.getById(id);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Boolean modifyIsQuery(Contest contest) {
        Boolean sy = contestDao.updateIsQueryById(contest) == 1;
        sy &= domjudgeDao.updateIsQueryByContestId(contest) == 1;
        return sy;
    }
}
