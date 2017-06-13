package com.gs.common.web;

import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;

/**
 * Created by WangGenshen on 7/29/16.
 */
public class ServletContextUtil {

    public static ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    public static String getContextPath() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getContextPath();
    }
}
