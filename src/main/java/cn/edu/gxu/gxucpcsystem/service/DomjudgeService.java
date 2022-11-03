package cn.edu.gxu.gxucpcsystem.service;


import cn.edu.gxu.gxucpcsystem.dao.mysql.DomjudgeDao;
import cn.edu.gxu.gxucpcsystem.domain.Domjudge;
import cn.edu.gxu.gxucpcsystem.utils.TsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DomjudgeService {
    @Autowired
    DomjudgeDao domjudgeDao;

    /**
     * 通过学号&比赛ID&密钥查询账密
     * @param contestID 比赛ID
     * @param userID 学号
     * @param key 密钥
     * @return
     */
    public Domjudge queryIDAndUserAndPass(Integer contestID, String userID, String key) {
        List<Domjudge> list = domjudgeDao.selectQuery(contestID, userID, key);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据比赛编号查询账密
     * @param id 比赛编号
     * @return
     */
    public List<Domjudge> queryPassAndUserAndNameAndUserIdByContest(Integer id) {
        return domjudgeDao.selectQueryByContestId(id);
    }

    public byte[] downloadPassUser(Integer id) throws IOException {
        List<Domjudge> list = queryPassAndUserAndNameAndUserIdByContest(id);
        TsvUtil tsvUtil = new TsvUtil();
        return tsvUtil.createTsv(list);
    }
}
