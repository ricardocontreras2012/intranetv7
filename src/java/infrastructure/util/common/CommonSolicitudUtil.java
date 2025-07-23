package infrastructure.util.common;

import domain.model.SolicitudAttach;
import domain.model.SolicitudAttachId;
import domain.model.TdocumentoSolicitud;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;

/**
 * Utilidad para manejar las solicitudes y sus archivos adjuntos.
 *
 * Esta clase proporciona métodos para obtener el estado de una solicitud,
 * gestionar los archivos adjuntos asociados con las solicitudes, y guardar
 * dichos archivos en la base de datos.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class CommonSolicitudUtil {

    /**
     * Constructor privado para evitar la instanciación de esta clase
     * utilitaria.
     */
    private CommonSolicitudUtil() {
    }

    /**
     * Devuelve una representación legible del estado de una solicitud basado en
     * un código de resolución.
     *
     * @param resolucuion Código de resolución.
     * @return La descripción del estado de la solicitud.
     */
    public static String getResolucion(String resolucuion) {
        if (resolucuion == null) {
            return null;
        }

        switch (resolucuion) {
            case "A":
                return "Aprobada";
            case "R":
                return "Rechazada";
            case "P":
                return "Pendiente";
            case "AP":
                return "Aprobada Parcial";
            case "TR":
                return "En Trámite";
            default:
                return null;
        }
    }

    /**
     * Guarda los archivos adjuntos asociados con una solicitud.
     *
     * Este método procesa los archivos subidos y los guarda en la base de
     * datos, asociándolos con la solicitud correspondiente.
     *
     * @param action Instancia de la clase que gestiona la acción.
     * @param genericSession Sesión genérica que maneja las operaciones en la
     * base de datos.
     * @param upload Archivos a ser subidos.
     * @param uploadFileName Nombres de los archivos a ser subidos.
     * @param key Clave utilizada para identificar la solicitud.
     */
    public static void saveAttach(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, String key) {
        // Obtener la sesión de trabajo de la solicitud usando la clave proporcionada
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtener la lista de archivos adjuntos de la solicitud
        List<SolicitudAttach> attachList = getAttachFiles(action, upload, uploadFileName);

        // Iniciar transacción de Hibernate
        HibernateUtil.beginTransaction(ActionUtil.getDBUser());

        // Asociar cada archivo adjunto con la solicitud y guardar en la base de datos
        IntStream.range(0, attachList.size()).forEach(i -> {
            SolicitudAttach attach = attachList.get(i);
            SolicitudAttachId attachId = new SolicitudAttachId();
            TdocumentoSolicitud tDoc = new TdocumentoSolicitud();

            // Asignar código fijo al tipo de documento (se deberá cambiar pronto)
            tDoc.setTdsCod(10);

            // Configurar el ID del archivo adjunto
            attachId.setSolaCorrelAttach(i);
            attachId.setSolaCorrelSol(ws.getSolicitud().getSolFolio());

            // Establecer los valores en el objeto de archivo adjunto
            attach.setTdocumentoSolicitud(tDoc);
            attach.setId(attachId);

            // Guardar el archivo adjunto en la base de datos
            ContextUtil.getDAO().getSolicitudAttachRepository(ActionUtil.getDBUser()).save(attach);
        });

        // Confirmar la transacción de Hibernate
        HibernateUtil.commitTransaction();
    }

    /**
     * Genera una lista de objetos {@link SolicitudAttach} a partir de los
     * archivos subidos.
     *
     * Este método también renombra los archivos adjuntos y los sube al
     * servidor.
     *
     * @param action Instancia de la clase que gestiona la acción.
     * @param upload Archivos a ser subidos.
     * @param uploadFileName Nombres de los archivos a ser subidos.
     * @return Lista de objetos {@link SolicitudAttach} con la información de
     * los archivos.
     */
    private static List<SolicitudAttach> getAttachFiles(ActionCommonSupport action, File[] upload, String[] uploadFileName) {
        List<SolicitudAttach> attachList = new ArrayList<>();

        try {
            // Verificar si hay archivos subidos
            if (upload != null && upload.length > 0) {
                Integer folio = CommonSequenceUtil.getDocumentSeq();
                File[] files = new File[upload.length];

                // Crear objetos de solicitud adjunta para cada archivo
                IntStream.range(0, upload.length).forEach(i -> {
                    SolicitudAttach attach = new SolicitudAttach();
                    attach.setSolaAttachFile(uploadFileName[i]);
                    files[i] = upload[i];
                    attachList.add(attach);
                });

                // Renombrar cada archivo y subirlo al servidor
                IntStream.range(0, attachList.size()).forEach(i -> {
                    SolicitudAttach attach = attachList.get(i);
                    String nombre = getAttachFileName(attach.getSolaAttachFile(), "_SOL_" + i, folio);
                    attach.setSolaAttachFile(nombre);

                    // Subir el archivo al servidor
                    try {
                        // Subir el archivo al servidor
                        doUpload(action, files[i], nombre, "sol");
                    } catch (Exception e) {
                        e.printStackTrace();  // Manejar la excepción según sea necesario
                    }

                });
            }

        } catch (Exception e) {
            e.printStackTrace();  // Imprimir el error en caso de que falle algún proceso
        }

        return attachList;
    }
}
