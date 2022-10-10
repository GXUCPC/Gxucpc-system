package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.dao.mysql.MapContestDao;
import cn.edu.gxu.gxucpcsystem.domain.MapContest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-10 2:11 PM
 */

@Service
public class MapContestService {
    @Autowired
    MapContestDao mapContestDao;
    public boolean addMapContest(MapContest mapContest){
        return mapContestDao.addMapContest(mapContest) == 1;
    }
    public boolean updateMapContest(MapContest mapContest){
        return mapContestDao.updateMapContest(mapContest) == 1;
    }
    public boolean delMapContest(Integer id){
        return mapContestDao.delMapContest(id) == 1;
    }
    public PagesEntity selectByPage(Integer currentPage, Integer numberPerPage) {
        return new PagesEntity(mapContestDao.selectQuery((currentPage - 1) * numberPerPage, numberPerPage), mapContestDao.getCount());
    }
    public boolean updateSpider(int id,boolean ok){
        return mapContestDao.isSpider(ok,id) == 1;
    }
}
