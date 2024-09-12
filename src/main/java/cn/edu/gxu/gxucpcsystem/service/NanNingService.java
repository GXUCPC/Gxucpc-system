package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.dao.mysql.ContestDao;
import cn.edu.gxu.gxucpcsystem.dao.mysql.NnInformationDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.NnTeam;
import cn.edu.gxu.gxucpcsystem.utils.ExcelHandle;
import cn.edu.gxu.gxucpcsystem.utils.Re;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class NanNingService {
    @Resource
    private NnInformationDao nnInformationDao;
    @Resource
    private ContestDao contestDao;

    public Re addTeam(NnTeam team) {
        if (nnInformationDao.insert(team) == 1) {
            return new Re(Code.STATUS_OK, null, "报名成功");
        }
        return new Re(Code.DATABASE_ERROR, null, "系统异常");
    }

    public byte[] downloadForms(HttpServletResponse response, Integer id) throws IOException {
        List<NnTeam> teams = nnInformationDao.getTeamsByContent(id);
        Contest contest = contestDao.select(id);
        if(contest == null) {
            return null;
        }

        ExcelHandle.exportExcelNanNing(response, teams,"报名表", contest.getName(), 15);
        return null;
    }
}
