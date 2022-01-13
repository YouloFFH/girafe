package cc.fireflyhut.girafe.util;

import cc.fireflyhut.girafe.constants.Constants;
import cc.fireflyhut.girafe.constants.RegexConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * ip工具类
 */
public class IpUtil {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || Constants.UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || Constants.UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || Constants.UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || Constants.UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        if (Constants.LOCALHOST_ABNORMAL_IP.equals(ip)) {
            return Constants.LOCALHOST_IP;
        }
        if (StringUtils.isNotBlank(ip) && !ip.matches(RegexConstants.IP)) {
            return null;
        }
        return ip;
    }

}
