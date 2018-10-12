package com.xinho.springboot.mail.controller;

import com.xinho.springboot.mail.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1815:45
 */
@RestController
public class MailController {

    @Autowired
    IMailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/sendMail1")
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("1291742117@qq.com","test simple mail"," hello this is simple mail");
    }

    @RequestMapping("/sendMail2")
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("1291742117@qq.com","test simple mail",content);
    }

    @RequestMapping("/sendMail3")
    public void sendAttachmentsMail() {
        String filePath="C:\\Users\\18040111\\Desktop\\111.sql";
        mailService.sendAttachmentsMail("1291742117@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @RequestMapping("/sendMail4")
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\18040111\\Desktop\\1.jpg";

        mailService.sendInlineResourceMail("1291742117@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @RequestMapping("/sendMail5")
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("1291742117@qq.com","主题：这是模板邮件",emailContent);
    }

}
