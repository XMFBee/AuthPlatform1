package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 财务管理页面跳转
 * Created by 小蜜蜂 on 2017-04-12.
 */
@Controller
@RequestMapping("/financialView")
public class FinancialViewController {

    private Logger logger = (Logger) LoggerFactory.getLogger(FinancialViewController.class);

    /**
     * 跳转至支出管理页面
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "payOutType", method = RequestMethod.GET)
    public String payOutType() {
        logger.info("跳转至支出类型管理页面");
        return "/financialControl/payOutType";
    }

    /**
     * 跳转至收入管理页面
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "incomeType", method = RequestMethod.GET)
    public String incomeType() {
        logger.info("跳转至收入类型管理页面");
        return "/financialControl/incomeType";
    }

    /**
     * 跳转至收入管理页面
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "piRecording", method = RequestMethod.GET)
    public String piRecording() {
        logger.info("跳转至收支记录管理页面");
        return "/financialControl/piRecording";
    }

    /**
     * 跳转至支出管理页面
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "payOut", method = RequestMethod.GET)
    public String payOut() {
        logger.info("跳转至支出管理页面");
        return "/financialControl/payOut";
    }

    /**
     * 跳转至收入管理页面
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "income", method = RequestMethod.GET)
    public String income() {
        logger.info("跳转至收入管理页面");
        return "/financialControl/income";
    }

    /**
     * 跳转至工资管理页面
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "salary", method = RequestMethod.GET)
    public String salary() {
        logger.info("跳转至工资管理页面");
        return "/financialControl/salary";
    }

    /**
     * 跳转至账单管理页面
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "bill", method = RequestMethod.GET)
    public String bill() {
        logger.info("跳转至账单管理页面");
        return "/financialControl/bill";
    }

}
