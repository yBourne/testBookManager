package com.ybourne.project.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    private static int maxAge = 60*60*24*7;

    public static void writeCookie(String key, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static String getCookies(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals(key)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void removeCookie(String key, HttpServletRequest request,
                                    HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        for (int i=0; i<(cookies==null ? 0 : cookies.length); i++){
            if ((key).equalsIgnoreCase(cookies[i].getName())){
                Cookie cookie = new Cookie(key, "");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
