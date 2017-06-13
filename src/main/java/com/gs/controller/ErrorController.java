package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/error")
public class ErrorController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ErrorController.class);

    /**
     * 404
     */
    @RequestMapping(value = "notFound", method = RequestMethod.GET)
    public String notFound() {
        logger.info("404错误, 页面未找到");
        return "error/notFound";
    }

    /**
     * 500
     */
    @RequestMapping(value = "serverError", method = RequestMethod.GET)
    public String serverError() {
        logger.info("500错误, 服务器内部错误");
        return "error/serverError";
    }

    /**
     * 无角色
     */
    @RequestMapping(value = "notRole", method = RequestMethod.GET)
    public String notRole() {
        logger.info("不拥有角色");
        return "error/notRole";
    }
}
