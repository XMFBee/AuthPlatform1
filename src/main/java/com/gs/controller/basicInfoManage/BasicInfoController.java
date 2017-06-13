package com.gs.controller.basicInfoManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 基本信息管理跳转Controller， 姚勇
 */
@Controller
@RequestMapping("/basicInfo")
public class BasicInfoController {

    private Logger logger = (Logger) LoggerFactory.getLogger(BasicInfoController.class);

    /**
     * 公司信息管理
     *
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "companyInfo", method = RequestMethod.GET)
    public String companyInfoIndex() {
        logger.info("跳转到公司信息管理页面");
        return "basicInfoManage/companyInfo";
    }


    /**
     * 汽车品牌管理
     *
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "carBrand")
    public String carBrandIndex() {
        logger.info("跳转到汽车品牌管理页面");
        return "basicInfoManage/carBrand";
    }

    /**
     * 车型管理
     *
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "carModel")
    public String carModelIndex() {
        logger.info("跳转到车型管理页面");
        return "basicInfoManage/carModel";
    }

    /**
     * 汽车颜色管理
     *
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "carColor")
    public String carColorIndex() {
        logger.info("跳转到汽车颜色管理页面");
        return "basicInfoManage/carColor";
    }

    /**
     * 汽车车牌管理
     *
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "carPlate")
    public String carPlateIndex() {
        logger.info("跳转到汽车车牌管理页面");
        return "basicInfoManage/carPlate";
    }

    /**
     * 保养项目管理
     */
    @RequiresAuthentication
    @RequestMapping(value = "upkeepItem")
    public String upkeepItemIndex() {
        logger.info("跳转到保养项目管理页面");
        return "basicInfoManage/maintainItem";
    }

    /**
     * 维修项目管理
     */
    @RequiresAuthentication
    @RequestMapping(value = "fixItem")
    public String maintainItemIndex() {
        logger.info("跳转到维修项目管理页面");
        return "basicInfoManage/fixItem";
    }

    /**
     * 常见故障管理
     */
    @RequiresAuthentication
    @RequestMapping(value = "commonFaults")
    public String commonFaultsIndex() {
        logger.info("跳转到常见故障管理页面");
        return "basicInfoManage/commonFaults";
    }

}
