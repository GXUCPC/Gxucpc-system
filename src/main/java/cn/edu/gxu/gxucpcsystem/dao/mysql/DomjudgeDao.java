package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.Domjudge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DomjudgeDao {

    /**
     * 按照学号&比赛ID&密钥查询账密
     * @param contestID 比赛ID
     * @param userID 学号
     * @param key 密钥
     * @return
     */
    List<Domjudge> selectQuery(Integer contestID, String userID, String key);

    /**
     * 插入一条记录
     * @param userId 学号
     * @param contestId 比赛ID
     * @return
     */
    Integer insertQuery(String userId, Integer contestId, String username, String password);

    /**
     * 修改用户名
     * @param id 账号ID
     * @param username 修改后的用户名
     * @return
     */
    Integer updateUsernameById(Integer id, String username);

    /**
     * 修改下载状态
     * @param contest
     * @return
     */
    Integer updateIsQueryByContestId(Contest contest);

    /**
     * 根据比赛编号查询所有账密
     * @param id 比赛编号
     * @return
     */
    List<Domjudge> selectQueryByContestId(Integer id);
}
