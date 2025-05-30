package service.common;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import domain.model.AluCar;
import domain.model.Calificacion;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.ActionParameterAwareSupport;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonArchivoUtil;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonAlumnoProgramaEstudioPrintProgramasService {

    public static ActionInputStreamUtil service(ActionParameterAwareSupport action, GenericSession genericSession, Map<String, String[]> parameters, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();

        // Verificar si faltan archivos antes de procesar
        String archivosFaltan = archivosFaltan(genericSession, aluCar, parameters, key);
        if (!archivosFaltan.isEmpty()) {
            action.addActionError("Faltan\n" + archivosFaltan);
            throw new Exception("Archivos faltantes");
        }

        // Generar el input stream para el PDF final
        InputStream input = getInput(genericSession, aluCar, parameters, key);
        String name = "PROGRAMAS_" + aluCar.getId().getAcaRut() + ".pdf";
        String description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }

    // Genera el nombre del archivo PDF basado en los datos de la calificación(asignatura)
    private static String getFileName(AluCar aluCar, Calificacion calificacion) {
        String idFile = aluCar.getId().getAcaCodCar() + "-" + aluCar.getAcaCodMen() + "-" + calificacion.getAsignatura().getAsiCod();
        if ("S".equals(calificacion.getAsignatura().getAsiElect())) {
            idFile += "-" + calificacion.getId().getCalElect() + "-" + calificacion.getId().getCalAgno() + "-" + calificacion.getId().getCalSem();
        }
        return CommonArchivoUtil.getServerPath("prog") + idFile + ".pdf";
    }

    // Verifica si faltan archivos necesarios
    private static String archivosFaltan(GenericSession genericSession, AluCar aluCar, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        return IntStream.range(0, ws.getCalificaciones().size())
                .filter(pos -> parameters.get("ck_" + pos) != null)
                .mapToObj(pos -> getFileName(aluCar, ws.getCalificaciones().get(pos)))
                .filter(fileName -> !CommonArchivoUtil.exists(fileName))
                .collect(Collectors.joining("\n"));
    }

    // Genera el InputStream con el PDF final
    private static InputStream getInput(GenericSession genericSession, AluCar aluCar, Map<String, String[]> parameters, String key) throws IOException {
        WorkSession ws = genericSession.getWorkSession(key);
        String outFile = SystemParametersUtil.PATH_TEMP_FILES + "prog_" + aluCar.getId().getAcaRut() + ".pdf";
        Document document = null;
        PdfCopy writer = null;

        try {
            // Combinamos los PDFs de las calificaciones seleccionadas
            for (int pos = 0; pos < ws.getCalificaciones().size(); pos++) {
                if (parameters.get("ck_" + pos) != null) {
                    String fileName = getFileName(aluCar, ws.getCalificaciones().get(pos));

                    try (PdfReader reader = new PdfReader(fileName)) {
                        reader.consolidateNamedDestinations();
                        int n = reader.getNumberOfPages();

                        if (document == null) {
                            document = new Document(reader.getPageSizeWithRotation(1));
                            writer = new PdfCopy(document, new FileOutputStream(outFile));
                            document.open();
                        }

                        // Importar las páginas del archivo PDF
                        for (int i = 1; i <= n; i++) {
                            PdfImportedPage page = writer.getImportedPage(reader, i);
                            writer.addPage(page);
                        }

                        PRAcroForm form = reader.getAcroForm();
                        if (form != null) {
                            writer.copyAcroForm(reader);
                        }
                    }
                }
            }

            // Finalizamos el documento
            if (document != null) {
                document.close();
            }

            // Agregar imagen de sello
            Image img = Image.getInstance(getServletContext().getRealPath(SystemParametersUtil.CERTIFICATE_TIMBRE_PROGRAMAS_IMAGE_PATH));
            img.setAbsolutePosition(300, 0);
            img.scaleToFit(200, 500);

            // Crear un PdfStamper para añadir el sello y los textos adicionales
            PdfReader reader = null;
            PdfStamper stamper = null;
            try {
                reader = new PdfReader(outFile);
                String outFileFinal = SystemParametersUtil.PATH_TEMP_FILES + "prog__" + aluCar.getId().getAcaRut() + ".pdf";
                stamper = new PdfStamper(reader, new FileOutputStream(outFileFinal));
                stamper.setRotateContents(false);

                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    PdfContentByte under = stamper.getUnderContent(i);
                    under.addImage(img);

                    PdfContentByte over = stamper.getOverContent(i);
                    Phrase tPage = new Phrase("Pág " + i + " de " + reader.getNumberOfPages(), new Font(Font.COURIER, 10));
                    float xt = reader.getPageSize(i).getWidth() - 30;
                    float yt = reader.getPageSize(i).getBottom(25);
                    ColumnText.showTextAligned(over, Element.ALIGN_RIGHT, tPage, xt, yt, 0);

                    Phrase t1 = new Phrase("EL REGISTRO CURICULAR DE LA FACULTAD DE ADMINISTRACIÓN Y ECONOMÍA", new Font(Font.COURIER, 6));
                    Phrase t2 = new Phrase("DE LA UNIVERSIDAD DE SANTIAGO DE CHILE, CERTIFICA QUE:", new Font(Font.COURIER, 6));
                    Phrase t3 = new Phrase(aluCar.getAlumno().getNombreStd(), new Font(Font.COURIER, 6, Font.UNDERLINE | Font.BOLD));
                    Phrase t4 = new Phrase("APROBÓ SATISFACTORIAMENTE LA ASIGNATURA DEL PLAN DE ESTUDIO", new Font(Font.COURIER, 6));

                    ColumnText.showTextAligned(over, Element.ALIGN_LEFT, t1, 50, 60, 0);
                    ColumnText.showTextAligned(over, Element.ALIGN_LEFT, t2, 50, 50, 0);
                    ColumnText.showTextAligned(over, Element.ALIGN_LEFT, t3, 50, 30, 0);
                    ColumnText.showTextAligned(over, Element.ALIGN_LEFT, t4, 50, 20, 0);
                }
            } finally {
                // Cerrar PdfStamper y PdfReader manualmente
                if (stamper != null) {
                    stamper.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }

            LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut());
            return CommonArchivoUtil.getFile("prog__" + aluCar.getId().getAcaRut() + ".pdf", "tmp");

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error al generar el archivo PDF", e);
        }
    }
}
