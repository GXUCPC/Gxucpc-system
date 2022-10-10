package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-10 1:00 PM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBoard {
    int contest_id;
    int id;
    String content;
    int rank;
    int team_id;
    Domjudge domjudge;
}
