/*
 * @(#)MailUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import static com.opensymphony.xwork2.ActionContext.getContext;
import com.opensymphony.xwork2.ActionSupport;
import static com.opensymphony.xwork2.util.TextParseUtil.commaDelimitedStringToSet;
import domain.model.MensajeAttach;
import domain.model.PasswordTicket;
import java.io.File;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import static javax.mail.internet.InternetAddress.parse;
import domain.repository.PasswordTicketRepository;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_URL_ATTACH;
import infrastructure.util.common.CommonArchivoUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Envio de correos.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class MailUtil {

    static int MAX_INTENTOS = 30;
    static int DELAY = 5000;

    /**
     *
     */
    public MailUtil() {
    }

    /**
     * Envia de correo.
     *
     * @param emailAddress Dirección de correo.
     * @param subject Subject del correo.
     * @param text Texto del correo.
     */
    public static void sendEmail(String emailAddress, String subject, String text) {
        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            System.out.println("[WARN] Dirección de correo vacía.");
            return;
        }

        String email = getValidEmail(emailAddress);
        Address[] recipients;
        try {
            recipients = InternetAddress.parse(email, false);
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
                String fromAddress = wrapper.getAccount().getUsername();

                message.setFrom(new InternetAddress(fromAddress));
                message.setRecipients(Message.RecipientType.BCC, recipients);
                message.setSubject(subject);
                message.setText(text);

                System.out.println("[INFO] Intento " + attempt + ": Enviando correo desde " + fromAddress + " -> " + Arrays.toString(recipients));

                transport.sendMessage(message, recipients);

                System.out.println("[INFO] Correo enviado exitosamente.");
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
                    recipients = validUnsent;
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

    /**
     * Envia correo con la confirmacion de la peticion de una nueva password.
     *
     * @param rut RUT del usuario.
     * @param userType Tipo de usuario.
     * @param email correo(s) del usuario.
     */
    public static void sendURLNewPassword(Integer rut, String userType, String email) {
        // Obtención de la persistencia del ticket
        PasswordTicketRepository ticketRepository = ContextUtil.getDAO().getPasswordTicketRepository(ActionUtil.getDBUser());

        // Crear y configurar el ticket
        PasswordTicket ticket = new PasswordTicket();
        ticket.setPtRut(rut);
        ticket.setPtUserType(userType);
        ticket.setPtKey(ticketRepository.getKey(rut));

        // Eliminar tickets previos y agregar el nuevo
        ticketRepository.deleteTickets(rut);
        ticketRepository.insertTicket(ticket);

        ActionSupport action = (ActionSupport) getContext().getActionInvocation().getAction();
        // Obtener el asunto y cuerpo del correo de manera centralizada
        String subject = action.getText("mail.password.requirement.subject");
        String body = action.getText("mail.password.requirement.text") + " " + ActionUtil.getURL() + "/CommonPasswordSetNewPassword?rut=" + rut + "&key=" + ticket.getPtKey();

        // Convertir el string de emails a un conjunto para evitar duplicados y enviar el correo
        //commaDelimitedStringToSet(email).forEach(s -> sendEmail(s, subject, body));
        sendEmail(email, subject, body);
    }

    public static void sendNewPassword(String email, String password) {
        // Convertir el correo a un conjunto (sin duplicados)
        Set<String> emails = new HashSet<>(commaDelimitedStringToSet(email));
        ActionSupport action = (ActionSupport) getContext().getActionInvocation().getAction();

        // Usar streams para enviar el correo a cada destinatario
        emails.forEach(recipient -> sendEmail(recipient, action.getText("mail.password"), password));
    }

    /**
     * Envia copia del mensaje a un correo especifico de usuario.
     *
     * @param emailAddress Correo del destinatario.
     * @param subject Subject del correo.
     * @param body Cuerpo del mensaje.
     * @param attachList
     * @param attachFileName
     * @param imagen
     * @param tipo
     * @param part
     * @param intento
     */    
    public void sendMensajeEmail(
            String emailAddress, String subject, String body,
            List<MensajeAttach> attachList, String attachFileName,
            String imagen, String tipo, String part, int intento) {

        if (intento > MAX_INTENTOS) {
            System.out.println("[ERROR] Se alcanzó el número máximo de intentos para: " + emailAddress);
            return;
        }

        String email = getValidEmail(emailAddress); // Puede contener múltiples correos separados por coma
        if (email == null || email.trim().isEmpty()) {
            System.out.println("[WARN] Dirección de correo inválida.");
            return;
        }

        SmtpTransportPoolUtil.TransportWrapper wrapper = null;

        try {
            wrapper = EmailPoolManagerUtil.SMTP_POOL.borrowTransport();
            if (wrapper == null) {
                System.out.println("[ERROR] No se pudo obtener un transporte de correo.");
                return;
            }

            Transport transport = wrapper.getTransport();
            Session session = EmailPoolManagerUtil.SMTP_POOL.getSession();

            MimeMessage message = new MimeMessage(session);

            buildMessage(message, email, subject, body, attachList, attachFileName, imagen, tipo, part, transport);

            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("[INFO] Correo enviado a: " + email + " usando " + transport.getURLName().getUsername());

        } catch (SendFailedException se) {
            System.out.println("[WARN] SendFailedException: " + se.getMessage());

            Address[] invalid = se.getInvalidAddresses();
            Address[] validUnsent = se.getValidUnsentAddresses();

            // Mostrar direcciones inválidas detectadas durante el envío
            if (invalid != null && invalid.length > 0) {
                for (Address addr : invalid) {
                    System.out.println("[INVALID EMAIL DURANTE ENVÍO] => " + addr.toString());
                }
            }

            // Reintentar con las direcciones válidas que no fueron enviadas
            if (validUnsent != null && validUnsent.length > 0) {
                String newEmail = Arrays.stream(validUnsent)
                        .map(Address::toString)
                        .collect(Collectors.joining(","));

                System.out.println("[INFO] Reintentando envío a direcciones válidas: " + newEmail);
                sendMensajeEmail(newEmail, subject, body, attachList, attachFileName, imagen, tipo, part, intento + 1);

            } else {
                System.out.println("[FAIL] No hay direcciones válidas para reintentar.");
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Error general al enviar correo: " + e.getMessage());
            e.printStackTrace();

        } finally {
            if (wrapper != null) {
                EmailPoolManagerUtil.SMTP_POOL.returnTransport(wrapper);
            }
        }
    }

    private void buildMessage(MimeMessage message, String emailAddress, String subject, String body,
            List<MensajeAttach> attachList, String attachFileName, String imagen,
            String tipo, String part, Transport transport) throws Exception {

        message.setFrom(new InternetAddress(transport.getURLName().getUsername()));
        message.setRecipients(Message.RecipientType.BCC, parse(emailAddress));
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
}