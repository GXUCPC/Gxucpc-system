package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.LqInformationDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.ImageMedal;
import cn.edu.gxu.gxucpcsystem.domain.LqPlayer;
import cn.edu.gxu.gxucpcsystem.utils.ExcelHandle;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class LanQiaoService {
    @Resource
    private LqInformationDao lqInformationDao;
    @Resource
    private ContestDao contestDao;
    @Resource
    private ImageMedalService imageMedalService;

    public Re addPlay(LqPlayer lqPlayer) {
        if(lqInformationDao.insert(lqPlayer) == 1) {
            return new Re(Code.STATUS_OK, null, "报名成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "系统异常");
    }

    public byte[] downloadForms(HttpServletResponse request, Integer id) throws IOException {
        List<LqPlayer> players = lqInformationDao.getPlayersByContent(id);
        Contest contest = contestDao.select(id);
        if(contest == null) {
            return null;
        }
        Map<String, byte[]> imgs = Maps.newHashMap();
        for(LqPlayer lqPlayer: players) {
            ImageMedal medal = imageMedalService.queryFile(lqPlayer.getImgURI());
            imgs.put(lqPlayer.getImgURI(), medal.getFiles());
        }

        ExcelHandle.exportExcelLQ(request, players, imgs,"报名表", contest.getName(), 15);
        return null;
    }
}
