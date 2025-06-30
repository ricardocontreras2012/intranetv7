/*
 * @(#)MailMensajeSenderThread.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.thread;

import domain.model.Mensaje;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.util.List;
import infrastructure.support.MensajeSupport;
import infrastructure.util.HibernateUtil;
import static infrastructure.util.SystemParametersUtil.PATH_URL_ATTACH;
import java.util.stream.Collectors;

/**
 *
 * @author Ricardo Contreras S.
 */
public class MailMensajeSenderThread extends Thread {

    private final MensajeSupport mensajeSupport;
    private final String urlBase;
    private static final int CCO = 100;

    /**
     *
     * @param mensajeSupport
     * @param urlBase
     */
    public MailMensajeSenderThread(MensajeSupport mensajeSupport, String urlBase) {
        this.mensajeSupport = mensajeSupport;
        this.urlBase = urlBase;
    }

    /**
     * Method description
     *
     */
    @Override
    public void run() {
        String attachFile = null;
        Mensaje msg = this.mensajeSupport.getMensaje();
        String emailSender = this.mensajeSupport.getEmailSender();
        List<String> emailsDestinatarios = this.mensajeSupport.getDestList();

        try {
            if (msg.getMensajeAttachList() != null && !msg.getMensajeAttachList().isEmpty()) {
                attachFile = msg.getMsgIdSession()
                        + msg.getMsgCorrel() + ".htm";
                createAttachForAttachFiles(PATH_URL_ATTACH + attachFile);
            }

            int lLista = emailsDestinatarios.size();
            int pos = 0;
            int cont = 1;
            String dir = "";

            out.println("**** email " + mensajeSupport.getPara());
            while (pos < lLista) {
                dir = (pos == 0 || "".equals(dir)) ? "" : dir + ",";
                dir += emailsDestinatarios.get(pos);

                if (cont == CCO || pos == lLista - 1) {
                    String msgText;

                    switch (msg.getMsgTipo()) {
                        case "TXT":
                            msgText = msg.getMsgMsg();
                            if (emailSender != null && !"".equals(emailSender)) {
                                msgText += "\n\n::e-mail remitente: " + emailSender;
                            }
                            break;
                        case "HTML":
                            msgText = mensajeSupport.getPage();
                            break;
                        default:
                            msgText = "";
                    }

                    MailThread mailThread = new MailThread(dir,
                            "From: " + msg.getMsgNombreEnv() + " (No-Reply): "
                            + msg.getMsgSubject(), msgText,
                            msg.getMensajeAttachList(), attachFile, msg.getMsgImagen(), msg.getMsgTipo());
                    mailThread.start();

                    cont = 0;
                    dir = "";
                }
                cont++;
                pos++;
            }
            out.println("**** email sent ****");
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        HibernateUtil.closeSession();
    }

    /**
     * Method description
     *
     *
     * @param fileName
     */
    private void createAttachForAttachFiles(String fileName) throws IOException {
        BufferedWriter out = null;
        Mensaje msg = this.mensajeSupport.getMensaje();

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF8"));
            out.write("<html><body><table><tr><td><p "
                    + "style=\"font-weight:bold;background-color:#0E51A7;color:white\">ARCHIVOS "
                    + "ADJUNTOS</p></td></tr>");           
            
            String tableRows = msg.getMensajeAttachList().stream()
                    .map(anAttachList -> "<tr><td><a href=\"" + urlBase
                    + "/CommonMensajeDownloadAttachFile?correl=" + msg.getMsgCorrel()
                    + "&key=" + msg.getMsgIdSession()
                    + "&name=" + anAttachList.getMenaAttachFile()
                    + "\">" + anAttachList.getMenaAttachFile() + "</a></td></tr>")
                    .collect(Collectors.joining("\n")); // Une todas las filas con un salto de línea

            out.write(tableRows);

            out.write("</table></body></html>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
