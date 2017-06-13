package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 小蜜蜂 on 2017-04-11.
 * 维修保养接待管理页面跳转
 */
@Controller
@RequestMapping("/maintenanceReception")
public class MaintenanceReceptionController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintenanceReceptionController.class);

    /**
     * 接待登记管理
     * @return
     */
    @RequiresAuthentication
//    @RequiresRoles(value={"系统超级管理员", "系统普通管理员"}, logical= Logical.OR)
    @RequestMapping(value="reception",method= RequestMethod.GET)
    public String reception() {
       logger.info("跳转到接待登记管理页面");
       return "maintenanceReceptionManage/reception";
    }

    /**
     * 维修保养明细管理
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("subsidiary")
    public String subsidiary() {
        logger.info("跳转到维修保养明细管理");
        return "maintenanceReceptionManage/subsidiary";
    }

}
