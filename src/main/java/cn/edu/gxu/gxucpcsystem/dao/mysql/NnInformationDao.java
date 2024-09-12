package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.NnTeam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NnInformationDao {
    String TABLE_NAME = "nn_information";
    String INSERT_COL = "school,team,member1,member2,member3,subject,grade,email,teacher,contestId";
    String ALL_COL = "id," + INSERT_COL;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_COL, ")value(#{school},#{team},#{member1},#{member2},#{member3},#{subject},#{grade},#{email},#{teacher},#{contestId})"})
    int insert(NnTeam team);

    @Select({"select", ALL_COL, "from", TABLE_NAME, "where contestId=#{id}"})
    List<NnTeam> getTeamsByContent(Integer id);
}
