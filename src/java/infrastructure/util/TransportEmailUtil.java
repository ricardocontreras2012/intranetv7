/*
 * @(#)TransportEmailUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util;

import com.sun.mail.util.MailConnectException;
import static java.lang.System.out;
import java.net.ConnectException;
import java.util.Properties;
import javax.mail.Session;
import static javax.mail.Session.getDefaultInstance;
import javax.mail.Transport;
import static infrastructure.util.SystemParametersUtil.EMAIL_USERS;
import static infrastructure.util.SystemParametersUtil.MAIL_PROPERTIES;
import static infrastructure.util.SystemParametersUtil.NUM_EMAIL_USERS;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TransportEmailUtil {

    private static Integer seqTransport = 0;

    /**
     * Method description
     *
     *
     *
     * @param i
     * @return
     *
     * @throws Exception
     */
    private static Transport getNewTransport(Integer i) {
        Transport transport;
        try {
            out.println("getNewTransport: -> " + i);

            Properties properties = MAIL_PROPERTIES;
            Session session = getDefaultInstance(properties);

            transport = session.getTransport("smtp");
            transport.connect(properties.getProperty("mail.smtp.host"),
                    EMAIL_USERS.get(i).getUser(),
                    EMAIL_USERS.get(i).getPassword());

        } catch (MailConnectException e) {       
            
            if(e.getCause() instanceof ConnectException)
            {
                System.out.println("Falló conexion con cuenta "+i);
            }
            transport = null;
        } catch (Exception e){                        
            transport = null;}

        return transport;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static synchronized Transport getTransport() {
        int cont = 10 * NUM_EMAIL_USERS; //# ciclos x las cuentas
        Transport transport = null;

        while (cont > 0) {
            transport = getNewTransport(seqTransport);
            switchTransport();

            if (transport != null && transport.isConnected()) {
                break;
            }
            cont--;
        }

        return transport;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    private static synchronized void switchTransport() {
        seqTransport = (seqTransport + 1) % NUM_EMAIL_USERS;
    }
}
