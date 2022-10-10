package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.MapContest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapContestDao {
    /***
     * 分页查询
     * @param offset
     * @param count
     * @return
     */
    public List<MapContest> selectQuery(Integer offset,  Integer count);

    /***
     * 添加映射关系
     * @param mapContest
     * @return
     */
    public Integer addMapContest(MapContest mapContest);

    /***
     * 修改
     * @param mapContest
     * @return
     */
    public Integer updateMapContest(MapContest mapContest);

    /***
     * 删除
     * @param id
     * @return
     */
    public Integer delMapContest(int id);

    /***
     * 爬虫开启或关闭
     * @param is_spider
     * @param id
     * @return
     */
    public Integer isSpider(boolean is_spider,int id);

    public Integer getCount();
}
