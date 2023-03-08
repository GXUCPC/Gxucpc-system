package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Text;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sct
 * @date 2022/8/15
 */
@Mapper
public interface TextDao {
    List<Text> getByID(Integer id);

    List<Text> getByType(String type, Integer offset, Integer count);

    List<Text> getAll(Integer offset, Integer count);

    Integer countAll();

    Integer countType(String type);

    Integer insert(String type, String author, Long time, String content, String title);

    Integer updateByID(Integer id, String type, String title, Long time, String content);

    Integer deleteByID(Integer id);
}
