package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 客户关系管理
 */
@Controller
@RequestMapping("/custManage")
public class CustomerManageController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CustomerManageController.class);

    /**
     * 维修保养记录管理
     */
    @RequiresAuthentication
    @RequestMapping(value = "maintainrecordIndex", method = RequestMethod.GET)
    public String maintainrecordIndex() {
        logger.info("跳转到维修保养记录管理");
        return "custManage/maintainrecord";
    }

    /**
     * 维修保养明细管理
     */
    @RequiresAuthentication
    @RequestMapping(value = "maintaindetailsIndex", method = RequestMethod.GET)
    public String maintaindetailsIndex() {
        logger.info("跳转到维修保养明细管理");
        return "custManage/maintaindetails";
    }

    /**
     * 维修保养提醒
     */
    @RequiresAuthentication
    @RequestMapping(value = "maintainremindIndex", method = RequestMethod.GET)
    public String maintainremindIndex() {
        logger.info("跳转到维修保养提醒");
        return "custManage/maintainremind";
    }

    /**
     * 短信群发提醒
     */
    @RequiresAuthentication
    @RequestMapping(value = "messagesendIndex", method = RequestMethod.GET)
    public String messagesendIndex() {
        logger.info("跳转到短信群发提醒");
        return "custManage/messagesend";
    }


    /**
     * 投诉管理
     */
    @RequiresAuthentication
    @RequestMapping(value = "complaintIndex", method = RequestMethod.GET)
    public String complaintIndex() {
        logger.info("跳转到投诉管理");
        return "custManage/complaint";
    }

    /**
     * 跟踪回访管理
     */
    @RequiresAuthentication
    @RequestMapping(value = "tracklistIndex", method = RequestMethod.GET)
    public String tracklistIndex() {
        logger.info("跳转到跟踪回访管理");
        return "custManage/tracklist";
    }

    /**
     * 微信公众号查看
     */
    @RequestMapping(value = "wechatpublicIndex", method = RequestMethod.GET)
    public String wechatpublicIndex() {
        logger.info("跳转到微信公众号查看");
        return "custManage/wechatpublic";
    }

}
