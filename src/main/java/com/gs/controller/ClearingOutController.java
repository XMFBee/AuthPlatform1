package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 结算出厂, 张文星
 */
@Controller
@RequestMapping("/clearingOut")
public class ClearingOutController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ClearingOutController.class);

    /**
     * 提车提醒
     */
    @RequiresAuthentication
    @RequestMapping(value = "carRemindIndex", method = RequestMethod.GET)
    public String carRemind() {
        logger.info("跳转到提车提醒页面");
        return "clearingOut/carRemind";
    }

    /**
     * 收费单据
     */
    @RequiresAuthentication
    @RequestMapping(value = "chargeDocumentsIndex", method = RequestMethod.GET)
    public String chargeDocuments() {
        logger.info("跳转到收费单据页面");
        return "clearingOut/chargeDocuments";
    }

}
