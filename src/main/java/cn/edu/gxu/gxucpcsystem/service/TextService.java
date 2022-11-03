package cn.edu.gxu.gxucpcsystem.service;

import cn.edu.gxu.gxucpcsystem.controller.entity.PagesEntity;
import cn.edu.gxu.gxucpcsystem.dao.mysql.TextDao;
import cn.edu.gxu.gxucpcsystem.domain.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sct
 * @date 2022/8/15
 */
@Service
public class TextService {
    @Autowired
    TextDao textDao;

    public Text getByID(Integer id) {
        List<Text> queryR = textDao.getByID(id);
        if (queryR.isEmpty()) {
            return null;
        }
        return queryR.get(0);
    }

    public PagesEntity getByType(String type, Integer currentPage, Integer numberPerPage) {
        return new PagesEntity(textDao.getByType(type, (currentPage - 1) * numberPerPage, numberPerPage), textDao.countType(type));
    }

    public PagesEntity getAll(Integer currentPage, Integer numberPerPage) {
        return new PagesEntity(textDao.getAll((currentPage - 1) * numberPerPage, numberPerPage), textDao.countAll());
    }

    public Boolean insert(Text text, String author) {
        text.setTime(System.currentTimeMillis());
        return textDao.insert(text.getType(), author, text.getTime(), text.getContent(), text.getTitle()) == 1;
    }

    public Boolean updateByID(Text text) {
        return textDao.updateByID(text.getId(), text.getType(), text.getAuthor(), text.getTime(), text.getContent()) == 1;
    }

    public Boolean deleteByID(Integer id) {
        return textDao.deleteByID(id) == 1;
    }
}
