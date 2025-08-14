/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.encuesta;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;
import domain.model.ComentarioEncuestaDocente;
import domain.model.Curso;
import domain.model.Profesor;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.AppStaticsUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonEncuestaUtil;
import java.util.function.Function;

public class CommonEncuestaPrintService {

    Font titleFont = PdfUtil.getFont("times", 14.0f, Font.BOLD);
    Font font = PdfUtil.getFont("times", 9.0f, Font.NORMAL);
    Font fontSub = PdfUtil.getFont("times", 9.0f, Font.UNDERLINE);
    Font smalFont = PdfUtil.getFont("times", 7.0f, Font.NORMAL);
    Color grayColor = new Color(230, 230, 230);

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        String name = "encuesta_" + ws.getCurso().getCodigo("_") + ".pdf";
        
        return new ActionInputStreamUtil(name, AppStaticsUtil.PDF_MIME, getInput(genericSession, ws));
    }

    private InputStream getInput(GenericSession genericSession, WorkSession ws) throws Exception {
        Curso curso = ws.getCurso();
        List<Profesor> profList = ContextUtil.getDAO().getProfesorRepository("CM").getProfesores(curso.getId());

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        //Document doc = new Document();
        Document doc = new Document(PageSize.LETTER, 50, 50, 20, 50);
        PdfWriter.getInstance(doc, buffer);
        doc.open();

        if ("PR".equals(genericSession.getUserType())) {
            addPdf(doc, ws, genericSession.getProfesorSession().getProfesor(), curso);
        } else {
            for (Profesor prof : profList) {
                addPdf(doc, ws, prof, curso);
            }
        }
        
        doc.close();
        return new ByteArrayInputStream(buffer.toByteArray());
    }

    private void addPdf(Document doc, WorkSession ws, Profesor prof, Curso curso) throws Exception {
        Date fecha = getSysdate();

        doc.addTitle("Encuesta Docente");
        doc.addAuthor("USACH");
        doc.addSubject(curso.getNombreFull());
        doc.addCreator("Intranet: " + fecha);
        doc.addCreationDate();
        doc.open();

        String imagePath = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH3);
        Image logo = Image.getInstance(imagePath);
        logo.scaleToFit(70, 70);
        logo.setAbsolutePosition(50, 700);
        doc.add(logo);

        Paragraph titleParagraph = new Paragraph("UNIVERSIDAD DE SANTIAGO DE CHILE", titleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        titleParagraph.setSpacingAfter(2); // Espacio entre párrafos
        doc.add(titleParagraph);

        Paragraph facultyParagraph = new Paragraph(ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getNombreFacultadxAsign(curso.getId().getCurAsign()), titleFont);
        facultyParagraph.setAlignment(Element.ALIGN_CENTER);
        facultyParagraph.setSpacingAfter(2); // Espacio entre párrafos
        doc.add(facultyParagraph);

        Paragraph fechaParagraph = new Paragraph(DateUtil.getFormattedDate(fecha, "dd-MM-yyyy hh:mm:ss"), smalFont);
        fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
        fechaParagraph.setSpacingAfter(30);
        doc.add(fechaParagraph);

        PdfPTable headerTable = new PdfPTable(new float[]{2f, 8f});
        headerTable.setWidthPercentage(100);

        PdfPCell cell1 = new PdfPCell(new Phrase("ENCUESTA", font));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPadding(0);
        headerTable.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase(curso.getNombreFull(), font));
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setPadding(0);
        headerTable.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Phrase("PROFESOR", font));
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setBorder(PdfPCell.NO_BORDER);
        cell3.setPadding(0);
        headerTable.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Phrase(prof.getNombre(), font));
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell4.setBorder(PdfPCell.NO_BORDER);
        cell4.setPadding(0);
        headerTable.addCell(cell4);

        doc.add(headerTable);
        doc.add(new Paragraph("\n\n"));

        PdfPTable encTable = new PdfPTable(new float[]{50f, 4f, 4f, 4f, 4f});
        encTable.setWidthPercentage(100);
        //DecimalFormat df = new DecimalFormat("#0.0");

        encTable.addCell(newCellHeader("PREGUNTA"));
        encTable.addCell(newCellHeader("PROM"));
        encTable.addCell(newCellHeader("MAX"));
        encTable.addCell(newCellHeader("MIN"));
        encTable.addCell(newCellHeader("DESV"));

        ws.getRespEncta().forEach(resp -> {
            encTable.addCell(newCell(resp.getPregDes(), Element.ALIGN_LEFT));
            encTable.addCell(newCell(String.valueOf(resp.getProm()), Element.ALIGN_CENTER));
            encTable.addCell(newCell(String.valueOf(resp.getMaximo()), Element.ALIGN_CENTER));
            encTable.addCell(newCell(String.valueOf(resp.getMinimo()), Element.ALIGN_CENTER));
            encTable.addCell(newCell(String.valueOf(resp.getDesv()), Element.ALIGN_CENTER));

            PdfPCell emptyCell = new PdfPCell();
            emptyCell.setColspan(5);
            emptyCell.setFixedHeight(2);
            emptyCell.setBorder(PdfPCell.NO_BORDER);
            encTable.addCell(emptyCell);
        });

        doc.add(encTable);
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("#ALUMNOS RESPONDIERON: " + ws.getRespEncta().get(0).getNumResp(), font));
        doc.add(new Paragraph("PROMEDIO CURSO: " + ws.getRespEncta().get(0).getCedPromCur(), font));
        doc.add(new Paragraph("PROMEDIO AREA: " + ws.getRespEncta().get(0).getCedPromArea(), font));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));

        // Agregar comentarios positivos y mejoras        
        addComments(doc, "COMENTARIOS POSITIVOS",
                CommonEncuestaUtil.getComentarios(ws.getComentarioEncuestaDocenteList(),
                        ComentarioEncuestaDocente::getCedComen1,
                        ComentarioEncuestaDocente::setCedComen1),
                ComentarioEncuestaDocente::getCedComen1);

        addComments(doc, "COMENTARIOS MEJORAS",
                CommonEncuestaUtil.getComentarios(ws.getComentarioEncuestaDocenteList(),
                        ComentarioEncuestaDocente::getCedComen2,
                        ComentarioEncuestaDocente::setCedComen2),
                ComentarioEncuestaDocente::getCedComen2);
    }

    private void addComments(Document doc, String title, List<ComentarioEncuestaDocente> comments,
            Function<ComentarioEncuestaDocente, String> getter) throws DocumentException {
        doc.add(new Paragraph(title, fontSub));
        comments.forEach(comentario -> doc.add(new Paragraph(getter.apply(comentario), smalFont)));
        doc.add(new Paragraph("\n"));
    }

    // Métodos getPositivos y getMejoras como antes
    private PdfPCell newCellHeader(String parrafo) {
        PdfPCell cell = new PdfPCell(new Paragraph(parrafo, smalFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(grayColor);
        cell.setBorder(PdfPCell.NO_BORDER);

        return cell;
    }

    private PdfPCell newCell(String parrafo, Integer align) {
        PdfPCell cell = new PdfPCell(new Paragraph(parrafo, smalFont));
        cell.setHorizontalAlignment(align);
        cell.setBorder(PdfPCell.NO_BORDER);

        return cell;
    }
}
