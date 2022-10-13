package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: MaoMao
 * @License: (C) Copyright 2005-2019, xxx Corporation Limited.
 * @Contact: 2986325137@qq.com
 * @Date: 8/6/2022 4:26 PM
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface PlayerDao {
    /**
     * 分页查询
     *
     * @param offset    偏移量
     * @param count     最大行数
     * @param contestId 比赛Id
     * @return Page[offset+1, offset+count]
     */
    List<Player> queryByPage(Integer offset, Integer contestId, Integer count);

    /**
     * 分页模糊查询
     *
     * @param offset    偏移量
     * @param count     最大行数
     * @param contestId 比赛Id
     * @param regex     正则值
     * @return Page[offset+1, offset+count]
     */
    List<Player> queryByPageRegex(Integer offset, Integer contestId, Integer count, String regex);

    /**
     * 新增报名表
     *
     * @param player 参赛选手
     * @return 影响行数
     */
    Integer addPlayer(Player player);

    /**
     * 查询比赛内选手最大行数
     *
     * @param contestId 比赛
     * @return 选手报名表数量
     */
    Integer getCount(Integer contestId);

    /**
     * 查询比赛内选手最大行数
     *
     * @param contestId 比赛
     * @param regex     正则值
     * @return 选手报名表数量
     */
    Integer regexCount(Integer contestId, String regex);

    /**
     * 修改报名表
     *
     * @param player 参赛选手
     * @return 影响行数
     */
    Integer updatePlayer(Player player);

    /**
     * 选手自己修改自己的信息
     * @param player 参赛选手
     * @return
     */
    Integer updatePlayerCheckClientNo(Player player);

    /**
     * 删除报名表
     *
     * @param id 参赛选手id
     * @return 影响行数
     */
    Integer deletePlayer(Integer id);

    /**
     * 根据比赛查询选手
     *
     * @param id 比赛ID
     * @return
     */
    List<Player> getPlayersByContent(Integer id);

    /**
     * 根据客户端编号查询提交记录
     *
     * @param client 客户端编号
     * @return
     */
    List<Player> selectFormsByClientNo(String client, Integer id);

    /**
     * 删除client客户端的报名记录
     * @param id 表单id
     * @param client 客户端编号
     * @return
     */
    Integer deletePlayerAndCheckClient(Integer id, String client);

    /**
     * 按照id查询姓名和学号
     * @param id 表单ID
     * @return
     */
    List<Player> selectNameAndUserIdById(Integer id);

    /**
     * 根据比赛编号查询表单编号
     * @param id 比赛编号
     * @return
     */
    List<Player> selectIdByContestId(Integer id);

    /**
     * 根据比赛编号&学号查询表单ID
     * @param userId 学号
     * @param contestId 比赛编号
     * @return
     */
    List<Player> selectIdByContestIdAndUserId(String userId, Integer contestId);
}
