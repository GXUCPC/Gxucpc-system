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
     * 新增报名表
     *
     * @param player 参赛选手
     * @return 影响行数
     */
    Integer addPlayer(Player player);

    Integer getCount(Integer contestId);
}
