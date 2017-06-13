package com.gs.common.util;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * Created by WangGenshen on 5/26/16.
 */
public class FileUtil {

    public static String uploadPath(HttpSession session, String subDir) {
        String rootPath = session.getServletContext().getRealPath("/");
        File uploads = new File(rootPath, "uploads");
        if (!uploads.exists()) {
            uploads.mkdir();
        }
        File uploadDir = new File(uploads, subDir);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        return uploadDir.getAbsolutePath();
    }

    public static String uploadFilePath(File file) {
        String path = file.getAbsolutePath();
        return path.substring(path.indexOf("uploads"));
    }

    /**
     * 多个文件类型用逗号隔开
     *
     * @param type
     * @return
     */
    public static boolean checkExtension(String fileName, String type) {
        String[] types = type.split(",");
        for (String t : types) {
            if (fileName.endsWith("." + t)) {
                return true;
            }
        }
        return false;
    }

    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
