package org.javaboy.mailserver.receiver;

import org.apache.juli.logging.LogFactory;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.Hr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @Author szh
 * @Date 2022/6/27 10:29
 * @PackageName:org.javaboy.mailserver.receiver
 * @ClassName: MailReceiver
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class MailReceiver {

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    TemplateEngine templateEngine;

    @RabbitListener(queues = "javaboy.mail.welcome")
    public void handler(Employee employee){
        logger.info(employee.toString());
        //收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(employee.getEmail());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posName",employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJobLevel().getName());
            context.setVariable("departemntName", employee.getDepartment().getName());
            String mail = templateEngine.process("mail.html", context);
            helper.setText(mail,true);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败："+e.getMessage());
        }
        javaMailSender.send(msg);
    }
}
