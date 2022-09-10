package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import cn.edu.gxu.gxucpcsystem.utils.ExcelHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

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
        int ff = playerDao.addPlayer(player);
        return ff >= 1;
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
     * @param response http返回体
     * @param id 比赛ID
     * @return 错误原因
     */
    public String downloadForms(HttpServletResponse response, Integer id) {
        List<Player> plays = playerDao.getPlayersByContent(id);
        List<Contest> contest = contestDao.getById(id);
        if(contest == null) {
            return "查无此比赛";
        }

        try {
            ExcelHandle.exportExcel(response, plays, "报名表", contest.get(0).getName() + "-" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-" + "报名表", 15);
        } catch (IOException e) {
            return "系统IO异常";
        }
        return null;
    }
}
