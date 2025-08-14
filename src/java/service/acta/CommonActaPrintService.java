/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.acta;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.Curso;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import org.apache.struts2.ServletActionContext;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Usach
 */
public class CommonActaPrintService {

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();

        String name = "notas_finales_" + curso.getCodigo("_") + ".pdf";
        String description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(genericSession.getRut(), ws, name));
    }

    private InputStream getInput(Integer rut, WorkSession ws, String name) throws Exception {
 
        Font titleFont = PdfUtil.getFont("times", 14.0f, Font.BOLD);
        Font font = PdfUtil.getFont("times", 9.0f, Font.NORMAL);
        Font smalFont = PdfUtil.getFont("times", 7.0f, Font.NORMAL);

        Curso curso = ws.getCurso();
        Date fecha = getSysdate();

        Document doc = new Document(PageSize.LETTER, 50, 50, 20, 50);
        doc.addTitle("Notas Finales");
        doc.addAuthor("USACH");
        doc.addSubject(curso.getNombreFull());
        doc.addCreator("Intranet: " + fecha);
        doc.addCreationDate();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, buffer);
       
        doc.open();

        // Agregar logo
        Image logo = Image.getInstance(ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH3));
        logo.scaleToFit(70, 70);
        logo.setAbsolutePosition(50, 700);
        doc.add(logo);

        // Títulos y detalles
        addParagraph(doc, "UNIVERSIDAD DE SANTIAGO DE CHILE", titleFont, Element.ALIGN_CENTER, 2);
        addParagraph(doc, ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getNombreFacultadxAsign(curso.getId().getCurAsign()), titleFont, Element.ALIGN_CENTER, 2);
        addParagraph(doc, DateUtil.getFormattedDate(fecha, "dd-MM-yyyy hh:mm:ss"), smalFont, Element.ALIGN_RIGHT, 30);

        // Tabla de encabezado
        PdfPTable headerTable = createHeaderTable(curso, font);
        doc.add(headerTable);

        // Tabla de notas
        PdfPTable nominaTable = createNominaTable(ws, font);
        doc.add(nominaTable);

        doc.close();

        CommonCertificacionUtil.writeFile(buffer, SystemParametersUtil.PATH_TEMP_FILES + name);
        buffer.close();

        LogUtil.setLog(rut, curso.getCodigo(" "));
        return CommonArchivoUtil.getFile(name, "tmp");
    }

    // Método auxiliar para agregar párrafos
    private void addParagraph(Document doc, String text, Font font, int alignment, float spacingAfter) throws Exception {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(alignment);
        paragraph.setSpacingAfter(spacingAfter);
        doc.add(paragraph);
    }

    // Crear tabla de encabezado
    private PdfPTable createHeaderTable(Curso curso, Font font) {
        PdfPTable headerTable = new PdfPTable(new float[]{2f, 8f});
        headerTable.setWidthPercentage(100);

        headerTable.addCell(createCell("NOTAS FINALES", font, Element.ALIGN_LEFT));
        headerTable.addCell(createCell(curso.getNombreFull(), font, Element.ALIGN_LEFT));
        headerTable.addCell(createCell("PROFESOR", font, Element.ALIGN_LEFT));
        headerTable.addCell(createCell(curso.getCurProfesores(), font, Element.ALIGN_LEFT));

        return headerTable;
    }

    // Crear tabla de notas
    private PdfPTable createNominaTable(WorkSession ws, Font font) {
        Color grayColor = new Color(230, 230, 230);
        PdfPTable nominaTable = new PdfPTable(new float[]{2f, 4f, 10f, 10f, 10f, 3f});
        nominaTable.setWidthPercentage(100);
        DecimalFormat df = new DecimalFormat("#0.0");

        // Cabecera de la tabla de notas
        nominaTable.addCell(createCell("", font, Element.ALIGN_LEFT));
        nominaTable.addCell(createHeaderCell("RUT", grayColor, font, Element.ALIGN_CENTER));
        nominaTable.addCell(createHeaderCell("ALUMNO", grayColor, font, Element.ALIGN_CENTER, 3));
        nominaTable.addCell(createHeaderCell("NOTA", grayColor, font, Element.ALIGN_CENTER));

        AtomicInteger row = new AtomicInteger(1);

        // Filas de la tabla de notas
        ws.getNominaActa().forEach(acta -> {
            nominaTable.addCell(createCell(row.getAndIncrement(), font, Element.ALIGN_RIGHT));
            nominaTable.addCell(createCell(acta.getAluRut() + "-" + acta.getAluDv(), font, Element.ALIGN_RIGHT));
            nominaTable.addCell(createCell(acta.getAluPaterno(), font, Element.ALIGN_LEFT, 8, true));
            nominaTable.addCell(createCell(acta.getAluMaterno()==null?"":acta.getAluMaterno(), font, Element.ALIGN_LEFT));
            nominaTable.addCell(createCell(acta.getAluNombre(), font, Element.ALIGN_LEFT));
            nominaTable.addCell(createCell(df.format(acta.getAcanFinal()), font, Element.ALIGN_RIGHT));
        });

        return nominaTable;
    }

    // Crear celda de contenido
    private PdfPCell createCell(Object content, Font font, int alignment) {
        return createCell(content, font, alignment, 0, false);
    }

    // Crear celda con padding y no wrap
    private PdfPCell createCell(Object content, Font font, int alignment, int paddingLeft, boolean noWrap) {
        PdfPCell cell = new PdfPCell(new Phrase(content.toString(), font));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingLeft(paddingLeft);
        cell.setNoWrap(noWrap);
        return cell;
    }

    // Crear celda de encabezado
    private PdfPCell createHeaderCell(String content, Color backgroundColor, Font font, int alignment) {
        return createHeaderCell(content, backgroundColor, font, alignment, 1);
    }

    // Crear celda de encabezado con colspan
    private PdfPCell createHeaderCell(String content, Color backgroundColor, Font font, int alignment, int colspan) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBackgroundColor(backgroundColor);
        cell.setColspan(colspan);
        return cell;
    }
}