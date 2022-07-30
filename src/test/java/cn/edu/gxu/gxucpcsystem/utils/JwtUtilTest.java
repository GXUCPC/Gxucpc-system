package cn.edu.gxu.gxucpcsystem.utils;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

/**
 * @author Sct
 * @date 2022/7/30
 */
public class JwtUtilTest {
    @Test
    public void tokenTest() {
        System.out.println(JwtUtil.createJWT(Code.JWT_KEY, Code.TTL_MILLIS, "root", "127.0.0.1", "Super Admin", "e10adc3949ba59abbe56e057f20f883e"));
    }
    @Test
    public void parseToken() {
        Claims claims = JwtUtil.parseJWT(Code.JWT_KEY,"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTdXBlciBBZG1pbiIsImlwIjoiMTI3LjAuMC4xIiwidXNlclR5cGUiOiJTdXBlciBBZG1pbiIsImV4cCI6MTY1OTc3ODI0MywiaWF0IjoxNjU5MTczNDQzLCJqdGkiOiI5N2FlMWNiZS00ZTM2LTQwYjAtOTI0MS1lNjdiYzUzMGM0NjIiLCJ1c2VybmFtZSI6InJvb3QifQ.B--UEeC-bUbzdzuWaDNWS0oZMkjfaPo4LcPovBRXbI0");
        System.out.println(claims);
    }
}
