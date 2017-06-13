package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.User;
import com.gs.common.util.SessionUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by WangGenshen on 5/17/16.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IndexController.class);

    /**
     * 后台主页
     */
    @RequestMapping(value = "backstageIndex", method = RequestMethod.GET)
    public ModelAndView backstageHome(HttpSession session) {
        ModelAndView mav = new ModelAndView("backstage/index");
        if(SessionUtil.isLogin(session)) {
            mav.addObject("user",(User) session.getAttribute("user"));
        }
        return mav;
    }

    /**
     * 前台主页
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView backstagefrontHome() {
        ModelAndView mav = new ModelAndView("Frontpage/home");
        return mav;
    }

    @RequestMapping(value = "redirect_index",method = RequestMethod.GET)
    public String redirectHome(Model model) {
        model.addAttribute("redirect", "redirect");
        return "Frontpage/home";
    }

}
