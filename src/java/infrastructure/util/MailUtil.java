/*
 * @(#)MailUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import static com.opensymphony.xwork2.ActionContext.getContext;
import com.opensymphony.xwork2.ActionSupport;
import static com.opensymphony.xwork2.util.TextParseUtil.commaDelimitedStringToSet;
import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;
import domain.model.MensajeAttach;
import domain.model.PasswordTicket;
import java.io.File;
import static java.lang.System.out;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import static javax.mail.Message.RecipientType.BCC;
import static javax.mail.Session.getDefaultInstance;
import javax.mail.internet.*;
import static javax.mail.internet.InternetAddress.parse;
import org.apache.commons.lang3.StringUtils;
import domain.repository.PasswordTicketRepository;
import static infrastructure.util.SystemParametersUtil.MAIL_PROPERTIES;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_URL_ATTACH;
import static infrastructure.util.TransportEmailUtil.getTransport;
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
        Properties properties = MAIL_PROPERTIES;
        Session session = getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

        try {
            Transport transport = getTransport();
            out.println(">>>> enviando email->" + emailAddress);
            message.setFrom(new InternetAddress(transport.getURLName().getUsername()));
            message.addRecipient(BCC, new InternetAddress(emailAddress));
            message.setSubject(subject);
            message.setText(text);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            out.println(">>>> email enviado->" + emailAddress + "::" + transport.getURLName().getUsername());
        } catch (Exception e) {
            e.printStackTrace();
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
        commaDelimitedStringToSet(email).forEach(s -> sendEmail(s, subject, body));
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
    public void sendMensajeEmail(String emailAddress, String subject, String body,
            List<MensajeAttach> attachList, String attachFileName, String imagen, String tipo, String part, int intento) {

        Properties properties = MAIL_PROPERTIES;
        Session session;
        MimeMessage message;
        Transport transport;

        if (intento <= MAX_INTENTOS) {
            try {
                session = getDefaultInstance(properties);
                message = new MimeMessage(session);
                transport = getTransport();
                if (emailAddress != null && !"".equals(getValidEmail(emailAddress))) {
                    out.println(">>>> enviando correo(" + intento + ")->" + emailAddress + "::" + transport.getURLName().getUsername());

                    InternetAddress[] iAdressArray = parse(emailAddress);

                    message.setFrom(new InternetAddress(transport.getURLName().getUsername()));
                    message.setRecipients(BCC, iAdressArray);
                    message.setSubject(subject);
                    Multipart multiPart = new MimeMultipart();

                    switch (part) {
                        case "TXT":
                            BodyPart bodyPart = new MimeBodyPart();
                            bodyPart.setText(body);
                            multiPart.addBodyPart(bodyPart);
                            break;

                        case "HTML":
                            MimeBodyPart htmlPart = new MimeBodyPart();
                            htmlPart.setContent(body, "text/html; charset=utf-8");
                            multiPart.addBodyPart(htmlPart);
                            break;

                        case "IMG":
                            multiPart = new MimeMultipart("related");
                            BodyPart messageBodyPart = new MimeBodyPart();
                            String htmlText = "<img src=\"cid:image\">";
                            messageBodyPart.setContent(htmlText, "text/html");
                            multiPart.addBodyPart(messageBodyPart);
                            messageBodyPart = new MimeBodyPart();
                            DataSource fds = new FileDataSource(new File(CommonArchivoUtil.getServerPath("msg") + imagen));

                            messageBodyPart.setDataHandler(new DataHandler(fds));
                            messageBodyPart.setHeader("Content-ID", "<image>");

                            // add image to the multipart
                            multiPart.addBodyPart(messageBodyPart);
                            break;
                    }

                    switch (tipo) {
                        case "TM_STD"://Tipo mail estandar
                            if (attachList != null && !attachList.isEmpty()) {
                                attachFile(multiPart, PATH_URL_ATTACH, attachFileName);
                            }
                            break;
                        /*case "TM_MSG":
                            attachFile(multiPart, PATH_ATTACH_MESSAGES, attachFileName);
                            break;*/
                        case "TM_CERT"://Tipo mail copia certificados/constancias
                            attachFile(multiPart, PATH_CERT, attachFileName);
                            break;
                        case "TM_SIT":
                            attachFile(multiPart, PATH_ATTACH_MESSAGES, attachFileName);
                            break;
                    }

                    message.setContent(multiPart);

                    transport.sendMessage(message, message.getAllRecipients());
                    out.println(">>>> correo enviado");
                }

            } catch (SendFailedException se) {

                System.out.println("en SendFailedException !!!");

                if (se.getCause() instanceof SMTPAddressFailedException) {
                    Address[] listaInvalid = se.getInvalidAddresses();
                    Address[] listaValid = se.getValidUnsentAddresses();

                    Arrays.stream(listaInvalid).forEach(address -> System.out.println("correo invalido!!!!! ===> " + address));

                    String newEmailAddress;
                    if (listaValid.length > 0) {
                        newEmailAddress = StringUtils.join(listaValid, ',');
                        sendMensajeEmail(newEmailAddress, subject, body,
                                attachList, attachFileName, imagen, tipo, part, intento);
                    }
                }
                if (se.getCause() instanceof SMTPSendFailedException) {
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException ex) {
                    }
                    sendMensajeEmail(emailAddress, subject, body,
                            attachList, attachFileName, imagen, tipo, part, intento + 1);
                }

            } catch (MessagingException me) {

                System.out.println("en MessagingException !!!!!!!!!!!!!!!!!!!");

                if (me.getCause() instanceof java.net.SocketTimeoutException || me.getCause() instanceof java.net.SocketException) {
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException ex) {
                    }
                    sendMensajeEmail(emailAddress, subject, body,
                            attachList, attachFileName, imagen, tipo, part, intento + 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
