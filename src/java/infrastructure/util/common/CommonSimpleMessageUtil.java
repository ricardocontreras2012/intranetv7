package infrastructure.util.common;

import domain.model.Mensaje;
import domain.model.MensajeAttach;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import infrastructure.support.MensajeNodeSupport;
import infrastructure.support.MensajeSenderSupport;
import infrastructure.support.MensajeSupport;
import infrastructure.thread.MailMensajeSenderThread;
import static infrastructure.util.ActionUtil.getURL;
import static infrastructure.util.DateUtil.getSysdate;

/**
 * Utilidades para enviar mensajes simples con o sin archivos adjuntos.
 *
 * @author Ricardo
 */
public class CommonSimpleMessageUtil {

    /**
     * Envía un mensaje con archivos adjuntos.
     *
     * @param files Archivos adjuntos.
     * @param key Clave de sesión.
     * @param rutDest RUT del destinatario.
     * @param nombreDest Nombre del destinatario.
     * @param rutEnv RUT del remitente.
     * @param nombreEnv Nombre del remitente.
     * @param rolEnv Rol del remitente.
     * @param glosaPrincipal Texto principal del mensaje.
     * @param glosaFinal Texto final del mensaje.
     * @param id ID del mensaje.
     * @param subject Asunto del mensaje.
     * @param tipoEmail Tipo de correo.
     */
    public static void send(String[] files, String key, Integer rutDest, String nombreDest, Integer rutEnv, String nombreEnv, String rolEnv, String glosaPrincipal, String glosaFinal, String id, String subject, String tipoEmail) {
        MensajeSupport mensajeSupport = createMensajeSupport(rutDest, nombreDest, subject, id);
        Mensaje mensaje = new Mensaje();

        setAttachFiles(files, mensaje);

        // Configurar mensaje
        configureMensaje(mensaje, rutEnv, nombreEnv, rolEnv, subject, glosaPrincipal, glosaFinal, key, mensajeSupport);

        // Enviar mensaje
        MensajeSenderSupport sender = new MensajeSenderSupport(mensajeSupport);
        List<String> destList = sender.send();
        mensajeSupport.setDestList(destList);

        // Iniciar hilo para enviar el mensaje
        MailMensajeSenderThread thread = new MailMensajeSenderThread(mensajeSupport, getURL());
        thread.start();
    }

    /**
     * Envía un mensaje con un archivo adjunto.
     *
     * @param file Archivo adjunto.
     * @param key Clave de sesión.
     * @param rutDest RUT del destinatario.
     * @param nombreDest Nombre del destinatario.
     * @param rutEnv RUT del remitente.
     * @param nombreEnv Nombre del remitente.
     * @param rolEnv Rol del remitente.
     * @param glosaPrincipal Texto principal del mensaje.
     * @param glosaFinal Texto final del mensaje.
     * @param id ID del mensaje.
     * @param subject Asunto del mensaje.
     * @param tipoEmail Tipo de correo.
     */
    public static void send(String file, String key, Integer rutDest, String nombreDest, Integer rutEnv, String nombreEnv, String rolEnv, String glosaPrincipal, String glosaFinal, String id, String subject, String tipoEmail) {
        send(new String[]{file}, key, rutDest, nombreDest, rutEnv, nombreEnv, rolEnv, glosaPrincipal, glosaFinal, id, subject, tipoEmail);
    }

    // Método para crear un MensajeSupport
    private static MensajeSupport createMensajeSupport(Integer rutDest, String nombreDest, String subject, String id) {
        MensajeSupport mensajeSupport = new MensajeSupport(null);
        MensajeNodeSupport rootNode = new MensajeNodeSupport();

        MensajeNodeSupport messageNodeSupport = new MensajeNodeSupport();
        messageNodeSupport.setId(valueOf(rutDest));
        messageNodeSupport.setTerminal(true);

        List<MensajeNodeSupport> lCommonMensajeNodeUtil = new ArrayList<>();
        lCommonMensajeNodeUtil.add(messageNodeSupport);

        rootNode.setId(id);
        rootNode.setValue(subject);
        rootNode.setTerminal(true);
        rootNode.setState("SP");
        rootNode.setNodeList(lCommonMensajeNodeUtil);

        mensajeSupport.setRootNode(rootNode);
        mensajeSupport.setPara(nombreDest);
        mensajeSupport.setSubject(subject);
        mensajeSupport.setCurrentNode(rootNode);

        return mensajeSupport;
    }

    // Método para configurar el Mensaje
    private static void configureMensaje(Mensaje mensaje, Integer rutEnv, String nombreEnv, String rolEnv, String subject, String glosaPrincipal, String glosaFinal, String key, MensajeSupport mensajeSupport) {
        mensaje.setMsgRutEnv(rutEnv);
        mensaje.setMsgNombreEnv(nombreEnv);
        mensaje.setMsgRolEnv(rolEnv);
        mensaje.setMsgSubject(subject);
        mensaje.setMsgMsg(glosaPrincipal + "\n" + glosaFinal);
        mensaje.setMsgPara(mensajeSupport.getPara());
        mensaje.setMsgFecha(getSysdate());
        mensaje.setMsgIdSession(key);
        mensaje.setMsgTipo("TXT");
        mensajeSupport.setMensaje(mensaje);
    }

    // Método para adjuntar archivos al mensaje
    private static void setAttachFiles(String[] files, Mensaje mensaje) {
        if (files == null || files.length == 0) {
            mensaje.setMsgAttach("N");
            mensaje.setMensajeAttachList(null);
            return;
        }

        List<MensajeAttach> attachList = Arrays.stream(files)
                .map(file -> {
                    MensajeAttach mensajeAttach = new MensajeAttach();
                    mensajeAttach.setMenaAttachFile(file);
                    return mensajeAttach;
                })
                .collect(Collectors.toList());

        mensaje.setMsgAttach("S");
        mensaje.setMensajeAttachList(attachList);
    }
}
