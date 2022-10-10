package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-10 12:30 PM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapContest {
    int      id;
    int      register_contest;
    int      domjudge_contest;
    boolean  is_spider;
    Long     update_time;
    String   url;
    int      fault_time;
    Long     contest_time;
    String   contestName;

}
