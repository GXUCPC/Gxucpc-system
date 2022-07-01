package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-06-24 7:08 PM
 */

@Service("PlayerService")
public class PlayerService {
    @Autowired
    private PlayerDao playerDao;
    public List<Player> turnToPage(int index){
        return playerDao.turnToPage(index * 20);
    }
}
