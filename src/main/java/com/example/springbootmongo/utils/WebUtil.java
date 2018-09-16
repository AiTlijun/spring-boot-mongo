package com.example.springbootmongo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author lijun web工具类
 */
public class WebUtil {
    private final static Logger logger = LoggerFactory.getLogger(WebUtil.class);


    /**
     * 异步方式 ，给 客户端写json数据
     *
     * @param response
     * @param msg
     */
    public static void writeJsonToClient(HttpServletResponse response, String msg) {
        writeStr(response, msg, "text/json");
    }

    /**
     * 异步方式给客户端些 文本数据
     *
     * @param response
     * @param msg
     */
    public static void writeStrToClient(HttpServletResponse response, String msg) {
        writeStr(response, msg, "text/html");
    }

    public static void writeStr(HttpServletResponse response, String msg, String type) {
        response.setContentType(type);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(msg);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            logger.error("网络异常", e);
        }
    }

    /**
     * 获取一个字符串中中文的数量
     *
     * @param str
     * @return
     */
    public static int getChineseNumber(String str) {
        Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
        Matcher matcher = pattern.matcher(str);
        int number = 0;
        while (matcher.find()) {
            number++;
        }

        return number;
    }

    public static boolean checkNickName(String nickname) {
        if (nickname != null && !nickname.trim().equals("")) {
            int chineseNumber = WebUtil.getChineseNumber(nickname);
            if (chineseNumber > 0) {
                return false;
            }
            // 4-12 4-10 8
            Pattern pattern = Pattern.compile("[\u4E00-\u9FA5a-zA-Z0-9]{4," + (12 - chineseNumber) + "}");
            Matcher matcher = pattern.matcher(nickname);
            return matcher.matches();
        }
        return false;
    }

    public static boolean checkPassWord(String password) {
        if (password != null && !password.trim().equals("") && password.length() > 5 && password.length() < 17) {
            return true;
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches() && email.length() < 41;
    }

    public static boolean checkMobile(String mobile) {
        Pattern pattern = Pattern.compile(
                "^((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 校验最长为4个字的中文姓名
     *
     * @param realName
     * @return
     */
    public static boolean checkRealName(String realName) {
        String regex = "^[\u4e00-\u9fa5]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(realName);
        return matcher.matches();
    }

    /**
     * 防止SQL注入和XSS攻击
     *
     * @param
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '&':
                    sb.append('＆');// 全角
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '#':
                    sb.append('＃');// 全角井号
                    break;
                case ';':
                    sb.append('；');
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        String tempstr = sb.toString().toLowerCase();
        if (tempstr.indexOf("script") != -1) {
            sb.toString().replaceAll("script", "");
        }
        if (tempstr.indexOf("javascript") != -1) {
            sb.toString().replaceAll("javascript", "");
        }
        if (tempstr.indexOf("vbscript") != -1) {
            sb.toString().replaceAll("vbscript", "");
        }
        return sb.toString();
    }


    /**
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 校验图片文件
     *
     * @param
     * @return
     */


    public static boolean isIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        return ipAddress;
    }
}
