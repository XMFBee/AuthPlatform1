package com.gs.common;

import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ArrayBin on 2017/3/20.
 */
public class Methods {

    public static final String DEFAULT_CODING = "utf-8";

    public static final String PATH_UPLOAD = "/WEB-INF/upload";

    /**
     * 根据当前日期自动创建存放文件的文件夹
     * @return
     */
    public static String createNewFolder() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }


    /**
     * 给文件取新名字
     * @return
     */
    public static String createName(String fileName) {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getRootPath(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/");
    }

    public static double division(double a ,double b){
        double num =a/b;

        DecimalFormat df = new DecimalFormat("#.0");

        return Double.valueOf(df.format(num));

    }

    public static int division(double a) {
        DecimalFormat df = new DecimalFormat("#");
        return Integer.valueOf(df.format(a));
    }

    /**
     * 上传图片的路径
     * @param
     * @return
     */
    public static String uploadPath(String type) {
        String folder = createNewFolder();
        String rootPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        String path = rootPath;
        if (type.equals("img")) {
            path += Constants.UPLOAD_IMAGES + folder;
        } else if (type.equals("video")) {
            path += Constants.UPLOAD_VIDEO + folder;
        } else if (type.equals("logo")){
            path += Constants.UPLOAD_LOGO + folder;
        } else if (type.equals("head")){
            path += Constants.UPLOAD_HEAD + folder;
        }
        path += "/";
        File file = new File(path);
        if (file.mkdirs()) {
            file.mkdirs();
        }
        return path;
    }

}
