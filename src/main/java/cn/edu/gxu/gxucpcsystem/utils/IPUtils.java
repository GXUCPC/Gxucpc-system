package cn.edu.gxu.gxucpcsystem.utils;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {
    /**
     * Nginx默认增加的IP地址头
     */
    public static final String HEADER_X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * 内部微服务之间调用增加的IP地址头
     */
    public static final String HEADER_X_REMOTE_USER_IP = "X-Remote-User-IP";

    /**
     * IP响应头的分割符号
     */
    private static final String IP_HEADER_DELIMITER = ",";

    public static String loadRemoteUserIP(HttpServletRequest request) {
        String xForwardedHeader = request.getHeader(HEADER_X_FORWARDED_FOR);
        // 先尝试从X-Forwarded-For获取
        if (xForwardedHeader != null && xForwardedHeader.trim().length() > 0) {
            String[] ips = xForwardedHeader.split(IP_HEADER_DELIMITER);
            return ips[0].trim();
        } else {
            // 尝试从内部自定义头上获取
            String internalIPHeader = request.getHeader(HEADER_X_REMOTE_USER_IP);
            if (null != internalIPHeader && internalIPHeader.trim().length() > 0) {
                return internalIPHeader.trim();
            } else {
                return request.getRemoteAddr();
            }
        }
    }

}
