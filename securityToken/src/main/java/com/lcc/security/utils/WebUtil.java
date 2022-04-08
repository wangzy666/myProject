package com.lcc.security.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/6 15:03
 */
public class WebUtil {
    public static String renderString(HttpServletResponse response,String str){
        try {
            response.setStatus(200);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
