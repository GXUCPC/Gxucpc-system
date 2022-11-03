package cn.edu.gxu.gxucpcsystem.extend;


import cn.edu.gxu.gxucpcsystem.domain.MapperContest;
import cn.edu.gxu.gxucpcsystem.service.MapperContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 5:04 PM
 */

@Slf4j
@Component
public class RunnableSpider implements Runnable{
    @Value("${scoreBoardSpider}")
    private String path;

    @Autowired
    MapperContestService mapperContestService;
    public MapperContest mapperContest;
    public int status = 0;
    @Override
    public void run() {
        try{
            log.info("开启爬虫脚本:" + path);
            String[] cmd = new String[]{"python3",path};
            Runtime.getRuntime().exec(cmd);
            mapperContest.setUpdateTime(System.currentTimeMillis());
            mapperContestService.updatePython(mapperContest);
        }catch (Exception e){
            log.error("爬虫出现异常: " + e.getMessage());
            this.status = 0;
        }

    }
}
