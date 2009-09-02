package org.poseidon.util.util2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * for mail need J2EE 1.4 Libraries
 * [important : exception with J2EE5.0]
 * @author weidong_zhao
 *
 */

public class MailFactory {
    
    private static final Logger LOGGER = Logger.getLogger(MailFactory.class);
    private static MailFactory m = new MailFactory();
    static{
        LoggerUtil.initLogging(LOGGER);
    }
    
    public static MailFactory createMail(){
        if(m == null){
            m = new MailFactory();
        }
        return m;
    }
    
    private MailFactory(){}

    // 默认设置
    String host     = "mail.9you.com";
    String user     = "account@staff.9you.com";
    String password = "12341234";

    public void initConfig(boolean isAgain) {
        Properties p = new Properties();
        boolean loadDefault = false;
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(getClass().getClassLoader().getResource("mail.properties").getPath()));
            p.load(in);
        } catch (Exception e) {
            loadDefault = true;
        }
        if (!loadDefault) {
            if (!isAgain) { // first
                host = (String) p.get("host1");
                user = (String) p.get("user1");
                password = (String) p.get("password1");
            } else { // again
                host = (String) p.get("host2");
                user = (String) p.get("user2");
                password = (String) p.get("password2");
            }
        }
        // System.out.println("MESSAGE:host["+host+"]sendUser["+user+"]password["+password+"]isAgain["+isAgain+"]");
    }

    /** HTML形式发送邮件 */
    public void sendWithHtml(String to, String subject, String content) throws MessagingException {
        initConfig(false);
        send(to, subject, content);
    }

    /** 邮件发送众失败时以另一个帐号再发送一次 */
    public void sendAgainWithHtml(String to, String subject, String content) throws MessagingException {
        initConfig(true);
        send(to, subject, content);
    }

    private void send(String to, String subject, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);// 指定SMTP服务器
        props.put("mail.transport.protocol", "SMTP");
        props.put("mail.smtp.auth", "true");// 指定是否需要SMTP验证

        content = "<html><body>" + content + "</body></html>";

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(false);// 是否在控制台显示debug信息
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(user));// 发件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// 收件人
        message.setSubject(subject, "gb2312");// 邮件主题
        message.setContent(content, "text/html;charset=gb2312");
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport = mailSession.getTransport("smtp");
        transport.connect(host, user, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) throws MessagingException {
        MailFactory mail = new MailFactory();
        String email = "zwd_happyboy@msn.com";
        email = "114343309@qq.com";
        email = "zwd.happyboy@gmail.com";
        String title = "测试邮箱发送";

        mail.initConfig(false);
        mail.send(email, title, "(" + mail.user + "/" + mail.host + "}");

        mail.initConfig(true);
        mail.send(email, title, "(" + mail.user + "/" + mail.host + "}");

    }

}


