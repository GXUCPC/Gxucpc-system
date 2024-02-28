package cn.edu.gxu.gxucpcsystem.extend;


import cn.edu.gxu.gxucpcsystem.domain.Board;
import cn.edu.gxu.gxucpcsystem.domain.MapperContest;
import cn.edu.gxu.gxucpcsystem.service.BoardService;
import cn.edu.gxu.gxucpcsystem.service.MapperContestService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 5:04 PM
 */

@Slf4j
@Component
public class RunnableSpider implements Runnable {
    @Value("${scoreBoardSpider}")
    private String path;

    @Autowired
    MapperContestService mapperContestService;
    @Autowired
    BoardService boardService;


    public MapperContest mapperContest;
    public int status = 0;

    @Override
    public void run() {
        MapperContest contest = mapperContestService.queryPython();
        if (contest.getFaultTime() >= 10) return;
        try {
            log.info("开启更新任务");
            boardService.updateBoard(contest.getId(), domjudgeAPI(contest.getUrl()));
        } catch (Exception e) {
            log.error("爬虫出现异常: " + e.getMessage());
            this.status = 0;
            mapperContest.setFaultTime(mapperContest.getFaultTime() + 1);
        }
        mapperContest.setUpdateTime(System.currentTimeMillis());
        mapperContestService.updatePython(mapperContest);

    }

    private List<Board> domjudgeAPI(String api) throws IOException {
        List<Board> boards = Lists.newArrayList();
        URL url = new URL(api);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // 获取API响应结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonString = response.toString();
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            JSONArray rowsArray = jsonObject.getJSONArray("rows");
            for (int i = 0; i < rowsArray.size(); i++) {
                JSONObject rowObject = rowsArray.getJSONObject(i);
                String problems = rowObject.getString("problems");
                int rank = rowObject.getIntValue("rank");
                int teamId = rowObject.getIntValue("team_id");
                int numSolved = rowObject.getJSONObject("score").getIntValue("num_solved");
                int totalTime = rowObject.getJSONObject("score").getIntValue("total_time");
                Board board = new Board();
                board.setRank(rank);
                board.setTeamId(teamId);
                board.setNumSolved(numSolved);
                board.setTotalTime(totalTime);
                board.setContent(problems);

                boards.add(board);
            }
        } else {
            log.error("DomJudge API ERROR: Error code=connection.getResponseCode()");
        }
        // 断开连接
        connection.disconnect();
        return boards;
    }
}
