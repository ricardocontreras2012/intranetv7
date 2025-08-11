/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util;

/**
 *
 * @author Ricardo
 */
import javax.mail.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static infrastructure.util.PropertyLoaderUtil.loadProperties;

public class EmailPoolManagerUtil {

    public static final Properties MAIL_PROPERTIES = loadProperties("config/mail/mail.xml");
    private static final int NUM_EMAIL_USERS = Integer.parseInt(MAIL_PROPERTIES.getProperty("users"));
    public static final List<SmtpAccountUtil> SMTP_ACCOUNTS = buildSmtpAccounts();

    public static final SmtpTransportPoolUtil SMTP_POOL;

    static {
        try {
            Session session = Session.getInstance(MAIL_PROPERTIES);
            SMTP_POOL = new SmtpTransportPoolUtil(session, SMTP_ACCOUNTS, MAIL_PROPERTIES, 5); // poolSize = 5 por cuenta
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SMTP pool", e);
        }
    }

    private static List<SmtpAccountUtil> buildSmtpAccounts() {
        return IntStream.range(0, NUM_EMAIL_USERS)
                .mapToObj(i -> {
                    String user = MAIL_PROPERTIES.getProperty("mail.smtp.user" + i);
                    String password = MAIL_PROPERTIES.getProperty("mail.smtp.password" + i);
                    return new SmtpAccountUtil(user, password);
                })
                .collect(Collectors.toList());
    }    
}
