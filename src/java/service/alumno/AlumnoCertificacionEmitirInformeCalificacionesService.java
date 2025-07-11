/*
 * @(#)AlumnoCertificacionEmitirInformeCalificacionesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfArray;
import com.lowagie.text.pdf.PdfBoolean;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfIndirectObject;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfStream;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Alumno;
import domain.model.Tramite;
import domain.model.Unidad;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.security.Security.addProvider;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import infrastructure.support.CalificacionCertificacionSupport;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import static infrastructure.util.BarCodeUtil.putBarCode;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FontsPDFUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonPDFUtil;
import infrastructure.util.common.CommonSimpleMessageUtil;
import java.awt.color.ICC_Profile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoCertificacionEmitirInformeCalificacionesService {

    /*static Font TA_12 = PdfUtil.getFont("tahoma", 12.0f, NORMAL);
    static Font TA_7 = PdfUtil.getFont("tahoma", 7.0f, NORMAL);
    static Font TA_8 = PdfUtil.getFont("tahoma", 8.0f, NORMAL);
    static Font font = PdfUtil.getFont("tahoma", 8.5f, NORMAL);*/
    FontsPDFUtil fontsUtil = (FontsPDFUtil) getServletContext().getAttribute("fontsUtil");

    Font TA_7 = fontsUtil.crearFont("tahoma", 7, Font.NORMAL);
    Font TA_8 = fontsUtil.crearFont("tahoma", 8, Font.NORMAL);
    Font TA_8_5 = fontsUtil.crearFont("tahoma", 8.5f, Font.NORMAL);
    Font TA_12 = fontsUtil.crearFont("tahoma", 12, Font.NORMAL);

    /**
     * Method description
     *
     * @param correl
     * @return
     */
    public ActionInputStreamUtil service(Integer correl) {

        Map<String, String> mapParams = CommonCertificacionUtil.getParams(correl);

        Integer tramite = Integer.parseInt(mapParams.get("tramite"));
        String obs = mapParams.get("glosa");
        Integer folio = Integer.parseInt(mapParams.get("folio"));
        String name = mapParams.get("archivo");
        Integer genera = Integer.parseInt(mapParams.get("genera"));
        String type = mapParams.get("type");
        String session = mapParams.get("session");
        Integer monto = Integer.parseInt(mapParams.get("monto"));
        String description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(correl, tramite, obs, folio, name, genera, type, CommonCertificacionUtil.getPagoString(monto), session));
    }

    public InputStream getInput(Integer correl, Integer codTramite, String obs, Integer folio, String name, Integer genera, String type, String pagado, String key) {

        AluCar aluCar = CommonCertificacionUtil.getAluCar(correl);
        Unidad facultad = aluCar.getUnidadFacultad();
        String codigo = CommonCertificacionUtil.getVerificador(folio);
        Date fecha = getSysdate();

        try {
            Document doc = new Document(PageSize.LETTER);
            doc.setMargins(50.0f, 50.0f, 150.0f, 150.0f);
            doc.addTitle("Informe de Calificaciones");
            doc.addAuthor("FAE-USACH");
            doc.addSubject(aluCar.getAlumno().getNombre());
            doc.addCreator("Intranet FAE: " + fecha);
            doc.addCreationDate();

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter writer = getInstance(doc, buffer);

            /// REQUISITOS PDF-A //////////////////////////////////////////////
            // Configurar versión PDF y etiquetado
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
            writer.setTagged();

            // Configurar MarkInfo para PDF etiquetado
            PdfDictionary markInfo = new PdfDictionary();
            markInfo.put(PdfName.MARKED, PdfBoolean.PDFTRUE);
            writer.getExtraCatalog().put(PdfName.MARKINFO, markInfo);

            // Crear metadatos XMP
            writer.createXmpMetadata();

            // Metadatos básicos
            writer.getInfo().put(PdfName.TITLE, new PdfString("INFORME DE CALIFICACIONES"));
            writer.getInfo().put(PdfName.CREATOR, new PdfString("FAE-USACH"));
            writer.getInfo().put(PdfName.AUTHOR, new PdfString("FAE-USACH"));
            writer.getInfo().put(PdfName.PRODUCER, new PdfString("OpenPDF"));
            ////////////////////////////// FIN REQUISITOS PDF-A /////////////////

            AlumnoCertificacionEmitirInformeCalificacionesService.HeaderFooterPageEvent hf = new AlumnoCertificacionEmitirInformeCalificacionesService.HeaderFooterPageEvent(folio, codigo, pagado);
            writer.setPageEvent(hf);
            addProvider(new BouncyCastleProvider());

            doc.open();

            /// REQUISITOS PDF-A //////////////////////////////////////////////
            // Cargar perfil ICC (sRGB)
            ICC_Profile iccProfile = PdfUtil.loadICCProfile(getServletContext().getRealPath("/images/sRGB2014.icc"));

            // Configurar OutputIntent con el perfil ICC
            PdfStream iccStream = new PdfStream(iccProfile.getData());
            iccStream.put(PdfName.N, new PdfNumber(3));
            iccStream.put(PdfName.ALTERNATE, PdfName.DEVICERGB);
            iccStream.flateCompress();

            PdfIndirectObject iccIndirect = writer.addToBody(iccStream);

            PdfDictionary outputIntent = new PdfDictionary(PdfName.OUTPUTINTENT);
            outputIntent.put(PdfName.S, PdfName.GTS_PDFA1);
            outputIntent.put(new PdfName("OutputConditionIdentifier"), new PdfString("sRGB IEC61966-2.1"));
            outputIntent.put(PdfName.DESTOUTPUTPROFILE, iccIndirect.getIndirectReference());

            PdfArray outputIntents = new PdfArray();
            outputIntents.add(outputIntent);
            writer.getExtraCatalog().put(PdfName.OUTPUTINTENTS, outputIntents);

            // Generar y asignar XMP corregido
            String xmpMetadata = PdfUtil.createValidXMPMetadata("I3: INFORME DE CALIFICACIONES", aluCar.getAlumno().getNombreStd());
            writer.setXmpMetadata(xmpMetadata.getBytes(StandardCharsets.UTF_8));
            ////////////////////////////// FIN REQUISITOS PDF-A /////////////////

            putHeader(doc, writer, aluCar);
            putCalificacionesMalla(doc, aluCar);
            putCalificacionesAdicionales(doc, aluCar);
            putCalificacionesOtras(doc, aluCar);
            putFooter(doc, aluCar.getAlumno().getAluSexo(), String.format("%.1f", aluCar.getAluCarFunction().getPromedioAprobados()), codTramite, obs, facultad, fecha);
            doc.close();
            CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);
            buffer.close();

            Files.createSymbolicLink(Paths.get(PATH_ATTACH_MESSAGES + name), Paths.get(PATH_CERT + name));

            CommonSimpleMessageUtil.send(name, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genera, "Registrador Curricular", "RC", "",
                    "", "I3", "CERTIFICADO DE CALIFICACIONES", "TM_CERT");

            CommonCertificacionUtil.registraLog(aluCar, folio, codigo, fecha, SystemParametersUtil.I3, 0, 0, codTramite, "", obs, genera, type);

            // Ojo por ahora 1 pero despues puede ser el que correponda al carrito
            ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).setEstadoCarrito(correl, 1, "EM");
            LogUtil.setLog(genera, aluCar.getId().getAcaRut());

            return CommonArchivoUtil.getFile(name, "cert");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void putHeader(Document doc, PdfWriter writer, AluCar aluCar) {
        Alumno alumno = aluCar.getAlumno();

        PdfPTable header = createHeaderTable(aluCar);
        PdfContentByte canvas = writer.getDirectContent();
        header.writeSelectedRows(0, -1, 0, 750, canvas);

        PdfPTable table = createInfoTable(aluCar, alumno);
        doc.add(table);
    }

    private PdfPTable createHeaderTable(AluCar aluCar) {
        PdfPTable header = new PdfPTable(1);
        header.setTotalWidth(512);
        header.setWidths(new float[]{100});
        header.setLockedWidth(true);

        Stream.of(
                new Phrase("UNIVERSIDAD DE SANTIAGO DE CHILE", TA_12),
                new Phrase(aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()), TA_12),
                new Phrase("REGISTRO CURRICULAR", TA_12),
                new Phrase("INFORME DE CALIFICACIONES", TA_12)
        ).forEach(phrase -> {
            PdfPCell cell = new PdfPCell(phrase);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.addCell(cell);
        });

        return header;
    }

    private PdfPTable createInfoTable(AluCar aluCar, Alumno alumno) {
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(512);
        table.setWidths(new float[]{30, 70});
        table.setLockedWidth(true);

        addCell(table, "Nombre:", alumno.getNombre());
        addCell(table, "Cédula Nacional de Identidad N°:", alumno.getAluRut().toString() + '-' + alumno.getAluDv());
        addCell(table, "Carrera:", aluCar.getNombreCarrera());
        addCell(table, "Plan de Estudios: Resolución(es):", aluCar.getPlan().getPlaResoluciones());
        addCell(table, "Ingreso:", aluCar.getId().getAcaAgnoIng().toString());

        return table;
    }

    private void addCell(PdfPTable table, String label, String value) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, TA_8_5));
        labelCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell valueCell = new PdfPCell(new Phrase(value, TA_8_5));
        valueCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    private void putCalificacionesMalla(Document doc, AluCar aluCar) {
        AluCarId id = aluCar.getId();
        List<CalificacionCertificacionSupport> cartola
                = delExentos(ContextUtil.getDAO().getCalificacionPersistence(ActionUtil.getDBUser()).getI4NotasMalla(
                        id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(),
                        id.getAcaSemIng(), aluCar.getAcaCodMen(),
                        aluCar.getAcaCodPlan()));

        putLista(doc, cartola);

    }

    private void putCalificacionesAdicionales(Document doc, AluCar aluCar) {
        List<CalificacionCertificacionSupport> adicionales = ContextUtil.getDAO().getCalificacionAdicionalLogroPersistence(ActionUtil.getDBUser()).findAprobadas(aluCar);

        if (!adicionales.isEmpty()) {
            PdfUtil.putBlank(doc, TA_8);
            Paragraph texto = new Paragraph("ASIGNATURAS ADICIONALES", TA_8_5);
            doc.add(texto);
            putLista(doc, adicionales);
        }
    }

    private void putCalificacionesOtras(Document doc, AluCar aluCar) {
        AluCarId id = aluCar.getId();
        List<CalificacionCertificacionSupport> otras = delExentos(ContextUtil.getDAO().getCalificacionPersistence(ActionUtil.getDBUser()).getI4NotasOtras(
                id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(),
                id.getAcaSemIng(), aluCar.getAcaCodMen(),
                aluCar.getAcaCodPlan()));

        if (!otras.isEmpty()) {
            PdfUtil.putBlank(doc, TA_8);
            Paragraph texto = new Paragraph("ASIGNATURAS FUERA DEL PLAN", TA_8_5);
            doc.add(texto);
            putLista(doc, otras);
        }

    }

    private void putFooter(Document doc, String sexo, String prom, Integer codTramite, String obs, Unidad facultad, Date fecha) throws Exception {
        float[] columnWidths = {60, 40};
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);

        Tramite tramite = ContextUtil.getTramiteMap().get(codTramite);
        /*String extiende = (("1".equals(sexo)) ? "La alumna" : "El alumno") + " obtuvo promedio " + prom + "\n"
                + "Se extiende el presente certificado para ser presentado en: Trámite "
                + tramite.getTraPrefijo() + ' ' + tramite.getTraDescripcion();*/

        String extiende
                = "Se extiende el presente certificado para ser presentado en: Trámite "
                + tramite.getTraPrefijo() + ' ' + tramite.getTraDescripcion();

        if (obs != null) {
            extiende += "\n" + obs;
        }

        Paragraph p1 = new Paragraph(extiende, TA_8_5);

        Paragraph p2 = new Paragraph("La autenticidad de este certificado podrá ser verificada en " + facultad.getUniUrl(), TA_8_5);

        PdfPCell c1 = new PdfPCell(new Paragraph(DateUtil.getFechaCiudad(fecha), TA_8_5));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(Rectangle.NO_BORDER);

        Image image = getInstance(getServletContext().getRealPath(SystemParametersUtil.CERTIFICATE_REGISTRADOR_IMAGE_PATH.replace("###", facultad.getUniCod().toString())));
        image.scaleAbsolute(512.0f, 692.0f);
        image.scaleToFit(200, 400);

        PdfPCell c2 = new PdfPCell(image);
        c2.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);
        table.addCell(c2);

        PdfUtil.putBlank(doc,TA_8);
        PdfUtil.putBlank(doc,TA_8);
        doc.add(p1);
        PdfUtil.putBlank(doc,TA_8);
        PdfUtil.putBlank(doc,TA_8);
        doc.add(p2);
        PdfUtil.putBlank(doc,TA_8);
        PdfUtil.putBlank(doc,TA_8);
        doc.add(table);
    }

    private PdfPCell getPdfPCell(String txt, int align, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(txt, TA_8_5));
        cell.setHorizontalAlignment(align);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(bgColor);
        return cell;
    }

    private PdfPTable creaTabla() {
        float[] columnWidths = {80, 8, 12, 10, 10, 10};

        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        // Agregar encabezados con un solo método
        List<String> headers = Arrays.asList("ASIGNATURA", "NOTA", "SEM/AÑO", "SCT", "CRED", "");
        headers.forEach(header -> table.addCell(getPdfPCell(header, Element.ALIGN_CENTER, myColor)));

        table.setHeaderRows(1);

        return table;
    }

    private void putLista(Document doc, List<CalificacionCertificacionSupport> lista) {
        PdfUtil.putBlank(doc,TA_8);

        PdfPTable table = creaTabla();

        Color myColorGris = new Color(240, 240, 240);
        Color myColorBlanco = new Color(255, 255, 255);

        // Recorremos la lista usando Stream para obtener las celdas de manera más limpia
        IntStream.range(0, lista.size()).forEach(i -> {
            CalificacionCertificacionSupport cal = lista.get(i);

            // Alternar entre los colores de fondo usando el índice
            Color myColor = (i % 2 == 0) ? myColorBlanco : myColorGris;

            // Extraer valores de cal y agregarlos a la tabla
            addCalificacionCells(table, cal, myColor);
        });

        // Agregar celdas vacías al final de la tabla
        addEmptyCells(table, 6);

        // Agregar el pie de página
        addFooter(table);

        doc.add(table);
    }

    private void addCalificacionCells(PdfPTable table, CalificacionCertificacionSupport cal, Color color) {
        Map<String, String> calValues = extractCalificacionValues(cal);

        table.addCell(getPdfPCell(calValues.get("calNombre"), Element.ALIGN_LEFT, color));
        table.addCell(getPdfPCell(calValues.get("calNota"), Element.ALIGN_CENTER, color));
        table.addCell(getPdfPCell(calValues.get("calSemAgno"), Element.ALIGN_CENTER, color));
        table.addCell(getPdfPCell(calValues.get("asiSCT"), Element.ALIGN_CENTER, color));
        table.addCell(getPdfPCell(calValues.get("asiTEL"), Element.ALIGN_CENTER, color));
        table.addCell(getPdfPCell(calValues.get("calProc"), Element.ALIGN_CENTER, color));
    }

    private static Map<String, String> extractCalificacionValues(CalificacionCertificacionSupport cal) {
        Map<String, String> values = new HashMap<>();
        values.put("calNombre", cal.getCalNombre());
        values.put("calNota", cal.getCalNota());
        values.put("calSemAgno", cal.getId().getCalSem() + "/" + cal.getId().getCalAgno());
        values.put("asiSCT", (cal.getAsiSCT() != null) ? cal.getAsiSCT().toString() : "");
        values.put("asiTEL", (cal.getAsiTEL() != null) ? cal.getAsiTEL().toString() : "");
        values.put("calProc", "CCN".equals(cal.getCalProc()) ? "CONV." : "");
        return values;
    }

    private void addEmptyCells(PdfPTable table, int numCells) {
        // Agregar celdas vacías al final de la tabla
        IntStream.range(0, numCells).forEach(i -> {
            PdfPCell emptyCell = new PdfPCell(new Phrase("", TA_8_5));
            emptyCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(emptyCell);
        });
    }

    private void addFooter(PdfPTable table) {
        PdfPCell foot = new PdfPCell(new Paragraph("Notas : 0 a 100, nota mínima 50 (hasta 2/90) y 1 a 7, nota mínima 4 (desde 1/91); CONV=Convalidado", TA_7));
        foot.setColspan(6);
        foot.setHorizontalAlignment(Element.ALIGN_CENTER);
        foot.setBackgroundColor(new Color(204, 204, 204));
        foot.setBorder(Rectangle.NO_BORDER);
        table.addCell(foot);
    }

    /**
     * Method description
     *
     * @param lista
     * @return
     */
    private static List<CalificacionCertificacionSupport> delExentos(List<CalificacionCertificacionSupport> lista) {
        Iterator<CalificacionCertificacionSupport> iter = lista.iterator();

        while (iter.hasNext()) {
            if ("EXE".equals(iter.next().getCalProc())) {
                iter.remove();
            }
        }

        return lista;
    }

    public class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;

        private final Integer folio;
        private final String codigo;
        private final String pagado;

        public HeaderFooterPageEvent(Integer folio, String codigo, String pagado) {
            this.folio = folio;
            this.codigo = codigo;
            this.pagado = pagado;
        }

        /**
         *
         * @param writer
         * @param document
         */
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            template = writer.getDirectContent().createTemplate(30, 16);
            try {
                total = Image.getInstance(template);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            addHeader(writer);
            addFooter(writer);
        }

        private void addHeader(PdfWriter writer) {
            Image background = null;
            try {
                background = getInstance(getServletContext().getRealPath(SystemParametersUtil.CERTIFICATE_BACKGROUND_CLEAN_IMAGE_PATH));
            } catch (Exception e) {
            }

            float width = 612;
            float height = 792;
            writer.getDirectContentUnder()
                    .addImage(background, width, 0, 0, height, 0, 0);

            float[] columnWidths = {65, 35};
            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(512);
            table.setWidths(columnWidths);
            table.setLockedWidth(true);

            PdfPCell cDummy = new PdfPCell(new Paragraph(""));
            cDummy.setBorder(Rectangle.NO_BORDER);
            PdfPCell cFolio = new PdfPCell(new Paragraph("Folio:" + folio, TA_8));
            cFolio.setHorizontalAlignment(Element.ALIGN_LEFT);
            cFolio.setBorder(Rectangle.NO_BORDER);

            PdfPCell cCodigo = new PdfPCell(new Paragraph("Código de Verificación:" + codigo, TA_8));
            cCodigo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cCodigo.setBorder(Rectangle.NO_BORDER);

            table.addCell(cDummy);
            table.addCell(cFolio);
            table.addCell(cDummy);
            table.addCell(cCodigo);

            table.writeSelectedRows(0, -1, 34, 700, writer.getDirectContent());
        }

        private void addFooter(PdfWriter writer) {
            try {
                putBarCode(writer.getDirectContent(), folio, codigo, 0, pagado);
            } catch (Exception e) {
            }

            CommonPDFUtil.addFooter(writer, TA_8_5, total);
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            CommonPDFUtil.onCloseDocument(writer, document, template, TA_8_5);
        }
    }
}
