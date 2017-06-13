package com.gs.controller;

import com.gs.bean.ceshi;
import com.gs.common.bean.ControllerResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 *  boostrap table Controller
 */
@Controller
@RequestMapping("/vaildate")
public class VaildateController {

    @RequestMapping(value = "index" ,method = RequestMethod.GET)
    public ModelAndView tableIndex(){
       ModelAndView mav = new ModelAndView("jqueryVaildate/vaildate");
       return mav;
    }

    @ResponseBody
    @RequestMapping(value = "query" ,method = RequestMethod.POST)
    public List<ceshi> query(){
       return null;
    }

    @ResponseBody
    @RequestMapping(value = "addFile", method = RequestMethod.POST)
    public ControllerResult addFile(@RequestParam("txt_file") MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        System.out.print(fileName);
        try{
            InputStream fileInputStream = file.getInputStream();
        } catch (Exception e){
            e.printStackTrace();
            return ControllerResult.getFailResult("上传失败");
        }
        return ControllerResult.getSuccessResult("上传成功");
    }
}
