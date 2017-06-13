package com.gs.controller.basicInfoManage;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 维修项目管理
 * Created by yaoyong on 2017/4/18.
 */
@Controller
@RequestMapping("/fixltem")
public class FixltemController {

    private Logger logger = (Logger) LoggerFactory.getLogger(FixltemController.class);
}
