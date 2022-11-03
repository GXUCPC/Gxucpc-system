package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Contest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sct
 * @date 2022/7/31
 */
@Mapper
public interface ContestDao {

    /**
     * 按照名字模糊查询
     * @param query 关键字
     * @return
     */
    List<Contest> selectContestIdAndNameByLikeName(String query);

    /**
     * 分页查询
     *
     * @param offset 偏移量
     * @param count 最大行数
     * @return Page[offset+1, offset+count]
     */
    List<Contest> queryByPage(Integer offset, Integer count);

    /**
     * 分页查询
     * @param offset 偏移量
     * @param count 最大行数
     * @return Page[offset+1, offset+count] : [id, name, createTime]
     */
    List<Contest> selectIdAndNameAndCreateTimeByPage(Integer offset, Integer count);

    /**
     * 新增比赛
     *
     * @param name 名称
     * @param signUpBeginTime 报名开始时间
     * @param signUpEndTime 报名结束时间
     * @param email 赛事邮箱
     * @param smtpPassword smtp密码
     * @param contestBeginTime 比赛开始时间
     * @param contestEndTime 比赛结束时间
     * @param createTime 比赛创建时间
     * @return 影响行数
     */
    Integer addContest(String name, Long signUpBeginTime, Long signUpEndTime, String email, String smtpPassword, Long contestBeginTime, Long contestEndTime, Long createTime);

    /**
     * 删除比赛
     * @param id 比赛ID
     * @return 影响行数
     */
    Integer delContest(Integer id);

    /**
     * 修改比赛
     *
     * @param id 比赛ID
     * @param name 名称
     * @param signUpBeginTime 报名开始时间
     * @param signUpEndTime 报名结束时间
     * @param email 赛事邮箱
     * @param smtpPassword smtp密码
     * @param contestBeginTime 比赛开始时间
     * @param contestEndTime 比赛结束时间
     * @return 影响行数
     */
    Integer updateContest(Integer id, String name, Long signUpBeginTime, Long signUpEndTime, String email, String smtpPassword, Long contestBeginTime, Long contestEndTime);

    /**
     * 更改下载状态
     *
     * @param id 比赛ID
     * @param isDownload 下载状态
     * @return
     */
    Integer updateDownload(Integer id, Boolean isDownload);

    /**
     * 获取下载状态
     *
     * @param id 比赛ID
     * @return 下载状态
     */
    Boolean getDownload(Integer id);

    /**
     * 查询数据表共有几条记录
     *
     * @return 记录总数
     */
    Integer getCount();

    /**
     * 通过ID查询
     *
     * @param id 比赛ID
     * @return
     */
    List<Contest> getById(Integer id);

    /**
     * 通过ID修改查询状态
     * @param contest
     * @return
     */
    Integer updateIsQueryById(Contest contest);

    /**
     * 根据名称查询ID
     * @param contest
     * @return
     */
    Integer selectIdByName(Contest contest);
}
