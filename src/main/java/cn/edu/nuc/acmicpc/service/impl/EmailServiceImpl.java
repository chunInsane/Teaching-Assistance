package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.util.EmailUtil;
import cn.edu.nuc.acmicpc.model.MailAuthentication;
import cn.edu.nuc.acmicpc.model.MailObject;
import cn.edu.nuc.acmicpc.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Email service implement.
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    @Qualifier("mailAuthentication")
    private MailAuthentication mailAuthentication;

    @Override
    public void send(String emailAddress, String title, String content) {
        MailObject mailObject = new MailObject();
        mailObject.setToAddresses(emailAddress);
        mailObject.setSubject(title);
        mailObject.setBody(content);
        mailObject.setMailAuthentication(mailAuthentication);
        mailObject.setHostname("smtp.163.com");
        mailObject.setCcAddresses("");
        EmailUtil.mailTo(mailObject);
    }

}
