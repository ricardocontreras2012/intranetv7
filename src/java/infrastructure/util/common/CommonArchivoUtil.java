package infrastructure.util.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.err;
import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Paths.get;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.openInputStream;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.FormatUtil.normalizaFileName;
import static infrastructure.util.SystemParametersUtil.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Utilidad para gestionar archivos en la intranet, como subir, eliminar y
 * obtener archivos de diferentes directorios. Esta clase se encarga de
 * gestionar los archivos de acuerdo al tipo que se le indique (material,
 * mensajes, solicitudes, etc.).
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonArchivoUtil {

    // Constructor privado para evitar la instanciación de esta clase utilitaria
    private CommonArchivoUtil() {
    }

    private static final Map<String, String> PATH_MAP = new HashMap<>();

    static {
        PATH_MAP.put("mat", PATH_MATERIALES);
        PATH_MAP.put("msg", PATH_ATTACH_MESSAGES);
        PATH_MAP.put("sol", PATH_ATTACH_SOLICITUDES);
        PATH_MAP.put("cert", PATH_CERT);
        PATH_MAP.put("acta", PATH_ACTAS);
        PATH_MAP.put("tmp", PATH_TEMP_FILES);
        PATH_MAP.put("sit", PATH_SITUACIONES);
        PATH_MAP.put("conv", PATH_CONV);
        PATH_MAP.put("prog", PATH_PROG);
        PATH_MAP.put("tit", PATH_TITULACION);
    }

    /**
     * Sube un archivo de material de apoyo a la docencia al servidor.
     *
     * @param action Objeto que maneja las acciones de la interfaz de usuario.
     * @param upload El archivo a subir.
     * @param name El nombre del archivo.
     * @param tipo El tipo de archivo, que determinará la carpeta de destino.
     * @throws Exception Si ocurre un error al subir el archivo.
     */
    public static void doUpload(ActionCommonSupport action, File upload, String name, String tipo) throws Exception {
        if (upload != null) {
            try {
                // Copia el archivo al destino según el tipo
                copyFile(upload, new File(getServerPath(tipo) + name));
            } catch (IOException e) {
                // Si ocurre un error, se agrega un mensaje de error en la acción y se vuelve a lanzar la excepción
                action.addActionError(action.getText("struts.messages.error.uploading") + "  " + name);
                throw e;
            }
        }
    }

    /**
     * Genera el nombre del archivo de material de apoyo a la docencia.
     *
     * @param uploadFileName El nombre original del archivo cargado.
     * @param folio El número de folio para asignar al archivo.
     * @return El nombre generado del archivo.
     */
    public static String getMaterialFileName(String uploadFileName, Integer folio) {
        return generateFileName(uploadFileName, null, folio);
    }

    /**
     * Genera el nombre del archivo adjunto de un mensaje.
     *
     * @param uploadFileName El nombre original del archivo cargado.
     * @param sequence Una secuencia para añadir al nombre del archivo.
     * @param folio El número de folio para asignar al archivo.
     * @return El nombre generado del archivo.
     */
    public static String getAttachFileName(String uploadFileName, String sequence, Integer folio) {
        return generateFileName(uploadFileName, sequence, folio);
    }

    /**
     * Genera el nombre del archivo adjunto de una solicitud.
     *
     * @param uploadFileName El nombre original del archivo cargado.
     * @param correlSol El número de correlativo de la solicitud.
     * @param correlDoc El número de correlativo del documento.
     * @return El nombre generado del archivo.
     */
    public static String getSolicitudAttachFileName(String uploadFileName, Integer correlSol, Integer correlDoc) {
        return generateFileName(uploadFileName, correlSol + "_" + correlDoc, null);
    }

    /**
     * Método auxiliar que genera el nombre del archivo tomando en cuenta el
     * nombre original, una secuencia y el folio.
     *
     * @param uploadFileName El nombre original del archivo cargado.
     * @param sequence Una secuencia o identificador adicional para el archivo.
     * @param folio El número de folio o correlativo.
     * @return El nombre generado del archivo.
     */
    private static String generateFileName(String uploadFileName, String sequence, Integer folio) {
        int posPunto = uploadFileName.lastIndexOf('.');
        String ext = uploadFileName.substring(posPunto); // Obtiene la extensión del archivo
        String nombre = uploadFileName.substring(0, posPunto); // Obtiene el nombre sin la extensión

        // Normaliza el nombre del archivo y le agrega el sufijo o el folio si es necesario
        StringBuilder fileName = new StringBuilder(normalizaFileName(nombre));
        if (sequence != null) {
            fileName.append(sequence);
        }
        if (folio != null) {
            fileName.append('_').append(folio);
        }
        fileName.append(ext); // Agrega la extensión al final del nombre

        return fileName.toString();
    }

    /**
     * Elimina un archivo del sistema de archivos si existe.
     *
     * @param fileName El nombre del archivo que se desea eliminar.
     */
    public static void deleteFile(String fileName) {
        try {
            // Elimina el archivo si existe
            deleteIfExists(get(fileName));
        } catch (IOException e) {
            // Si ocurre un error al eliminar el archivo, se imprime en la salida de error
            err.println("Error eliminando archivo " + fileName);
        }
    }

    /**
     * Verifica si un archivo existe en el sistema de archivos.
     *
     * @param fileName El nombre del archivo a verificar.
     * @return true si el archivo existe, false si no existe.
     */
    public static boolean exists(String fileName) {
        return new File(fileName).exists(); // Devuelve si el archivo existe o no
    }

    /**
     * Obtiene un archivo desde el servidor de acuerdo al nombre y tipo de
     * archivo.
     *
     * @param name El nombre del archivo a obtener.
     * @param tipo El tipo de archivo que determinará la carpeta.
     * @return Un InputStream para leer el archivo.
     * @throws Exception Si ocurre un error al obtener el archivo.
     */
    public static InputStream getFile(String name, String tipo) throws Exception {
        InputStream retValue = null;

        if (name != null) {
            // Abre el archivo en la ruta determinada por el tipo
            retValue = openInputStream(new File(getServerPath(tipo) + name));
        }

        return retValue;
    }

    /**
     * Obtiene la ruta en el servidor de acuerdo al tipo de archivo.
     *
     * @param tipo El tipo de archivo que determinará la carpeta de destino.
     * @return La ruta completa del servidor para el tipo de archivo.
     */    
    public static String getServerPath(String tipo) {
        return Optional.ofNullable(PATH_MAP.get(tipo))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de archivo desconocido: " + tipo));
    }
}
