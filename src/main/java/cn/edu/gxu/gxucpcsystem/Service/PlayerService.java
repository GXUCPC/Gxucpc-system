package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (ff >= 1) {
            return true;
        }
        return false;
    }

    /**
     * 更新选手参赛信息
     *
     * @param player 比赛选手信息
     * @return 是否更新成功
     */
    public boolean updatePlayer(Player player) {
        int ff = playerDao.updatePlayer(player);
        if (ff >= 1) {
            return true;
        }
        return false;
    }

    /**
     * 删除选手参赛信息
     *
     * @param player 比赛选手信息
     * @return 是否删除成功
     */
    public boolean deletePlayer(Player player) {
        int ff = playerDao.deletePlayer(player);
        if (ff >= 1) {
            return true;
        }
        return false;
    }
}
