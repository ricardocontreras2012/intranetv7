/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import com.lowagie.text.pdf.draw.LineSeparator;
import domain.model.AluCar;
import domain.model.Practica;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
import infrastructure.util.common.CommonHorarioUtil;

/**
 *
 * @author Usach
 */
public class AlumnoPracticaAutorizacionPrintService {

    public ActionInputStreamUtil service(GenericSession genericSession,
            String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aca = ws.getAluCar();
        Integer rut = aca.getId().getAcaRut();
        String name = "carta_autorizacion_" + rut + "_" + ws.getSolicitud().getSolFolio() + ".pdf";
        String description = FormatUtil.getMimeType(name);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(aca.getParametros().getAgnoIns(), aca.getParametros().getSemIns()));

        return new ActionInputStreamUtil(name, description, getInput(ws, name));
    }

    public InputStream getInput(WorkSession ws, String name) throws Exception {
        Font titleFont = PdfUtil.getFont("times", 14.0f, Font.BOLD);
        Font font = PdfUtil.getFont("times", 10.0f, Font.NORMAL);
        Font smalFont = PdfUtil.getFont("times", 7.0f, Font.NORMAL);

        AluCar aca = ws.getAluCar();
        Practica practica = ws.getPractica();

        Date fecha = getSysdate();
        
        Document doc = new Document(PageSize.LETTER, 100, 100, 20, 50);
        doc.addTitle("Carta Autorización");
        doc.addAuthor("USACH");
        doc.addSubject(aca.getAlumno().getNombre());
        doc.addCreator("Intranet: " + fecha);
        doc.addCreationDate();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = getInstance(doc, buffer);

        doc.open();

        Image backgroundImage = Image.getInstance(ServletActionContext.getServletContext().getRealPath("/images/local/practicas/fondo.jpg"));
        backgroundImage.scaleToFit(doc.getPageSize().getWidth(), doc.getPageSize().getHeight());
        backgroundImage.setAbsolutePosition(0, 0); // Establece la posición en la esquina inferior izquierda
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.addImage(backgroundImage);

        String imagePath = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH1);
        Image logo = Image.getInstance(imagePath);
        logo.scaleToFit(70, 70);
        logo.setAbsolutePosition(50, 700);
        doc.add(logo);

        Paragraph titleParagraph = new Paragraph("UNIVERSIDAD DE SANTIAGO DE CHILE", titleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        titleParagraph.setSpacingAfter(2); // Espacio entre párrafos
        doc.add(titleParagraph);

        Paragraph facultyParagraph = new Paragraph("FACULTAD DE ADMINISTRACIÓN Y ECONOMÍA", titleFont);
        facultyParagraph.setAlignment(Element.ALIGN_CENTER);
        facultyParagraph.setSpacingAfter(2); // Espacio entre párrafos
        doc.add(facultyParagraph);

        String departamento = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getNombreDepartamento(
                aca.getPlan().getMencion().getId().getMenCodCar(),
                aca.getAcaCodMen()).toUpperCase(infrastructure.util.ContextUtil.getLocale());

        Paragraph deptoParagraph = new Paragraph(departamento, titleFont);
        deptoParagraph.setAlignment(Element.ALIGN_CENTER);
        deptoParagraph.setSpacingAfter(30);
        doc.add(deptoParagraph);

        String autorizacion
                = "AUTORIZACIÓN DE " + practica.getAsignatura().getAsiNom()
                        .toUpperCase(ContextUtil.getLocale());

        Paragraph autParagraph = new Paragraph(autorizacion, titleFont);
        autParagraph.setAlignment(Element.ALIGN_CENTER);
        autParagraph.setSpacingAfter(2);
        doc.add(autParagraph);

        Paragraph folioParagraph = new Paragraph(String.valueOf(ws.getSolicitud().getSolFolio()), smalFont);
        folioParagraph.setAlignment(Element.ALIGN_RIGHT);
        folioParagraph.setSpacingAfter(10);
        doc.add(folioParagraph);

        Paragraph fechaParagraph = new Paragraph(DateUtil.getFechaCiudad(fecha), font);
        fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
        fechaParagraph.setSpacingAfter(10);
        doc.add(fechaParagraph);

        String parrafo1 = aca.getAlumno().getNombreStd() + ", Cédula Nacional de Identidad N°. "
                + aca.getAlumno().getAluRut() + '-' + aca.getAlumno().getAluDv()
                + ", de la carrera de " + aca.getNombreCarrera()
                + ", con teléfono 7180000, solicita realizar su "
                + practica.getAsignatura().getAsiNom()
                + " con una duración de " + ContextUtil.getDAO().getPracticaRepository(ActionUtil.getDBUser()).getHoras(practica.getAsignatura().getAsiCod())
                + " horas en: " + practica.getEmpleador().getEmpNombre() + " con dirección en "
                + practica.getPraDireccion() + ", comuna de "
                + FormatUtil.initCapital(practica.getComuna().getComNom()) + ",Región "
                + FormatUtil.initCapital(practica.getComuna().getRegion().getRegNom())
                + ", teléfono(s): " + practica.getPraTelefonoEmp() + '.';

        Paragraph p1 = new Paragraph(parrafo1, font);
        p1.setAlignment(Element.ALIGN_JUSTIFIED);
        p1.setSpacingAfter(20);
        doc.add(p1);

        PdfPTable firmaAlu = new PdfPTable(1);
        firmaAlu.setWidthPercentage(33); // 33% del ancho de la página
        firmaAlu.setHorizontalAlignment(Element.ALIGN_RIGHT); // Alinear a la derecha
        PdfPCell lineCell = new PdfPCell();
        lineCell.setBorder(PdfPCell.NO_BORDER);
        lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        lineCell.setPaddingBottom(5); // Espacio debajo de la línea

        lineCell.addElement(new Chunk(new LineSeparator(1f, 100f, Color.BLACK, Element.ALIGN_CENTER, -1))); 
        firmaAlu.addCell(lineCell);

        PdfPCell textCell = new PdfPCell(new Phrase("Firma Alumno", font));
        textCell.setBorder(PdfPCell.NO_BORDER);
        textCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        textCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        firmaAlu.addCell(textCell);
        doc.add(firmaAlu);
        
        doc.add(new Chunk("\n"));

        String parrafo2 = "Autorizo a " + aca.getAlumno().getNombreStd() + ", "
                + ("2".equals(aca.getAlumno().getAluSexo())
                ? "alumno"
                : "alumna") + " en práctica, para  desempeñarse en "
                + practica.getEmpleador().getEmpNombre()
                + " y realizará las siguientes funciones: " + practica.getPraLabor()
                + ", conforme a las exigencias curriculares de la Universidad.";
        Paragraph p2 = new Paragraph(parrafo2, font);
        p2.setAlignment(Element.ALIGN_JUSTIFIED);
        p2.setSpacingAfter(10);
        doc.add(p2);

        String parrafo3 = "La fecha de inicio de la práctica será a contar de "
                + ContextUtil.getDAO().getScalarRepository(
                        ActionUtil.getDBUser()).getFechaEnPalabras(
                        DateUtil.getFormattedDate(practica.getPraFechaInicio(), SystemParametersUtil.DATE_FORMAT)) + " y "
                + "finalizará el "
                + ContextUtil.getDAO().getScalarRepository(
                        ActionUtil.getDBUser()).getFechaEnPalabras(
                        DateUtil.getFormattedDate(practica.getPraFechaTermino(), SystemParametersUtil.DATE_FORMAT)) + '.';

        Paragraph p3 = new Paragraph(parrafo3, font);
        p3.setAlignment(Element.ALIGN_JUSTIFIED);
        p3.setSpacingAfter(10);
        doc.add(p3);

        String parrafo4 = practica.getAutoriza().getPerFull().toUpperCase(ContextUtil.getLocale()) + ", "
                + practica.getPraCalidadAut() + ", en representación de "
                + practica.getEmpleador().getEmpNombre() + ", firmo este documento.";
        Paragraph p4 = new Paragraph(parrafo4, font);
        p4.setAlignment(Element.ALIGN_JUSTIFIED);
        p4.setSpacingAfter(10);
        doc.add(p4);

        String parrafo5 = "Saluda atentamente a Ud.,";
        Paragraph p5 = new Paragraph(parrafo5, font);
        p5.setAlignment(Element.ALIGN_LEFT);
        p5.setSpacingAfter(50);
        doc.add(p5);
        
        PdfPTable firmaEmp = new PdfPTable(1);
        firmaEmp.setWidthPercentage(33); // 33% del ancho de la página
        firmaEmp.setHorizontalAlignment(Element.ALIGN_RIGHT); // Alinear a la derecha
        lineCell = new PdfPCell();
        lineCell.setBorder(PdfPCell.NO_BORDER);
        lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        lineCell.setPaddingBottom(5); // Espacio debajo de la línea

        lineCell.addElement(new Chunk(new LineSeparator(1f, 100f, Color.BLACK, Element.ALIGN_CENTER, -1))); 
        firmaEmp.addCell(lineCell);

        textCell = new PdfPCell(new Phrase("Firma y Timbre", font));
        textCell.setBorder(PdfPCell.NO_BORDER);
        textCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        textCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        firmaEmp.addCell(textCell);
        doc.add(firmaEmp);
       
        doc.close();

        CommonCertificacionUtil.writeFile(buffer, SystemParametersUtil.PATH_ATTACH_SOLICITUDES + name);
        buffer.close();

        LogUtil.setLog(aca.getId().getAcaRut(),practica.getSolicitud().getSolFolio() );
        return CommonArchivoUtil.getFile(name, "sol");
    }
}
