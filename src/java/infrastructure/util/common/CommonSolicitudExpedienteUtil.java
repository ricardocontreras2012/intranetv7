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

    // Cambiar el tipo de retorno a String para poder retornar mensajes como "success", "failure", etc.
    public static boolean saveAttach(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, Integer tdoc, String key) {
        try {
            // Verificamos si la operación fue exitosa o falló
            boolean retValue = getAttachFiles(action, genericSession, upload, uploadFileName, tdoc, key);
            return retValue;
        } catch (Exception e) {
            //e.printStackTrace();  // Loguear el error
            return false;  // Indicar que hubo un error general
        }
    }

    private static boolean getAttachFiles(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, Integer tdoc, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();

        String datoAlu = aluCar.getId().getAcaRut() + "-" + aluCar.getId().getAcaCodCar() + "-" + aluCar.getId().getAcaAgnoIng() + "-" + aluCar.getId().getAcaSemIng();
        String logro = ws.getExpedienteLogro().getPlanLogro().getLogro().getLogrDes();
        String documento = ws.getEstadoDocExpList().
                stream().
                map(EstadoDocExp::gettDocExpediente).
                filter(doc -> doc != null && Objects.equals(doc.getTdeCod(), tdoc)).
                map(TDocExpediente::getTdeDes).findFirst().orElse(null);

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
                        // Si no es PDF, puedes devolver un error específico
                        throw new IllegalArgumentException("El archivo " + fileName + " no es un archivo PDF.");
                        //System.out.println("El archivo " + fileName + " no es un archivo PDF.");
                    }

                    SolicitudAttach attach = new SolicitudAttach();
                    attach.setSolaAttachFile(uploadFileName[i]);
                    files[i] = upload[i];
                    attachList.add(attach);
                });

                // Renombrar y subir archivos
                IntStream.range(0, attachList.size()).forEach(i -> {
                    SolicitudAttach attach = attachList.get(i);
                    String nombre = sanitizeFileName(datoAlu + "-" + logro + "-" + documento + "-SOL-" + folio + ".pdf");

                    try {
                        ContextUtil.getDAO().getEstadoDocExpRepository(ActionUtil.getDBUser()).updateFile(
                                ws.getEstadoDocExpList().get(0).getId().getEdeRut(),
                                ws.getEstadoDocExpList().get(0).getId().getEdeCodCar(),
                                ws.getEstadoDocExpList().get(0).getId().getEdeAgnoIng(),
                                ws.getEstadoDocExpList().get(0).getId().getEdeSemIng(),
                                ws.getEstadoDocExpList().get(0).getId().getEdeCorrelLogro(),
                                tdoc, nombre
                        );
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.err.println("Error al actualizar el archivo: " + e.getMessage());
                        //throw new RuntimeException("Error al actualizar el archivo");
                    }

                    attach.setSolaAttachFile(nombre);

                    try {
                        // Subir el archivo al servidor
                        doUpload(action, files[i], nombre, "tit");
                    } catch (Exception e) {
                        e.printStackTrace();  // Manejo de error al subir el archivo
                        throw new RuntimeException("Error al subir el archivo: " + e.getMessage());
                    }
                });

                return true;  // Si todo salió bien, devolvemos true
            } else {
                System.err.println("No se subieron archivos.");
                return false;  // Si no se subieron archivos, devolvemos false
            }

        } catch (Exception e) {
            e.printStackTrace();  // Loguear el error
            return false;  // Si hubo un error en algún paso, devolvemos false
        }
    }
}
