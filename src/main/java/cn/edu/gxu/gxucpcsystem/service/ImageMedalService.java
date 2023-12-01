package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.domain.ImageMedal;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageMedalService {

    @Resource
    private MongoTemplate mongoTemplate;

    public void addMedal(ImageMedal medal) {
        mongoTemplate.save(medal);
    }

    public ImageMedal queryFile(String imgURI) {
        Query query = new Query(Criteria.where("fileURI").is(imgURI));
        return mongoTemplate.findOne(query, ImageMedal.class);
    }
}
