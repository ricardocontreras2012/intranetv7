package infrastructure.util.common;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.Alumno;
import domain.model.AluCar;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import static java.security.Security.addProvider;
import org.apache.struts2.ServletActionContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import static infrastructure.util.FormatUtil.normalizaFileName;

public abstract class CommonAlumnoPrintUtil {

    public static Font fontBig = PdfUtil.getFont("tahoma", 12.0f, Font.NORMAL);
    public static Font fontSmall = PdfUtil.getFont("tahoma", 6.5f, Font.NORMAL);
    public static Font fontMed = PdfUtil.getFont("tahoma", 7.5f, Font.NORMAL);
    public static Font font = PdfUtil.getFont("tahoma", 8.0f, Font.NORMAL);

    public static final Color[] COLORS = {new Color(255, 255, 255), new Color(240, 240, 240)};
    
    AluCar aluCar;

    public static PdfPTable getTableTwoCols() {
        return createTable(2, new float[]{30, 70}, 512);
    }

    // Método auxiliar para crear una tabla
    public static PdfPTable createTable(int columns, float[] widths, float totalWidth) {
        PdfPTable table = new PdfPTable(columns);
        try {
            table.setTotalWidth(totalWidth);
            table.setWidths(widths);
            table.setLockedWidth(true);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return table;
    }

    // Método auxiliar para agregar una celda a una tabla
    protected static void addCell(PdfPTable table, String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        table.addCell(cell);
    }

    /**
     * Método auxiliar para formatear números decimales con 2 decimales.
     */
    public static String formatDecimal(float value) {
        return String.format("%.2f", value);
    }

    // Método auxiliar para agregar una fila (dos celdas) a una tabla
    protected static void addRow(PdfPTable table, String label, String value) {
        addCell(table, label, font, Element.ALIGN_LEFT);
        addCell(table, value, font, Element.ALIGN_LEFT);
    }

    protected static void addEmptyRow(PdfPTable table) {
        addCell(table, " ", font, Element.ALIGN_LEFT);
        addCell(table, " ", font, Element.ALIGN_LEFT);
    }

    protected static PdfPCell getPdfPCell(String txt, int align, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(txt, fontSmall));
        cell.setHorizontalAlignment(align);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(bgColor);
        return cell;
    }

    public  class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;

        public HeaderFooterPageEvent() {
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
            Alumno alumno = aluCar.getAlumno();
            Image background = null;
            try {
                background = Image.getInstance(ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.CERTIFICATE_BACKGROUND_CLEAN_IMAGE_PATH));
            } catch (Exception e) {
                e.printStackTrace();
            }

            float width = 612;
            float height = 792;
            try {
                writer.getDirectContentUnder()
                        .addImage(background, width, 0, 0, height, 0, 0);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            PdfContentByte canvas = writer.getDirectContent();

            // Asegúrate de agregar el encabezado completo
            PdfPTable header = createTable(1, new float[]{100}, 562);  // Ajusta el tamaño según sea necesario
            addCell(header, DateUtil.getFormattedDate(DateUtil.getSysdate(), "dd-MM-yyyy hh:mm:ss"), fontSmall, Element.ALIGN_RIGHT);
            addCell(header, "UNIVERSIDAD DE SANTIAGO DE CHILE", fontBig, Element.ALIGN_CENTER);
            addCell(header, aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()), fontBig, Element.ALIGN_CENTER);  // Cambia esto si es necesario
            addCell(header, " ", fontBig, Element.ALIGN_CENTER); // Celda vacía
            addCell(header, getDocumentTitle().toUpperCase(), fontBig, Element.ALIGN_CENTER);  // Ajusta el título según sea necesario
            header.writeSelectedRows(0, -1, 0, 750, canvas);  // Ajusta las coordenadas se

            PdfPTable tHeader = getTableTwoCols();
            addRow(tHeader, "Nombre:", alumno.getNombre());
            addRow(tHeader, "Cédula Nacional de Identidad N°:", alumno.getAluRut() + "-" + alumno.getAluDv());
            addRow(tHeader, "Carrera:", aluCar.getNombreCarrera());
            addRow(tHeader, "Plan de Estudios: Resolución(es):", aluCar.getPlan().getPlaResoluciones());
            addRow(tHeader, "Ingreso:", aluCar.getId().getAcaAgnoIng().toString());
            addRow(tHeader, "Matrícula:", aluCar.getUltimaMatricula());
            tHeader.writeSelectedRows(0, -1, 50, 650, writer.getDirectContent());

            float[] columnWidths = {50, 50};
            PdfPTable table = new PdfPTable(2);
            try {
                table.setTotalWidth(512);
                table.setWidths(columnWidths);
                table.setLockedWidth(true);
                table.writeSelectedRows(0, -1, 34, 700, writer.getDirectContent());
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        private void addFooter(PdfWriter writer) {
            CommonPDFUtil.addFooter(writer, font, total);
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            CommonPDFUtil.onCloseDocument(writer, document, template, font);
        }
    }

    // Métodos abstractos que las clases hijas implementarán
    protected abstract void putDetail(Document document, AluCar aluCar) throws DocumentException;

    protected abstract String getDocumentTitle();

    // Método Template
    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        try {
            WorkSession ws = genericSession.getWorkSession(key);
            aluCar = ws.getAluCar();
            String documentTitle = getDocumentTitle();
            String name = normalizaFileName(documentTitle.toLowerCase()) + "_" + aluCar.getId().getAcaRut() + ".pdf";

            InputStream input = generatePdf(genericSession, name, aluCar, documentTitle);
            String description = FormatUtil.getMimeType(name);

            return new ActionInputStreamUtil(name, description, input);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private InputStream generatePdf(GenericSession genericSession, String name, AluCar aluCar, String title) throws Exception {
        Document doc = new Document(PageSize.LETTER);
        doc.setMargins(50.0f, 50.0f, 220.0f, 150.0f);
        doc.addTitle(title);
        doc.addAuthor("FAE-USACH");
        doc.addSubject(aluCar.getAlumno().getNombre());
        doc.addCreator("Intranet FAE: " + DateUtil.getSysdate());
        doc.addCreationDate();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(doc, buffer);

        HeaderFooterPageEvent hf = new HeaderFooterPageEvent();
        writer.setPageEvent(hf);
        addProvider(new BouncyCastleProvider());

        doc.open();
        //putHeader(doc, writer, aluCar, title);
        putDetail(doc, aluCar);
        doc.close();

        CommonCertificacionUtil.writeFile(buffer, SystemParametersUtil.PATH_TEMP_FILES + name);
        buffer.close();

        LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut());

        return CommonArchivoUtil.getFile(name, "tmp");
    }
}
