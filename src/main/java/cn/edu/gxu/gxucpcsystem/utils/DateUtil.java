package cn.edu.gxu.gxucpcsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sct
 * @date 2022/8/1
 */
public class DateUtil {
    public static String convertTimestamp2Date(Long timestamp, String pattern) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(new Date(timestamp));
    }

}
