package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 预约维护保养系统
 */
@Controller
@RequestMapping("/customerBooking")
public class CustomerBookingController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CustomerBookingController.class);

    /**
     * 模块管理
     */
    @RequestMapping(value = "bookingManagement", method = RequestMethod.GET)
    public String bookingManagement() {
        logger.info("跳转到模块管理页面");
        return "customerBooking/bookingManagement";
    }

    /**
     * 权限管理
     */
    @RequestMapping(value = "weChatReservation", method = RequestMethod.GET)
    public String weChatReservation() {
        logger.info("跳转到权限管理页面");
        return "customerBooking/weChatReservation";
    }

    /**
     * 权限分配
     */
    @RequestMapping(value = "phoneReservation", method = RequestMethod.GET)
    public String phoneReservation() {
        logger.info("跳转到权限分配页面");
        return "customerBooking/phoneReservation";
    }

}
