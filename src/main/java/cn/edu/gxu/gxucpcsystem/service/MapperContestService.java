package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.MapperContestDao;
import cn.edu.gxu.gxucpcsystem.domain.Contest;
import cn.edu.gxu.gxucpcsystem.domain.MapperContest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperContestService {
    @Autowired
    MapperContestDao mapperContestDao;

    public void stopPython() {
        mapperContestDao.deleteOldMapper();
    }

    public Boolean addPython(Contest contest) {
        return mapperContestDao.insertNewMapper(contest) == 1;
    }

    public Boolean updatePython(MapperContest contest) {
        return mapperContestDao.updateMapper(contest) == 1;
    }

    public MapperContest queryPython() {
        List<MapperContest> list = mapperContestDao.selectActive();
        if(list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
