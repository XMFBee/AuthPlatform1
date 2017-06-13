package com.gs.controller;

import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.Methods;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.SWFIconResult;
import com.gs.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Administrator on 2017-05-22.
 */

@Controller
@RequestMapping("/icon")
public class SWFIconUpload {
    @Resource
    private UserService userService;

    // TODO
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Object  uploadIcon(DefaultMultipartHttpServletRequest req, HttpSession session) {
        SWFIconResult result = new SWFIconResult();
        result.setSuccess(false);
        result.setMsg("修改头像失败!");
        result.setAvatarUrls(new ArrayList());
        User user = (User)session.getAttribute("user");
        String savePath = user.getUserIcon();
        if(user.getUserIcon() == null || user.getUserIcon().equals("")) {
            savePath = Constants.UPLOAD_HEAD + Methods.createNewFolder() + "/";
        }


//      p map = new HashMap();
//        map.put("code", 5);
//        map.put("type", 2);
//        map.put("content", ControllerResult.getFailResult("修改头像失败"));
        boolean bool = false;
        Iterator<String> fileNames = req.getFileNames();
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        while(fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile file = req.getFile(fileName);
            files.add(file);
        }
        if(fileSave(files, savePath ,  session)) {
            if(user.getUserIcon() == null || user.getUserIcon().equals("")) {
                int i = userService.updIcon(user.getUserId(), savePath+user.getUserId() + ".jpg");   // 设置头像
                if (i > 0) {
                    result.setSuccess(true);
                    result.setMsg("修改头像成功!");
                    user.setUserIcon(savePath+user.getUserId() + ".jpg");
                    result.setSourceUrl(savePath+user.getUserId() + ".jpg");
                }
            } else  {
                result.setSuccess(true);
                result.setMsg("修改头像成功!");
                user.setUserIcon(savePath);
                result.setSourceUrl(savePath);
            }
        }

        // Object {content: "Error #2032", code: 5, type: 2}
        return  result;
    }

    private boolean fileSave(List<MultipartFile> sourceFiles, String savePath,  HttpSession session) {
        User user = (User)session.getAttribute("user");
        byte[] temp = new byte[1024];
        int len = -1;
        String rootPath = session.getServletContext().getRealPath("/");
        try {
            File saveFile = null;
            if(user.getUserIcon() == null || user.getUserIcon().equals("")) {
                savePath = rootPath + "/" + savePath;
                File saveDir = new File(savePath);
                if(!saveDir.isDirectory()) {
                    saveDir.mkdirs();
                }
                saveFile = new File(savePath + user.getUserId() + ".jpg");
            } else {
                savePath = rootPath + "/" + savePath;
                saveFile = new File(savePath);
            }
            OutputStream fos = new FileOutputStream(saveFile);
            for(MultipartFile file : sourceFiles) {
                InputStream fis = file.getInputStream();
                while ((len = fis.read(temp)) != -1) {
                    fos.write(temp, 0, len);
                }
                fis.close();
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping("upload2")
    public void uploadIcon2() {
    }
}
