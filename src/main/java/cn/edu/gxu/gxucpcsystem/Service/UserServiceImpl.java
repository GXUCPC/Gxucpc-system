package cn.edu.gxu.gxucpcsystem.Service;

/**
 * @author MaoMao
 * @Description MongoDB 数据操作Service 接口实现
 * @create 2022-06-21 10:34 AM
 */
import cn.edu.gxu.gxucpcsystem.dao.mongodb.IUserService;
import cn.edu.gxu.gxucpcsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userService")//可以不用给定value,看个人习惯
public class UserServiceImpl implements IUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存用户
     * @param user 返回保存的用户
     */
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    /**
     * 根据名字查询用户
     * @param name 名字
     * @return 用户
     */
    public List<User> findUserByName(String name) {
        Query query = new Query(Criteria.where("userName").is(name));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

}