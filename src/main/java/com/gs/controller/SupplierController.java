package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 供应商管理页面跳转
 */

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    private Logger logger = (Logger) LoggerFactory.getLogger(SystemManageController.class);

    /**
     * 供应商类型管理
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "supplierType", method = RequestMethod.GET)
    public String supplierType() {
        logger.info("供应商类型管理");
        return "supplier/supplierType";
    }

    /**
     * 供应商管理
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "supplierInformation", method = RequestMethod.GET)
    public String supplierInformation() {
        logger.info("供应商管理");
        return "supplier/supplierInFormation";
    }

}
