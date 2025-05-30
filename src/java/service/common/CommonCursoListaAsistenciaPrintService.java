/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.AluCar;
import domain.model.Curso;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;

/**
 *
 * @author Usach
 */
public class CommonCursoListaAsistenciaPrintService {

    static Font fontBig = PdfUtil.getFont("times", 12.0f, Font.BOLD);
    static Font fontSmall = PdfUtil.getFont("tahoma", 7.0f, Font.NORMAL);
    static Font fontMed = PdfUtil.getFont("tahoma", 8.0f, Font.NORMAL);
    static Font font = PdfUtil.getFont("tahoma", 8.5f, Font.NORMAL);
    private static final String LOGO_PATH = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH3);
    private static Image logo;

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();
        String name = StringUtils.isEmpty(ws.getNombreCarrera()) ? "" : ws.getNombreCarrera();
        String file = "NOMINA_ASISTENCIA_" + curso.getNombreFull().replace(" ", "_") + ".pdf";
        String description = FormatUtil.getMimeType(file);

        return new ActionInputStreamUtil(name, description, getInput(genericSession, ws, name, file, curso));
    }

    public InputStream getInput(GenericSession genericSession, WorkSession ws, String name, String file, Curso curso) throws Exception {

        Integer genera = genericSession.getRut();
        String fecha = DateUtil.getFormatedDate(DateUtil.getSysdate(), "dd/MM/yyyy hh:MM:ss");

        Document doc = new Document(PageSize.LETTER.rotate(), 50, 50, 150, 50);// Establecer tamaño Letter
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(doc, buffer);
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(fecha, curso); // Crear evento para manejar encabezados y pies
        writer.setPageEvent(event); // Registrar el evento

        // Cargar el logo
        logo = Image.getInstance(LOGO_PATH);
        logo.scaleToFit(60, 60); // Ajustar tamaño del logo

        doc.open();

        String[] headers = new String[36]; // 5 encabezados estáticos + 31 generados dinámicamente

        // Encabezados fijos
        headers[0] = ""; // Columna vacía
        headers[1] = "RUN";
        headers[2] = "Paterno";
        headers[3] = "Materno";
        headers[4] = "Nombre";

        // Generar las columnas adicionales (Columna1, Columna2, ..., Columna31)
        for (int i = 5; i < headers.length; i++) {
            headers[i] = ""; // Genera Columna1, Columna2, ..., Columna31
        }

        // Crear la tabla con el número adecuado de columnas
        PdfPTable table = new PdfPTable(headers.length); // Número de columnas basado en el array headers
        table.setWidthPercentage(100);

        float[] columnWidths = new float[headers.length];

        // Asignar los anchos para las primeras 6 columnas de acuerdo a la definición original
        columnWidths[0] = 6;   // Columna vacía
        columnWidths[1] = 20;  // RUN
        columnWidths[2] = 25;  // Paterno
        columnWidths[3] = 25;  // Materno
        columnWidths[4] = 40;  // Nombre

        // Para las siguientes 31 columnas (de "Columna1" a "Columna31"), asignamos un ancho predeterminado
        for (int i = 5; i < columnWidths.length; i++) {
            columnWidths[i] = 5; // O cualquier valor que quieras para las columnas adicionales
        }

        table.setWidths(columnWidths);

        for (int i = 0; i < headers.length; i++) {
            String headerText = headers[i];
            PdfPCell cell = new PdfPCell(new Phrase(headerText, fontSmall));

            // Aplicar el color de fondo solo a las primeras 5 columnas (índices 0 a 4)
            if (i < 5) {
                Color lightGray = new Color(230, 230, 230); // Gris claro
                cell.setBackgroundColor(lightGray);
            } else {
                // No se aplica color de fondo a las columnas a partir de la sexta
                cell.setBackgroundColor(Color.WHITE); 
            }

            // Configuraciones comunes para todas las celdas
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.BOX);
            cell.setFixedHeight(30); // Ajustar la altura de las celdas a 30
            table.addCell(cell);
        }

        java.util.List<AluCar> nomina = curso.getNominaAlumnos();
        Integer i = 1;

        for (AluCar aca : nomina) {                        
            PdfPCell numCell = createCell(i.toString());
            numCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(numCell);            
            PdfPCell idCell = createCell(aca.getAlumno().getAluRut() + "-" + aca.getAlumno().getAluDv());
            idCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(idCell);
            table.addCell(createCell(aca.getAlumno().getAluPaterno()));
            table.addCell(createCell(aca.getAlumno().getAluMaterno()));
            table.addCell(createCell(aca.getAlumno().getAluNombre()));

            for (int j = 0; j <= 30; j++) {
                table.addCell(createCell(" "));
            }

            i++;
        }

        doc.add(table);
        doc.close();
        CommonCertificacionUtil.writeFile(buffer, PATH_TEMP_FILES + file);
        buffer.close();

        LogUtil.setLog(genera, name);
        return CommonArchivoUtil.getFile(file, "tmp");
    }

    private PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "", fontSmall));
        cell.setBorder(PdfPCell.BOX);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
        cell.setNoWrap(true);
                
        return cell;
    }

    private class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;
        private final String fecha;
        private final Curso curso;

        HeaderFooterPageEvent(String fecha, Curso curso) {
            this.fecha = fecha;
            this.curso = curso;
        }

        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            // Crear el template para el número total de páginas
            template = writer.getDirectContent().createTemplate(30, 15);
            try {
                total = Image.getInstance(template);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            logo.setAbsolutePosition(document.leftMargin(), document.getPageSize().getHeight() - 90);
            writer.getDirectContent().addImage(logo);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("UNIVERSIDAD DE SANTIAGO DE CHILE", fontBig),
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.getPageSize().getHeight() - 50,
                    0);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
                    new Phrase(fecha, fontSmall),
                    document.right(),
                    document.getPageSize().getHeight() - 50,
                    0);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
                    new Phrase("CURSO " + curso.getNombreFull(), fontBig),
                    document.leftMargin(),
                    document.getPageSize().getHeight() - 110,
                    0);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
                    new Phrase("PROFESOR: " + curso.getCurProfesores(), fontBig),
                    document.leftMargin(),
                    document.getPageSize().getHeight() - 130,
                    0);

            PdfPTable footer = new PdfPTable(3);

            float[] columnWidths = {90f, 8f, 2f};
            footer.setWidths(columnWidths);
            footer.setTotalWidth(742);
            footer.setLockedWidth(true);

            PdfPCell cDummy = new PdfPCell(new Paragraph("", font));
            cDummy.setBorder(Rectangle.NO_BORDER);
            footer.addCell(cDummy);

            PdfPCell cPage = new PdfPCell(new Paragraph(String.format("Pág %d de", writer.getPageNumber()), fontSmall));
            cPage.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPage.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cPage.setPaddingBottom(4);
            cPage.setBorder(Rectangle.NO_BORDER);
            cPage.setPaddingRight(0);
            footer.addCell(cPage);

            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.NO_BORDER);
            totalPageCount.setHorizontalAlignment(Element.ALIGN_LEFT);
            totalPageCount.setVerticalAlignment(Element.ALIGN_MIDDLE);
            footer.addCell(totalPageCount);

            PdfContentByte canvas = writer.getDirectContent();
            footer.writeSelectedRows(0, -1, 0, 40, canvas);
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            int totalLength = String.valueOf(writer.getPageNumber() - 1).length();
            int totalWidth = totalLength * 1;

            ColumnText.showTextAligned(template, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1), fontSmall),
                    totalWidth, 5, 0);
        }
    }
}
