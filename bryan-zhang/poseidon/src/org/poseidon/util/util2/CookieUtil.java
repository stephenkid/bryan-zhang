package org.poseidon.util.util2;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtil
 * 
 * @author 赵星
 * @version 创建时间：Mar 16, 2009 2:11:39 PM
 */
public abstract class CookieUtil {

    /**
     * 根据cookie名,cookie值,设置一个cookie添加至response对象中
     * @param response
     *            HttpServletResponse
     * @param name
     *            cookie名
     * @param value
     *            cookie值
     */
     
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /**
     * 根据cookie名获取该cookie
     * @param request
     *            HttpServletRequest
     * @param name
     *            cookie名
     * @return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        return null;
    }

    /**
     * 清除response中对应cookie
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param name
     *            cookie名
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                cookies[i].setMaxAge(0);
                cookies[i].setValue(null);
                cookies[i].setDomain(".9you.com");
                response.addCookie(cookies[i]);
                break;
            }
        }
    }

    /**
     * 清除response中所有cookie
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     */
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setValue(null);
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
    }
}
