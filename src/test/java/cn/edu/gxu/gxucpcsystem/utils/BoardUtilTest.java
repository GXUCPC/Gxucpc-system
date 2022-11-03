package cn.edu.gxu.gxucpcsystem.utils;

import cn.edu.gxu.gxucpcsystem.domain.AcMessage;
import org.junit.jupiter.api.Test;

public class BoardUtilTest {
    @Test
    public void test() {
        String str = "[{'label': 'A', 'problem_id': '1', 'num_judged': 1, 'num_pending': 0, 'solved': true, 'first_to_solve': false, 'time': 1277}, {'label': 'B', 'problem_id': '4', 'num_judged': 2, 'num_pending': 0, 'solved': true, 'first_to_solve': false, 'time': 1295}, {'label': 'C', 'problem_id': '7', 'num_judged': 2, 'num_pending': 0, 'solved': true, 'first_to_solve': true, 'time': 1337}, {'label': 'D', 'problem_id': '9', 'num_judged': 2, 'num_pending': 0, 'solved': true, 'first_to_solve': false, 'time': 1304}, {'label': 'E', 'problem_id': '10', 'num_judged': 4, 'num_pending': 0, 'solved': true, 'first_to_solve': false, 'time': 1316}, {'label': 'F', 'problem_id': '11', 'num_judged': 1, 'num_pending': 0, 'solved': true, 'first_to_solve': false, 'time': 5151}]";
        System.out.println(BoardUtil.parseAcMessage(str, AcMessage.class));
    }
}
