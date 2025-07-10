package infrastructure.util.common;

import domain.model.EstadoDocExp;
import domain.model.SolicitudAttach;
import domain.model.SolicitudAttachId;
import domain.model.TDocExpediente;
import domain.model.TdocumentoSolicitud;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.normalizaFileName;
import infrastructure.util.HibernateUtil;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Alvaro
 */
public class CommonSolicitudExpedienteUtil {

    private CommonSolicitudExpedienteUtil() {
    }

    public static void saveAttach(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, Integer tdoc, String key) {
        // Obtener la sesión de trabajo de la solicitud usando la clave proporcionada
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtener la lista de archivos adjuntos de la solicitud
        List<SolicitudAttach> attachList = getAttachFiles(action, genericSession, upload, uploadFileName, tdoc, key);

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
            ContextUtil.getDAO().getSolicitudAttachPersistence(ActionUtil.getDBUser()).save(attach);
        });

        // Confirmar la transacción de Hibernate
        HibernateUtil.commitTransaction();
    }

    private static List<SolicitudAttach> getAttachFiles(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, Integer tdoc, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

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
                // aca llamar a el nombre del documento
                IntStream.range(0, attachList.size()).forEach(i -> {
                    SolicitudAttach attach = attachList.get(i);
                    String nombre = getAttachFileName(attach.getSolaAttachFile(), "_SOL_" + i, folio);

                    try {
                        ContextUtil.getDAO().getEstadoDocExpPersistence(ActionUtil.getDBUser()).updateFile(ws.getEstadoDocExpList().get(0).getId().getEdeRut(), ws.getEstadoDocExpList().get(0).getId().getEdeCodCar(), ws.getEstadoDocExpList().get(0).getId().getEdeAgnoIng(), ws.getEstadoDocExpList().get(0).getId().getEdeSemIng(), ws.getEstadoDocExpList().get(0).getId().getEdeCorrelLogro(), tdoc, nombre);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Índice fuera de rango: ");
                    } catch (NullPointerException e) {
                        System.err.println("Referencia nula encontrada: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Error general: " + e.getMessage());
                        e.printStackTrace();
                    }

                    attach.setSolaAttachFile(nombre);

                    // Subir el archivo al servidor
                    try {
                        // Subir el archivo al servidor
                        doUpload(action, files[i], nombre, "tit");
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

    public static String obtenerDescripcionPorId(List<EstadoDocExp> lista, int idBuscado) {
        for (EstadoDocExp estado : lista) {
            TDocExpediente tipo = estado.gettDocExpediente();
            if (tipo != null && tipo.getTdeCod() == idBuscado) {
                return normalizaFileName(tipo.getTdeDes());
            }
        }
        return null; // o algún valor por defecto
    }
}
