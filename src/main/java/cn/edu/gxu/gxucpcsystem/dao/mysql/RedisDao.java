package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Redis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sct
 * @date 2022/8/17
 */
@Mapper
public interface RedisDao {
    Integer insert(String key, String value);
    Integer deleteByKey(String key);
    Integer deleteByValue(String value);
    List<Redis> getByKey(String key);
}
