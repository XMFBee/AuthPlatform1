package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/4/11.
 */
@Controller
@RequestMapping("maintenanceController")
public class MaintenanceController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintenanceController.class);

    @RequestMapping(value = "maintenanceIndex",method = RequestMethod.GET)
    public String maintenanceIndex(){
        logger.info("车辆维修保养进度管理");
        return "maintenance/maintenance";
    }

    @RequestMapping(value = "scheduleIndex",method = RequestMethod.GET)
    public String scheduleIndex(){
        logger.info("微信查看维修保养进度");
        return "maintenance/schedule";
    }
}
