package cn.edu.gxu.gxucpcsystem.dao.mysql;

import cn.edu.gxu.gxucpcsystem.domain.Player;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "PlayerDao") //解决Service层 @AutoWire 提示错误
public interface PlayerDao {
    List<Player> turnToPage(int index);
    void insertPlayer();
    void delPlayer();
}
