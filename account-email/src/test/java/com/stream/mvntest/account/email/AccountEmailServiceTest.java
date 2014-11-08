package com.stream.mvntest.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

/**
 * Created by stream on 2014/11/3.
 */
public class AccountEmailServiceTest {
    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception{
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("weixuerensoft@sina.com","weiXI!@#456");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws AccountEmailException, InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService)applicationContext.getBean("accountEmailService");
        String subject = "Test Subject";
        String emailContext = "<h1>This is a test email.</h1>";
        accountEmailService.sendMail("weix@ekingwin.com",subject,emailContext);
        greenMail.waitForIncomingEmail(2000,1);
        Message [] msgs = greenMail.getReceivedMessages();
        Assert.assertEquals(0,msgs.length);
    }

    @After
    public void stopMailServer(){
        greenMail.stop();
    }
}
