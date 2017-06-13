package com.gs.controller;

import com.gs.bean.ceshi;
import com.gs.common.bean.ControllerResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *  boostrap table Controller
 */
@Controller
@RequestMapping("/table")
public class TableController {

    @RequestMapping(value = "tableIndex" ,method = RequestMethod.GET)
    public ModelAndView tableIndex(){
       ModelAndView mav = new ModelAndView("table/table");
       return mav;
    }

    @ResponseBody
    @RequestMapping(value = "query" ,method = RequestMethod.POST)
    public List<ceshi> query(){
        ceshi u = new ceshi();
        u.setId(1);
        u.setName("aa");
        u.setPrice("11");
        ceshi u1 = new ceshi();
        u1.setId(2);
        u1.setName("aa");
        u1.setPrice("11");
        ceshi u2 = new ceshi();
        u2.setId(3);
        u2.setName("aa");
        u2.setPrice("11");
        ceshi u3 = new ceshi();
        u3.setId(4);
        u3.setName("aa");
        u3.setPrice("11");
        ceshi u4 = new ceshi();
        u4.setId(1);
        u4.setName("aa");
        u4.setPrice("11");
        ceshi u5 = new ceshi();
        u5.setId(1);
        u5.setName("aa");
        u5.setPrice("11");
        ceshi u6 = new ceshi();
        u6.setId(1);
        u6.setName("aa");
        u6.setPrice("11");
        ceshi u7 = new ceshi();
        u7.setId(1);
        u7.setName("aa");
        u7.setPrice("11");
        ceshi u8 = new ceshi();
        u8.setId(1);
        u8.setName("aa");
        u8.setPrice("11");
        ceshi u9 = new ceshi();
        u9.setId(1);
        u9.setName("aa");
        u9.setPrice("11");
        ceshi u10 = new ceshi();
        u10.setId(1);
        u10.setName("aa");
        u10.setPrice("11");
        ceshi u11 = new ceshi();
        u11.setId(1);
        u11.setName("aa");
        u11.setPrice("11");
        List<ceshi> uList = new ArrayList<ceshi>();
        uList.add(u);
        uList.add(u1);
        uList.add(u2);
        uList.add(u3);
        uList.add(u4);
        uList.add(u5);
        uList.add(u6);
        uList.add(u7);
        uList.add(u8);
        uList.add(u9);
        uList.add(u10);
        uList.add(u11);
        return uList;
    }

    @ResponseBody
    @RequestMapping(value = "queryType" ,method = RequestMethod.GET)
    public List<ceshi> queryType(){
        ceshi u = new ceshi();
        u.setId(1);
        u.setName("aa");
        u.setPrice("11");
        ceshi u1 = new ceshi();
        u1.setId(2);
        u1.setName("aa");
        u1.setPrice("11");
        ceshi u2 = new ceshi();
        u2.setId(3);
        u2.setName("aa");
        u2.setPrice("11");
        ceshi u3 = new ceshi();
        u3.setId(4);
        u3.setName("aa");
        u3.setPrice("11");
        ceshi u4 = new ceshi();
        u4.setId(1);
        u4.setName("aa");
        u4.setPrice("11");
        ceshi u5 = new ceshi();
        u5.setId(1);
        u5.setName("aa");
        u5.setPrice("11");
        ceshi u6 = new ceshi();
        u6.setId(1);
        u6.setName("aa");
        u6.setPrice("11");
        ceshi u7 = new ceshi();
        u7.setId(1);
        u7.setName("aa");
        u7.setPrice("11");
        ceshi u8 = new ceshi();
        u8.setId(1);
        u8.setName("aa");
        u8.setPrice("11");
        ceshi u9 = new ceshi();
        u9.setId(1);
        u9.setName("aa");
        u9.setPrice("11");
        ceshi u10 = new ceshi();
        u10.setId(1);
        u10.setName("aa");
        u10.setPrice("11");
        ceshi u11 = new ceshi();
        u11.setId(1);
        u11.setName("aa");
        u11.setPrice("11");
        List<ceshi> uList = new ArrayList<ceshi>();
        uList.add(u);
        uList.add(u1);
        uList.add(u2);
        uList.add(u3);
        uList.add(u4);
        uList.add(u5);
        uList.add(u6);
        uList.add(u7);
        uList.add(u8);
        uList.add(u9);
        uList.add(u10);
        uList.add(u11);
        return uList;
    }

    @ResponseBody
    @RequestMapping(value = "edit" ,method = RequestMethod.POST)
    public ControllerResult edit(ceshi cs){
        System.out.print(cs.getId()+"____________________________");
        return ControllerResult.getSuccessResult("修改成功");
    }
}
