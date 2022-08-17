package cn.edu.gxu.gxucpcsystem.Service;

import cn.edu.gxu.gxucpcsystem.dao.mysql.RedisDao;
import cn.edu.gxu.gxucpcsystem.domain.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sct
 * @date 2022/8/17
 */
@Service
public class RedisService {
    @Autowired
    RedisDao redisDao;

    public List<Redis> getImagesURL() {
        return redisDao.getByKey("URL");
    }

    public Redis getICP() {
        List<Redis> redis = redisDao.getByKey("ICP");
        if(redis.isEmpty()) {
            return null;
        }
        return redis.get(0);
    }

    public Boolean insert(String key, String value) {
        if(key.equals("ICP")) {
            redisDao.deleteByKey("ICP");
            return redisDao.insert(key, value) == 1;
        } else if(key.equals("URL")) {
            return redisDao.insert(key, value) == 1;
        } else return false;
    }

    public Boolean deleteImages(String value) {
        return redisDao.deleteByValue(value) >= 1;
    }
}
