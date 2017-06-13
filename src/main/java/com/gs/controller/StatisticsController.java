package com.gs.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * statistic modul page
 *
 * @author chengyan
 * @create 2017-04-11 16:13
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @RequiresAuthentication
    @RequestMapping("finance")
    public ModelAndView financePage(){
        ModelAndView mav = new ModelAndView("statistics/finance");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("maintaintype")
    public ModelAndView maintainTypePage(){
        ModelAndView mav = new ModelAndView("statistics/maintaintype");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("consume")
    public ModelAndView consumePage(){
        ModelAndView mav = new ModelAndView("statistics/consume");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("inventoryUsing")
    public ModelAndView inventoryUsingPage(){
        ModelAndView mav = new ModelAndView("statistics/inventoryUsing");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("inventoryUsing1")
    public ModelAndView inventoryUsingPage1(){
        ModelAndView mav = new ModelAndView("statistics/inventoryUsing1");
        return mav;
    }




    /**
     * 库存
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("repertory")
    public ModelAndView repertoryPage(){
        ModelAndView mav = new ModelAndView("statistics/repertory");
        return mav;
    }


    @RequiresAuthentication
    @RequestMapping("maintain")
    public ModelAndView maintainPage(){
        ModelAndView mav = new ModelAndView("statistics/maintain");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("pay")
    public ModelAndView payPage(){
        ModelAndView mav = new ModelAndView("statistics/pay");
        return mav;
    }


    @RequestMapping("vindicate")
    public ModelAndView vindicatePage(){
        ModelAndView mav = new ModelAndView("statistics/vindicate");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("client")
    public ModelAndView clientPage(){
        ModelAndView mav = new ModelAndView("statistics/client");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("stock")
    public ModelAndView stockPage(){
        ModelAndView mav = new ModelAndView("statistics/stock");
        return mav;
    }

    @RequiresAuthentication
    @RequestMapping("usematerials")
    public ModelAndView useMaterialsPage(){
        ModelAndView mav = new ModelAndView("statistics/usematerials");
        return mav;
    }
    @RequiresAuthentication
    @RequestMapping("workord")
    public ModelAndView workOrderPage(){
        ModelAndView mav = new ModelAndView("statistics/workord");
        return mav;
    }



    @RequestMapping("paysup")
    public ModelAndView paySupPage(){
        ModelAndView mav = new ModelAndView("statistics/paysup");
        return mav;
    }
    @RequestMapping("purchase")
    public ModelAndView purchasePage(){
        ModelAndView mav = new ModelAndView("statistics/purchase");
        return mav;
    }

    @RequestMapping("test")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView("statistics/test");
        return mav;
    }
}
