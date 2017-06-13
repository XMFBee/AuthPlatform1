package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by XiaoQiao on 2017/4/11.
 */
@Controller
@RequestMapping("/accessories")
public class AccessoriesController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesController.class);

    @RequiresAuthentication
    @RequestMapping("type")
    public String accessories_type() {
        logger.info("跳转到配件分类管理页面");
        return "accessories/accessories_type";
    }

    @RequiresAuthentication
    @RequestMapping("accessories")
    public String accessories() {
        logger.info("跳转到库存管理页面");
        return "accessories/accessories";
    }

    @RequiresAuthentication
    @RequestMapping("buy")
    public String accessories_buy() {
        logger.info("跳转到配件采购管理页面");
        return "accessories/accessories_buy";
    }

    @RequiresAuthentication
    @RequestMapping("sale")
    public String accessories_sale() {
        logger.info("跳转到配件销售管理页面");
        return "accessories/accessories_sale";
    }
}
