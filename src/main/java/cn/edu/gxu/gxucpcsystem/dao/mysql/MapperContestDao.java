package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.MapperContest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapperContestDao {
    Integer insertNewMapper(Contest contest);
    Integer deleteOldMapper();
    Integer updateMapper(MapperContest mapperContest);
    List<MapperContest> selectActive();
}
