package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.domain.Medal;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-09-06 7:53 AM
 */

@Service
public class MedalService {

    @Resource
    private MongoTemplate mongoTemplate;

    public void addMedal(Medal medal){
        mongoTemplate.save(medal);
    }
    public boolean  addList(List<Medal> lsMedal){
        for(Medal medal: lsMedal){
            if(medal.getSize() >1024) return false;
        }
        mongoTemplate.insert(lsMedal);
        return true;
    }
    private List<Medal>queryFile(String fileName ,String contestId){
        int id = Integer.parseInt(contestId);
        Query query = new Query(Criteria.where("contestId").is(id).and("fileName").is(fileName));
        List<Medal> ans = mongoTemplate.find(query,Medal.class);
        return ans;
    }
    public byte[] getFile(String fileName,String contestId){
        List<Medal> ans = queryFile(fileName,contestId);
        Medal medal = ans.get(0);
        return medal.getFiles();
    }
}
