/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util;

import static com.opensymphony.xwork2.ActionContext.getContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.model.MensajeAttach;
import domain.model.PasswordTicket;
import java.io.File;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import domain.repository.PasswordTicketRepository;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_URL_ATTACH;
import infrastructure.util.common.CommonArchivoUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Ricardo
 */
public class plop {

    private interface MimeMessageBuilder {

        void buildMessage(MimeMessage message, Address[] recipients, String subject, Transport transport) throws Exception;
    }

    private static void sendEmailInternal(String emailAddress, String subject, MimeMessageBuilder builder) {
        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            System.out.println("[WARN] Dirección de correo vacía.");
            return;
        }

        String validEmails = getValidEmail(emailAddress);
        Address[] recipients;
        try {
            recipients = InternetAddress.parse(validEmails, false);
            if (recipients.length == 0) {
                System.out.println("[WARN] No se encontraron direcciones válidas.");
                return;
            }
        } catch (AddressException e) {
            System.out.println("[ERROR] Error al parsear direcciones: " + e.getMessage());
            return;
        }

        final int MAX_ATTEMPTS = 3;
        int attempt = 0;
        boolean success = false;

        // Aquí recipients es mutable y se actualizará en cada ciclo
        while (attempt < MAX_ATTEMPTS && !success && recipients.length > 0) {
            attempt++;
            SmtpTransportPoolUtil.TransportWrapper wrapper = null;

            try {
                wrapper = EmailPoolManagerUtil.SMTP_POOL.borrowTransport();
                if (wrapper == null) {
                    System.out.println("[ERROR] Intento " + attempt + ": No se pudo obtener un transporte SMTP.");
                    continue;
                }

                Transport transport = wrapper.getTransport();
                Session session = EmailPoolManagerUtil.SMTP_POOL.getSession();

                MimeMessage message = new MimeMessage(session);

                // Pasamos el arreglo actual de recipients al builder
                builder.buildMessage(message, recipients, subject, transport);

                transport.sendMessage(message, message.getAllRecipients());

                System.out.println("[INFO] Correo enviado exitosamente desde "
                        + transport.getURLName().getUsername() + " a " + Arrays.toString(recipients));
                success = true;

            } catch (SendFailedException se) {
                System.out.println("[WARN] SendFailedException: " + se.getMessage());

                Address[] invalid = se.getInvalidAddresses();
                Address[] validUnsent = se.getValidUnsentAddresses();

                if (invalid != null && invalid.length > 0) {
                    for (Address addr : invalid) {
                        System.out.println("[INVALID EMAIL DETECTED DURANTE ENVÍO] => " + addr.toString());
                    }
                }

                if (validUnsent != null && validUnsent.length > 0) {
                    recipients = validUnsent; // Actualizamos la variable recipients para reintentar
                    System.out.println("[INFO] Reintentando solo con direcciones válidas: " + Arrays.toString(validUnsent));
                } else {
                    System.out.println("[FAIL] No quedan direcciones válidas para reintentar.");
                    break;
                }

            } catch (Exception e) {
                System.out.println("[ERROR] Intento " + attempt + ": Fallo inesperado: " + e.getMessage());
            } finally {
                if (wrapper != null) {
                    EmailPoolManagerUtil.SMTP_POOL.returnTransport(wrapper);
                }
            }
        }

        if (!success) {
            System.out.println("[FAIL] No se pudo enviar el correo tras " + MAX_ATTEMPTS + " intentos.");
        }
    }

    private void buildMessage(MimeMessage message, Address[] recipients, String subject, String body,
            List<MensajeAttach> attachList, String attachFileName, String imagen,
            String tipo, String part, Transport transport) throws Exception {

        message.setFrom(new InternetAddress(transport.getURLName().getUsername()));
        message.setRecipients(Message.RecipientType.BCC, recipients);
        message.setSubject(subject);

        Multipart multipart = buildMultipart(body, imagen, part);
        addAttachments(multipart, tipo, attachList, attachFileName);
        message.setContent(multipart);
    }

    private Multipart buildMultipart(String body, String imagen, String part) throws Exception {
        Multipart multipart;

        switch (part) {
            case "IMG":
                multipart = new MimeMultipart("related");

                MimeBodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent("<img src=\"cid:image\">", "text/html");
                multipart.addBodyPart(htmlPart);

                MimeBodyPart imagePart = new MimeBodyPart();
                File imageFile = new File(CommonArchivoUtil.getServerPath("msg") + imagen);
                imagePart.setDataHandler(new DataHandler(new FileDataSource(imageFile)));
                imagePart.setHeader("Content-ID", "<image>");
                multipart.addBodyPart(imagePart);
                break;

            case "HTML":
                MimeBodyPart htmlBody = new MimeBodyPart();
                htmlBody.setContent(body, "text/html; charset=utf-8");
                multipart = new MimeMultipart();
                multipart.addBodyPart(htmlBody);
                break;

            case "TXT":
            default:
                MimeBodyPart textBody = new MimeBodyPart();
                textBody.setText(body);
                multipart = new MimeMultipart();
                multipart.addBodyPart(textBody);
                break;
        }

        return multipart;
    }

    private void addAttachments(Multipart multipart, String tipo,
            List<MensajeAttach> attachList, String attachFileName) throws Exception {

        String path = null;

        switch (tipo) {
            case "TM_CERT":
                path = PATH_CERT;
                break;
            case "TM_SIT":
                path = PATH_ATTACH_MESSAGES;
                break;
            case "TM_STD":
                if (attachList != null && !attachList.isEmpty()) {
                    path = PATH_URL_ATTACH;
                }
                break;
            default:
                // Si no coincide, path se mantiene null
                break;
        }

        if (path != null) {
            attachFile(multipart, path, attachFileName);
        }
    }

    private static void attachFile(Multipart multipart, String path, String attachFileName) {
        File file = new File(path + attachFileName);

        // Verificar si el archivo existe
        if (!file.exists()) {
            System.err.println("El archivo no existe: " + path + attachFileName);
            return;
        }

        try {
            // Utilizamos FileDataSource que maneja automáticamente el tipo MIME
            DataSource dataSource = new FileDataSource(file);

            // Crear y configurar la parte del cuerpo para el archivo adjunto
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(dataSource));
            bodyPart.setFileName(attachFileName);

            // Agregar la parte del archivo al multipart
            multipart.addBodyPart(bodyPart);
        } catch (MessagingException e) {
            System.err.println("Error al agregar el archivo adjunto: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     * @param email
     * @return
     */
    public static String getValidEmail(String email) {
        return Arrays.stream(email.split(","))
                .map(String::trim) // Elimina espacios en blanco alrededor de los correos
                .filter(MailUtil::isValidEmail) // Filtra los correos válidos
                .collect(Collectors.joining(",")); // Une los correos válidos con coma
    }

    /**
     * Valida si una dirección de correo electrónico es válida.
     *
     * Este método utiliza la clase {@link InternetAddress} para intentar
     * validar el formato del correo electrónico. Si el correo no es válido, se
     * lanza una {@link AddressException} y el método devuelve {@code false}. Si
     * el correo es válido, se retorna {@code true}.
     *
     * @param email La dirección de correo electrónico que se desea validar.
     * @return {@code true} si el correo electrónico es válido, {@code false} si
     * es inválido.
     */
    public static boolean isValidEmail(String email) {
        try {
            // Intenta validar el correo electrónico utilizando InternetAddress
            new InternetAddress(email).validate();
            // Si no hay excepciones, el correo es válido

            return true;
        } catch (AddressException ex) {
            // Si se lanza una excepción, significa que el correo no es válido 
            return false;
        }
    }

    //////////////
    public static void sendEmail(String emailAddress, String subject, String text) {
        sendEmailInternal(emailAddress, subject, (message, recipients, subj, transport) -> {
            message.setFrom(new InternetAddress(transport.getURLName().getUsername()));
            message.setRecipients(Message.RecipientType.BCC, recipients);
            message.setSubject(subj);
            message.setText(text);
        });
    }

    public void sendMensajeEmail(
            String emailAddress, String subject, String body,
            List<MensajeAttach> attachList, String attachFileName,
            String imagen, String tipo, String part, int intento) {

        sendEmailInternal(emailAddress, subject, (message, recipients, subj, transport) -> {
            message.setFrom(new InternetAddress(transport.getURLName().getUsername()));
            message.setRecipients(Message.RecipientType.BCC, recipients);
            message.setSubject(subj);

            Multipart multipart = buildMultipart(body, imagen, part);
            addAttachments(multipart, tipo, attachList, attachFileName);
            message.setContent(multipart);
        });
    }

}
