package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sct
 * @date 2022/8/2
 */
@Service
public class EmailService {
    @Autowired
    ContestDao contestDao;

    public Boolean sendEmailByContest(Email email) {
        List<Contest> contestList = contestDao.getById(email.getId());
        if(contestList.isEmpty()) return false;
        Contest contest = contestList.get(0);
        // TODO 查询submit表，获取报名该比赛的人的邮箱
        return true;
    }
}
