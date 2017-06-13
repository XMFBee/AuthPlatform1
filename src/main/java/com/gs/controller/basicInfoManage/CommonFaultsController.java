package com.gs.controller.basicInfoManage;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常见故障管理
 * Created by yaoyong on 2017/4/18.
 */
@Controller
@RequestMapping("/commonFaults")
public class CommonFaultsController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CommonFaultsController.class);
}
