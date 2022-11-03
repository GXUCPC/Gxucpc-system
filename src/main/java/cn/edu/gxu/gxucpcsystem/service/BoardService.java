package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.BoardDao;
import cn.edu.gxu.gxucpcsystem.domain.AcMessage;
import cn.edu.gxu.gxucpcsystem.domain.Board;
import cn.edu.gxu.gxucpcsystem.utils.BoardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    @Autowired
    PlayerService playerService;

    /**
     * 根据比赛编号查询榜单
     * @param id
     * @return
     */
    public List<Board> queryBoardByContestId(Integer id) {
        List<Board> list = boardDao.selectBoardByContestId(id);
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setAcMessages((List<AcMessage>) BoardUtil.parseAcMessage(list.get(i).getContent(), AcMessage.class));
            list.get(i).setName(playerService.queryNameByContestIdAndIdMod(id, list.get(i).getTeamId()));
            list.get(i).setContent(null);
            list.get(i).setTeamId(null);
        }
        return list;
    }
}
