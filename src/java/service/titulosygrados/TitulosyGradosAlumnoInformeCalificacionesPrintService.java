package service.titulosygrados;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
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
import domain.model.Unidad;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import static java.security.Security.addProvider;
import java.util.Date;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import session.GenericSession;
import session.WorkSession;
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
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonPDFUtil;
import java.awt.color.ICC_Profile;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;
import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TitulosyGradosAlumnoInformeCalificacionesPrintService {

    FontsPDFUtil fontsUtil = (FontsPDFUtil) getServletContext().getAttribute("fontsUtil");

    Font TA_7 = fontsUtil.crearFont("tahoma", 7, Font.NORMAL);
    Font TA_8 = fontsUtil.crearFont("tahoma", 8, Font.NORMAL);
    Font TA_8_5 = fontsUtil.crearFont("tahoma", 8.5f, Font.NORMAL);
    Font TA_8_5B = fontsUtil.crearFont("tahoma", 8.5f, Font.BOLD);
    Font TNR_14 = fontsUtil.crearFont("times", 14, Font.BOLD);

    /**
     * Method description
     *
     * @param genericSession
     * @param key
     * @return
     * @throws java.lang.Exception
     */
    public ActionInputStreamUtil service(GenericSession genericSession,
            String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        String name;
        String description;

        Integer folio = CommonCertificacionUtil.getFolio();
        name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, 21);
        description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(genericSession, ws.getAluCar(), folio, name));
    }

    private InputStream getInput(GenericSession genericSession, AluCar aca, Integer folio, String name) throws Exception {

        Integer genera = genericSession.getRut();
        Unidad facultad = aca.getUnidadFacultad();
        String iniciales = ContextUtil.getDAO().getScalarPersistence("TG").getIniciales(genericSession.getRut(), "Funcionario de Títulos y Grados");
        String codigo = CommonCertificacionUtil.getVerificador(folio);
        Date fecha = getSysdate();

        Document doc = new Document(PageSize.LETTER);

        doc.setMargins(50.0f, 50.0f, 250.0f, 150.0f);
        doc.addTitle("Informe de Calificaciones");
        doc.addAuthor("FAE-USACH");
        doc.addSubject(aca.getAlumno().getNombre());
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

        TitulosyGradosAlumnoInformeCalificacionesPrintService.FooterPageEvent hf = new TitulosyGradosAlumnoInformeCalificacionesPrintService.FooterPageEvent(folio, codigo, "");
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
        String xmpMetadata = PdfUtil.createValidXMPMetadata("INFORME DE CALIFICACIONES", aca.getAlumno().getNombreStd());
        writer.setXmpMetadata(xmpMetadata.getBytes(StandardCharsets.UTF_8));
        ////////////////////////////// FIN REQUISITOS PDF-A /////////////////   

        String imagePath = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH1);
        HeaderFooterEvent headerEvent = new HeaderFooterEvent(imagePath,
                "UNIVERSIDAD DE SANTIAGO DE CHILE",
                "TÍTULOS Y GRADOS",
                folio.toString(),
                "INFORME DE CALIFICACIONES", iniciales, aca);

        writer.setPageEvent(headerEvent);

        putCalificacionesMalla(doc, aca);
        putCalificacionesAdicionales(doc, aca);
        putCalificacionesOtras(doc, aca);
        putFooter(doc, facultad, fecha);
        doc.close();
        CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);
        buffer.close();

        CommonCertificacionUtil.registraLog(aca, folio, codigo, fecha, SystemParametersUtil.I7, null, null, null, "", "", genera, "TG");
        LogUtil.setLog(genera, aca.getId().getAcaRut());

        return CommonArchivoUtil.getFile(name, "cert");
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

    private void putFooter(Document doc, Unidad facultad, Date fecha) throws Exception {
        float[] columnWidths = {60, 40};
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);

        //Paragraph p2 = new Paragraph("La autenticidad de este certificado podrá ser verificada en " + facultad.getUniUrl(), font);
        PdfPCell c1 = new PdfPCell(new Paragraph(DateUtil.getFechaCiudad(fecha), TA_8));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);
        doc.add(table);
    }

    private PdfPCell getPdfPCell(String txt, int align, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(txt, TA_8));
        cell.setHorizontalAlignment(align);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(bgColor);
        return cell;
    }

    private PdfPTable creaTabla() {
        float[] columnWidths = {80, 8, 12};

        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        table.addCell(getPdfPCell("ASIGNATURA", Element.ALIGN_LEFT, myColor));
        table.addCell(getPdfPCell("NOTA", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("SEM/AÑO", Element.ALIGN_CENTER, myColor));
        table.setHeaderRows(1);

        return table;
    }

    private void putLista(Document doc, List<CalificacionCertificacionSupport> lista) {
        PdfUtil.putBlank(doc,TA_8);
        
        PdfPTable table = creaTabla();

        Color myColorGris = new Color(240, 240, 240);
        Color myColorBlanco = new Color(255, 255, 255);

        IntStream.range(0, lista.size())
                .forEach(i -> {
                    CalificacionCertificacionSupport cal = lista.get(i);
                    Color myColor = (i % 2 == 0) ? myColorBlanco : myColorGris;

                    String calNombre = cal.getCalNombre();
                    String calNota = cal.getCalNota();
                    String calSemAgno = cal.getId().getCalSem() + "/" + cal.getId().getCalAgno();

                    // Agregar las celdas a la tabla
                    table.addCell(getPdfPCell(calNombre, Element.ALIGN_LEFT, myColor));
                    table.addCell(getPdfPCell(calNota, Element.ALIGN_CENTER, myColor));
                    table.addCell(getPdfPCell(calSemAgno, Element.ALIGN_CENTER, myColor));
                });

        PdfPCell hDummy = new PdfPCell(new Phrase("", TA_8));
        hDummy.setBorder(Rectangle.NO_BORDER);
        table.addCell(hDummy);
        table.addCell(hDummy);
        table.addCell(hDummy);

        PdfPCell foot = new PdfPCell(new Paragraph("Notas : 0 a 100, nota mínima 50 (hasta 2/90) y 1 a 7, nota mínima 4 (desde 1/91); CONV=Convalidado", TA_7));
        foot.setColspan(6);
        foot.setHorizontalAlignment(Element.ALIGN_CENTER);
        foot.setBackgroundColor(new Color(204, 204, 204));
        foot.setBorder(Rectangle.NO_BORDER);

        table.addCell(foot);

        doc.add(table);
    }

    /**
     * Method description
     *
     * @param lista
     * @return
     */
    private List<CalificacionCertificacionSupport> delExentos(List<CalificacionCertificacionSupport> lista) {
        /*Iterator<CalificacionCertificacionSupport> iter = lista.iterator();

        while (iter.hasNext()) {
            if ("EXE".equals(iter.next().getCalProc())) {
                iter.remove();
            }
        }*/

        return lista;
    }

    public class FooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;

        private final Integer folio;
        private final String codigo;

        public FooterPageEvent(Integer folio, String codigo, String pagado) {
            this.folio = folio;
            this.codigo = codigo;
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
        public void onEndPage(PdfWriter writer, Document doc) {
            addFooter(writer);
        }

        private void addFooter(PdfWriter writer) {
            try {
                putBarCode(writer.getDirectContent(), folio, codigo, 0, "");
            } catch (Exception e) {
            }

            CommonPDFUtil.addFooter(writer, TA_8, total);
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            CommonPDFUtil.onCloseDocument(writer, document, template, TA_8);
        }
    }

    public class HeaderFooterEvent extends PdfPageEventHelper {

        private final Image image;
        private final String universityName;
        private final String title;
        private final String folio;
        private final String reportTitle;
        private final String iniciales;
        private final AluCar aca;

        public HeaderFooterEvent(String imagePath, String universityName, String title, String folio, String reportTitle, String iniciales, AluCar aca) throws Exception {
            this.image = Image.getInstance(imagePath);
            this.image.scaleToFit(70, 70);
            this.universityName = universityName;
            this.title = title;
            this.folio = folio;
            this.reportTitle = reportTitle;
            this.iniciales = iniciales;
            this.aca = aca;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document doc) {
            PdfContentByte canvas = writer.getDirectContent();

            // Posición de la imagen
            image.setAbsolutePosition(50, doc.getPageSize().getHeight() - 120);
            doc.add(image);

            Phrase phrase = new Phrase(iniciales, TA_8);
            float textYPosition = doc.getPageSize().getHeight() - 120 - phrase.getFont().getSize();
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 50, textYPosition, 0);

            // Crear el encabezado
            PdfPTable header = new PdfPTable(1);
            header.setTotalWidth(doc.getPageSize().getWidth());
            header.setWidthPercentage(100);
            header.addCell(titleCell(universityName));
            header.addCell(titleCell(title));

            PdfPCell folioCell = createCell("FOLIO: " + folio, Element.ALIGN_RIGHT);
            folioCell.setPaddingRight(50);
            folioCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            header.addCell(folioCell);
            header.addCell(createCell(reportTitle));

            Alumno alumno = aca.getAlumno();

            float[] columnWidths = {30, 70};
            PdfPTable aluTable = new PdfPTable(2);
            aluTable.setTotalWidth(512);
            aluTable.setWidths(columnWidths);
            aluTable.setLockedWidth(true);

            PdfPCell tnombre = new PdfPCell(new Phrase("Nombre:", TA_8_5));
            tnombre.setBorder(Rectangle.NO_BORDER);
            PdfPCell nombre = new PdfPCell(new Phrase(alumno.getNombre(), TA_8_5B));
            nombre.setBorder(Rectangle.NO_BORDER);

            PdfPCell tced = new PdfPCell(new Phrase("Cédula Nacional de Identidad N°:", TA_8_5));
            tced.setBorder(Rectangle.NO_BORDER);
            PdfPCell ced = new PdfPCell(new Phrase(alumno.getAluRut().toString() + '-' + alumno.getAluDv(), TA_8_5B));
            ced.setBorder(Rectangle.NO_BORDER);

            PdfPCell tcarrera = new PdfPCell(new Phrase("Carrera:", TA_8_5));
            tcarrera.setBorder(Rectangle.NO_BORDER);
            PdfPCell carrera = new PdfPCell(new Phrase(aca.getNombreCarrera(), TA_8_5B));
            carrera.setBorder(Rectangle.NO_BORDER);

            PdfPCell tplan = new PdfPCell(new Phrase("Plan de Estudios: Resolución(es):", TA_8_5));
            tplan.setBorder(Rectangle.NO_BORDER);
            PdfPCell plan = new PdfPCell(new Phrase(aca.getPlan().getPlaResoluciones(), TA_8_5B));
            plan.setBorder(Rectangle.NO_BORDER);

            PdfPCell tingreso = new PdfPCell(new Phrase("Ingreso:", TA_8_5));
            tingreso.setBorder(Rectangle.NO_BORDER);
            PdfPCell ingreso = new PdfPCell(new Phrase(aca.getId().getAcaAgnoIng().toString(), TA_8_5B));
            ingreso.setBorder(Rectangle.NO_BORDER);

            aluTable.addCell(tnombre);
            aluTable.addCell(nombre);
            aluTable.addCell(tced);
            aluTable.addCell(ced);
            aluTable.addCell(tcarrera);
            aluTable.addCell(carrera);
            aluTable.addCell(tplan);
            aluTable.addCell(plan);
            aluTable.addCell(tingreso);
            aluTable.addCell(ingreso);

            header.writeSelectedRows(0, -1, 0, doc.getPageSize().getHeight() - 50, canvas);
            aluTable.writeSelectedRows(0, -1, 50, doc.getPageSize().getHeight() - 150, canvas);
        }

        private PdfPCell titleCell(String text) {
            PdfPCell cell = new PdfPCell(new Phrase(text, TNR_14));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return cell;
        }

        private PdfPCell createCell(String text) {
            PdfPCell cell = new PdfPCell(new Phrase(text, TNR_14));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return cell;
        }

        private PdfPCell createCell(String text, int alignment) {
            PdfPCell cell = new PdfPCell(new Phrase(text, TA_7));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(alignment);
            return cell;
        }
    }
}
