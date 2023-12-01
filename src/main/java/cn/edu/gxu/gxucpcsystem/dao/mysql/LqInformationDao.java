package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.LqPlayer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LqInformationDao {

    String TABLE_NAME = "lq_information";
    String INSERT_COL = "userName, userSex, userCourse, userClass, userQQ, userMail, userPhone, userId, contestId, isDiscount, imgURI";
    String ALL_COL = "id," + INSERT_COL;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_COL, ")value(#{userName}, #{userSex}, #{userCourse}, #{userClass}, #{userQQ}, #{userMail}, #{userPhone}, #{userId}, #{contestId}, #{isDiscount}, #{imgURI})"})
    int insert(LqPlayer player);

    @Select({"select", ALL_COL, "from", TABLE_NAME, "where contestId=#{id}"})
    List<LqPlayer> getPlayersByContent(Integer id);
}
