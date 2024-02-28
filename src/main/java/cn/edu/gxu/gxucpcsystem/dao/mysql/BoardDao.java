package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    List<Board> selectBoardByContestId(Integer id);
    Integer insertBoard(Board board);
    Integer deleteByContestId(Integer contestId);
}
