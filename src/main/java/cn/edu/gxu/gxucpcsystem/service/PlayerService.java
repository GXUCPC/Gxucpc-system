package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.DomjudgeDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.utils.ExcelHandle;
import cn.edu.gxu.gxucpcsystem.utils.RandomPwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 8/6/2022 4:51 PM
 * @Version: 1.0
 * @Description:
 */

@Service("PlayerService")
public class PlayerService {
    @Autowired
    PlayerDao playerDao;

    @Autowired
    ContestDao contestDao;

    @Autowired
    DomjudgeDao domjudgeDao;
    /**
     * 分页查找
     *
     * @param currentPage   当前页
     * @param numberPerPage 每页个数
     * @param contestId     比赛号
     * @return [总数，该页元素]
     */
    public PagesEntity getByPages(int currentPage, int numberPerPage, int contestId) {
        return new PagesEntity(playerDao.queryByPage((currentPage - 1) * numberPerPage, contestId, numberPerPage), playerDao.getCount(contestId));
    }

    /**
     * 分页模糊查找
     *
     * @param currentPage   当前页
     * @param numberPerPage 每页个数
     * @param contestId     比赛号
     * @param regex         正则值
     * @return [总数，该页元素]
     */
    public PagesEntity getByPagesRegex(int currentPage, int numberPerPage, int contestId, String regex) {
        return new PagesEntity(playerDao.queryByPageRegex((currentPage - 1) * numberPerPage, contestId, numberPerPage, regex), playerDao.regexCount(contestId, regex));
    }

    /**
     * 添加选手参赛信息
     *
     * @param player 比赛选手信息
     * @return 是否添加成功
     */
    public boolean addPlayer(Player player) {
        Integer ff = playerDao.addPlayer(player);
        List<Contest> contests = contestDao.getById(player.getContestId());
        if(contests == null) {
            return false;
        }
        List<Player> list = playerDao.selectIdByContestIdAndUserId(player.getUserId(), player.getContestId());

        String username = "GXUCPC" + String.format("%03d", list.get(0).getInformationId() % 1000);
        String password = RandomPwdUtil.getRandomPwd(8);
        if(ff == 1) {
            ff &= domjudgeDao.insertQuery(player.getUserId(), player.getContestId(), username, password);
        }
        return ff == 1;
    }

    /**
     * 更新选手参赛信息
     *
     * @param player 比赛选手信息
     * @return 是否更新成功
     */
    public boolean updatePlayer(Player player) {
        int ff = playerDao.updatePlayer(player);
        return ff >= 1;
    }

    /**
     * 选手自己更新自己的信息
     * @param player 参赛选手
     * @return
     */
    public boolean updatePlayerCheckClient(Player player) {
        int ff = playerDao.updatePlayerCheckClientNo(player);
        return ff >= 1;
    }
    /**
     * 删除选手参赛信息
     *
     * @param id 比赛选手id
     * @return 是否删除成功
     */
    public boolean deletePlayer(Integer id) {
        int ff = playerDao.deletePlayer(id);
        return ff >= 1;
    }

    /**
     * 下载比赛表单excel
     *
     * @param id 比赛ID
     * @return 错误原因
     */
    public byte[] downloadForms(HttpServletResponse request, Integer id) throws IOException {
        List<Player> plays = playerDao.getPlayersByContent(id);
        List<Contest> contest = contestDao.getById(id);
        if (contest == null) {
            return null;
        }
        ExcelHandle.exportExcel(request, plays, "报名表", contest.get(0).getName(), 15);
        return null;
    }

    public List<Player> queryFormsByClientNo(String client, Integer id) {
        return playerDao.selectFormsByClientNo(client, id);
    }

    /**
     * 取消报名
     * @param client 客户端编号
     * @param id 表单id
     * @return
     */
    public Boolean cancelRegistration(String client, Integer id) {
        return playerDao.deletePlayerAndCheckClient(id, client) == 1;
    }

    /**
     * 通过表单ID查询姓名和学号
     * @param id 表单ID
     * @return
     */
    public Player queryNameAndUserIdById(Integer id) {
        List<Player> list = playerDao.selectNameAndUserIdById(id);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    /**
     * 根据比赛编号和参赛者Domjudge编号(表单编号%1000)查找名称
     * @param contestId
     * @param teamId
     * @return
     */
    public String queryNameByContestIdAndIdMod(Integer contestId, Integer teamId) {
        return playerDao.selectNameByContestIdAndIdMod(contestId, teamId);
    }
}
