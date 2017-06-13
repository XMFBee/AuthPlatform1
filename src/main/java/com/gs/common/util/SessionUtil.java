package com.gs.common.util;

import javax.servlet.http.HttpSession;

/**
 * 是否登录验证
 */
public class SessionUtil {

    /**
     * 是否登录
     */
    public static boolean isLogin(HttpSession session) {
        if(session.getAttribute("user")!=null){
            return true;
        }
        return false;
    }

    /**
     * 是否车主登录
     */
    public static boolean isOwnerLogin(HttpSession session) {
        if(session.getAttribute("frontUser")!=null){
            return true;
        }
        return false;
    }
}
