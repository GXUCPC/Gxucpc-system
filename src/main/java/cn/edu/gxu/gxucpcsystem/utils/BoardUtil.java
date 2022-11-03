package cn.edu.gxu.gxucpcsystem.utils;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

public class BoardUtil {
    public static List<?> parseAcMessage(String acMessage, Class<?> clazz) {
        acMessage = acMessage.replace("True", "true");
        acMessage = acMessage.replace("False", "false");
        return JSONArray.parseArray(acMessage, clazz);
    }
}
