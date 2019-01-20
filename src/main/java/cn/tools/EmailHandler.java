package cn.tools;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailHandler {
    private static Properties props = new Properties();

    public static void sendEmail(String to, String text) throws Exception {
        props.put("username", "18874488975m@sina.cn");
        props.put("password", "helloworld");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.sina.cn");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", true);

        Session session = Session.getDefaultInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
        //session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("18874488975m@sina.cn", "MC网站邮件中心", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(to, "User", "UTF-8"));

        message.setSubject("欢迎使用MC网站账号", "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(text, "text/html;charset=UTF-8");
        // 6. 设置显示的发件时间
        message.setSentDate(new Date());
        // 7. 保存前面的设置
        message.saveChanges();


        Transport transport = session.getTransport();
        transport.connect("18874488975m@sina.cn", "helloworld");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
