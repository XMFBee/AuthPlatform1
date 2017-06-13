package com.gs.controller;

import com.gs.common.bean.ControllerResult;
import com.gs.service.RemindService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/25.
 */
@Controller
@RequestMapping("/remind")
public class RemindController {

    @Resource
    private RemindService remindService;

    @ResponseBody
    @RequestMapping(value = "deleteRemind",method = RequestMethod.POST)
    public ControllerResult deleteRemind(String remindId){
        if(remindId!=null&&!remindId.equals("")){
            remindService.deleteRemind(remindId);
            return ControllerResult.getSuccessResult("(๑•̀ㅂ•́)و✧已经撤回该条消息");
        }else{
            return ControllerResult.getFailResult("撤回失败");
        }
    }
}
