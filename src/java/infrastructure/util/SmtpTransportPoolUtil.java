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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SmtpTransportPoolUtil {

    private final List<SmtpAccountUtil> accounts = new ArrayList<>();
    private static final int MAX_BORROW_RETRIES = 3;
    private final List<BlockingQueue<Transport>> transportPools = new ArrayList<>();
    private final Session session;
    private final Properties mailProperties;
    private final AtomicInteger nextIndex = new AtomicInteger(0);

    public SmtpTransportPoolUtil(Session session, List<SmtpAccountUtil> configuredAccounts, Properties mailProperties, int poolSizePerUser) {
        this.session = session;
        this.mailProperties = mailProperties;

        for (SmtpAccountUtil account : configuredAccounts) {
            ArrayBlockingQueue<Transport> pool = new ArrayBlockingQueue<>(poolSizePerUser);
            try {
                for (int i = 0; i < poolSizePerUser; i++) {
                    Transport t = session.getTransport("smtp");
                    t.connect(
                            mailProperties.getProperty("mail.smtp.host"),
                            Integer.parseInt(mailProperties.getProperty("mail.smtp.port")),
                            account.getUsername(),
                            account.getPassword()
                    );
                    pool.offer(t);
                }
                // ✅ Solo si todo salió bien, agregamos la cuenta como válida
                transportPools.add(pool);
                accounts.add(account);
                System.out.println("✔ Cuenta SMTP agregada al pool: " + account.getUsername());
            } catch (AuthenticationFailedException e) {
                System.err.println("❌ Autenticación fallida para " + account.getUsername() + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("⚠️ Error general con " + account.getUsername() + ": " + e.getMessage());
            }
        }

        if (accounts.isEmpty()) {
            throw new RuntimeException("No se pudo autenticar ninguna cuenta SMTP válida.");
        }
    }

    public TransportWrapper borrowTransport() {
        int attempts = 0;
        int totalAccounts = accounts.size();

        // Empezar desde el índice actual en el round robin
        int startIndex = nextIndex.getAndUpdate(i -> (i + 1) % totalAccounts);

        // Intentar hasta 3 veces en total, rotando por cuentas distintas
        for (int i = 0; i < totalAccounts && attempts < MAX_BORROW_RETRIES; i++) {
            int index = (startIndex + i) % totalAccounts;
            SmtpAccountUtil account = accounts.get(index);

            try {
                Transport transport = session.getTransport("smtp");

                transport.connect(
                        mailProperties.getProperty("mail.smtp.host"),
                        Integer.parseInt(mailProperties.getProperty("mail.smtp.port")),
                        account.getUsername(),
                        account.getPassword()
                );

                // Si conecta bien, devolvemos wrapper
                return new TransportWrapper(transport, account, index);

            } catch (MessagingException e) {
                System.err.println("❌ Falló conexión SMTP con cuenta " + account.getUsername() + ": " + e.getMessage());
                attempts++;
                // Continua con siguiente cuenta
            }
        }

        System.err.println("🚫 No se pudo obtener ningún Transport válido tras " + attempts + " intentos.");
        return null;
    }

    public void returnTransport(TransportWrapper wrapper) {
        try {
            wrapper.getTransport().close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static class TransportWrapper {

        private final Transport transport;
        private final SmtpAccountUtil account;
        private final int accountIndex;

        public TransportWrapper(Transport transport, SmtpAccountUtil account, int accountIndex) {
            this.transport = transport;
            this.account = account;
            this.accountIndex = accountIndex;
        }

        public Transport getTransport() {
            return transport;
        }

        public SmtpAccountUtil getAccount() {
            return account;
        }

        public int getAccountIndex() {
            return accountIndex;
        }
    }

    public Session getSession() {
        return this.session;
    }
}
