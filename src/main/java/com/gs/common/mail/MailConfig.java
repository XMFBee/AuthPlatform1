package com.gs.common.mail;

import com.gs.bean.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MailConfig {
    private static Logger logger = (Logger) LoggerFactory.getLogger(MailConfig.class);
    private static Properties props;

    /**
     * 根据路径读配置文件
     */
    public static Properties readProperties(String path){
        props = new Properties();
        try {
            props.load(MailConfig.class.getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return props;
    }

    // 根据文件读配置文件
    public static Properties readProperties(File file) {
        props = new Properties();
        try {
            props.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    // 读配置文件的string值
    public static String getString(String key){
        return props.getProperty(key);
    }
    // 读配置文件的int值
    public static int getInt(String key){
        return Integer.valueOf(props.getProperty(key));
    }

    // 发送邮件
    public static void sendMail(File file, Mail mail){
        Properties props = MailConfig.readProperties(file); // 根据路径读到配置文件
        Session session = Session.getInstance(props, new MailAuth(MailConfig.
                getString("username"), MailConfig.getString("password")));
        try {
            Transport transport = session.getTransport();
            transport.connect(); // 尝试发送邮件
            logger.info("尝试发送邮件");
            Message msg = new MimeMessage(session);
            mail.setFrom(MailConfig.getString("username")); // 把账号经过改编
            msg.setFrom(mail.getFrom()); // 再调用get方法
            msg.setSubject(mail.getSubject());
            if (mail.getContent() != null){
                msg.setContent(mail.getContent(), mail.getType());
            }else {
                msg.setContent(mail.getMultipart());
            }
            // 假如密送不为空
            if (mail.getBccRecipients() != null){
                msg.setRecipients(RecipientType.BCC, mail.getBccRecipients());
            }
//            // 这个为空就报错
//            // 发送邮件的类型,
//            if (mail.getRecipients() != null) {
//                msg.setRecipients(RecipientType.TO, mail.getRecipients());
//            }
//            // 假如超送不为空
//            if (mail.getCcRecipients() != null){
//                msg.setRecipients(RecipientType.CC, mail.getCcRecipients());
//            }
            transport.send(msg);
            logger.info("发送邮件成功");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
