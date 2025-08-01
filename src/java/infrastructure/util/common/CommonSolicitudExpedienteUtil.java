package infrastructure.util.common;

import domain.model.AluCar;
import domain.model.EstadoDocExp;
import domain.model.SolicitudAttach;
import domain.model.TDocExpediente;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.normalizaFileName;
import static infrastructure.util.FormatUtil.sanitizeFileName;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        // Obtener la lista de archivos adjuntos de la solicitud
        getAttachFiles(action, genericSession, upload, uploadFileName, tdoc, key);
    }

    private static void getAttachFiles(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, Integer tdoc, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();

        String datoAlu = aluCar.getId().getAcaRut() + "-" + aluCar.getId().getAcaCodCar() + "-" + aluCar.getId().getAcaAgnoIng() + "-" + aluCar.getId().getAcaSemIng();
        String logro = ws.getExpedienteLogro().getPlanLogro().getLogro().getLogrDes();
        String documento = ws.getEstadoDocExpList().
                stream().
                map(EstadoDocExp::gettDocExpediente).
                filter(doc -> doc != null && Objects.equals(doc.getTdeCod(), tdoc)).
                map(TDocExpediente::getTdeDes).findFirst().orElse(null); // o "No encontrada", o lo que prefieras

        List<SolicitudAttach> attachList = new ArrayList<>();

        try {
            // Verificar si hay archivos subidos
            if (upload != null && upload.length > 0) {
                Integer folio = CommonSequenceUtil.getDocumentSeq();
                File[] files = new File[upload.length];

                // Crear objetos de solicitud adjunta para cada archivo
                IntStream.range(0, upload.length).forEach(i -> {
                    String fileName = uploadFileName[i];
                    // Verificar que el archivo sea un PDF
                    if (fileName != null && !fileName.toLowerCase().endsWith(".pdf")) {
                        // Aquí podrías lanzar una excepción o simplemente agregar un mensaje de error
                        //System.err.println("El archivo " + fileName + " no es un archivo PDF.");
                        return;  // Devolvemos para evitar procesar archivos que no sean PDFs
                    }

                    SolicitudAttach attach = new SolicitudAttach();
                    attach.setSolaAttachFile(uploadFileName[i]);
                    files[i] = upload[i];
                    attachList.add(attach);
                });

                // Renombrar cada archivo y subirlo al servidor
                // aca llamar a el nombre del documento
                IntStream.range(0, attachList.size()).forEach(i -> {
                    SolicitudAttach attach = attachList.get(i);
                    String nombre = sanitizeFileName(datoAlu + "-" + logro + "-" + documento + "-SOL-" + folio + ".pdf");

                    try {
                        ContextUtil.getDAO().getEstadoDocExpRepository(ActionUtil.getDBUser()).updateFile(ws.getEstadoDocExpList().get(0).getId().getEdeRut(), ws.getEstadoDocExpList().get(0).getId().getEdeCodCar(), ws.getEstadoDocExpList().get(0).getId().getEdeAgnoIng(), ws.getEstadoDocExpList().get(0).getId().getEdeSemIng(), ws.getEstadoDocExpList().get(0).getId().getEdeCorrelLogro(), tdoc, nombre);
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
    }

    public static String obtenerDescripcionPorId(List<EstadoDocExp> lista, int idBuscado) {
        for (EstadoDocExp estado : lista) {
            TDocExpediente tipo = estado.gettDocExpediente();
            if (tipo != null && tipo.getTdeCod() == idBuscado) {
                return normalizaFileName(tipo.getTdeDes());
            }
        }
        return null;
    }
}
