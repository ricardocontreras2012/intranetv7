/*
 * @(#)LogUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import static com.opensymphony.xwork2.ActionContext.getContext;
import domain.model.Curso;
import static org.apache.logging.log4j.LogManager.getLogger;
import org.apache.logging.log4j.Logger;
import static org.apache.struts2.ServletActionContext.getRequest;
import static infrastructure.util.FormatUtil.msgLog;

/**
 * Clase de utilidad para el registro de logs en la aplicación.
 * <p>
 * Esta clase proporciona métodos para registrar excepciones, mensajes de información,
 * y datos relevantes en los logs, como la IP del usuario y el nombre de la acción.
 * </p>
 * 
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class LogUtil {

    private static final Logger LOGGER = getLogger(LogUtil.class.getName());

    /**
     * Registra el mensaje de error para una excepción, incluyendo información
     * sobre la clase, método y línea donde ocurrió el error.
     *
     * @param t Excepción que contiene la información del error.
     */
    public static void logExceptionMessage(Throwable t) {
        String logMessage = getExceptionLogMessage(t.getStackTrace()[0], t.getMessage());
        LOGGER.error(logMessage);
    }

    /**
     * Registra el mensaje de error para una excepción, iterando sobre el stack trace
     * para obtener detalles de cada traza, hasta encontrar "action.AbstractAction".
     *
     * @param e Excepción a ser registrada.
     */
    public static void logExceptionMessage(Exception e) {
        for (StackTraceElement element : e.getStackTrace()) {
            String logMessage = getExceptionLogMessage(element, e.getMessage());
            LOGGER.error(logMessage);

            if ("action.AbstractAction".equals(element.getClassName())) {
                break; // Termina la iteración si se encuentra la clase "AbstractAction"
            }
        }
    }

    /**
     * Registra un mensaje informativo en el log.
     * 
     * @param info Mensaje informativo que será registrado.
     */
    public static void logInfo(String info) {
        String logAction = msgLog(info.replace("'", "'''"));
        LOGGER.info(logAction);
    }

    /**
     * Registra el mensaje de log con el rut y la ID de la acción.
     * 
     * @param rut Número de identificación del usuario.
     */
    public static void setLog(Integer rut) {
        logInfo(formatLogMessage(rut));
    }

    /**
     * Registra el mensaje de log con el rut y un mensaje adicional.
     * 
     * @param rut Número de identificación del usuario.
     * @param msg Mensaje adicional a registrar.
     */
    public static void setLog(Integer rut, String msg) {
        logInfo(formatLogMessage(rut, msg));
    }

    /**
     * Registra el mensaje de log con dos números de rut.
     * 
     * @param rut Número de identificación del primer usuario.
     * @param rutMsg Número de identificación del segundo usuario.
     */
    public static void setLog(Integer rut, Integer rutMsg) {
        logInfo(formatLogMessage(rut, rutMsg));
    }

    /**
     * Registra el mensaje de log con el rut y la información de un curso.
     * 
     * @param rut Número de identificación del usuario.
     * @param curso Objeto curso que contiene la información del curso.
     */
    public static void setLogCurso(Integer rut, Curso curso) {
        logInfo(formatLogMessage(rut, curso.getNombreCorto()));
    }

    /**
     * Método privado para obtener la información de la excepción formateada.
     * 
     * @param element Elemento del stack trace que contiene la clase, el método y la línea.
     * @param message Mensaje de la excepción.
     * @return El mensaje de log formateado.
     */
    private static String getExceptionLogMessage(StackTraceElement element, String message) {
        return msgLog(String.format("%s:%s:%d %s", element.getClassName(), element.getMethodName(), element.getLineNumber(), message));
    }

    /**
     * Método privado para obtener un mensaje de log formateado con la IP y el nombre de la acción.
     * 
     * @return Un mensaje de log que incluye la dirección IP y el nombre de la acción.
     */
    private static String getId() {
        return String.format(" IP:%s %s", getRequest().getRemoteAddr(), getContext().getActionName());
    }

    /**
     * Método privado para formatear el mensaje de log con el rut y otros detalles.
     * 
     * @param rut Número de identificación del usuario.
     * @return El mensaje de log formateado con el rut y la ID de la acción.
     */
    private static String formatLogMessage(Integer rut) {
        return msgLog(rut + getId());
    }

    /**
     * Método privado para formatear el mensaje de log con el rut y un mensaje adicional.
     * 
     * @param rut Número de identificación del usuario.
     * @param msg Mensaje adicional.
     * @return El mensaje de log formateado con el rut y el mensaje adicional.
     */
    private static String formatLogMessage(Integer rut, String msg) {
        return msgLog(rut + getId() + " > " + msg);
    }

    /**
     * Método privado para formatear el mensaje de log con el rut y otro rut.
     * 
     * @param rut Número de identificación del primer usuario.
     * @param rutMsg Número de identificación del segundo usuario.
     * @return El mensaje de log formateado con el rut y el otro rut.
     */
    private static String formatLogMessage(Integer rut, Integer rutMsg) {
        return msgLog(rut + getId() + " > " + rutMsg);
    }
}
