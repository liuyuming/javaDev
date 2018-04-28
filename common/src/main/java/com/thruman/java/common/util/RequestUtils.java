package com.thruman.java.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author niexiang
 * @Description
 * @create 2018-04-03 10:40
 **/
public class RequestUtils {

    public static Integer getUserID(HttpServletRequest request){
        if (request.getSession().getAttribute("userID") != null) return (Integer) request.getSession().getAttribute("userID");

        return null;
    }


    public static String getParams(HttpServletRequest request){
        try {
            StringBuffer result = new StringBuffer();
            Enumeration enu=request.getParameterNames();
            while(enu.hasMoreElements()){
                String paraName=(String)enu.nextElement();
                if (!(paraName.equals("password")))
                    result.append(paraName).append(": ").append(request.getParameter(paraName)).append(" ");
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(final HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        setIP(ip,request);
        return ip;
    }


    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getIpAddress() {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        HttpServletRequest request = getHttpRequest();
        String ip = request.getHeader("X-Forwarded-For");
        setIP(ip,request);
        return ip;
    }

    private static void setIP(String ip, HttpServletRequest request) {
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
    }


    public static HttpServletRequest getHttpRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra.getRequest();
    }


}
