/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.AluCar;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import domain.model.LaborRealizada;
import domain.model.Practica;
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
public class AlumnoPracticaPresentacionPrintService {

    public ActionInputStreamUtil service(GenericSession genericSession,
            String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aca = ws.getAluCar();
        Integer rut = aca.getId().getAcaRut();
        String name = "carta_presentacion_" + rut + "_"+ ws.getSolicitud().getSolFolio()+".pdf";
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
        LaborRealizada autoridad = ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findLaborRealizadaMencion(aca.getPlan().getMencion().getId().getMenCodCar(), aca.getAcaCodMen(), "CP");

        Document doc = new Document(PageSize.LETTER, 100, 100, 20, 50);
        doc.addTitle("Carta Presentación");
        doc.addAuthor("USACH");
        doc.addSubject(aca.getAlumno().getNombre());
        doc.addCreator("Intranet: " + fecha);
        doc.addCreationDate();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, buffer);

        doc.open();

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

        String departamento = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getNombreDepartamento(
                aca.getPlan().getMencion().getId().getMenCodCar(),
                aca.getAcaCodMen()).toUpperCase(infrastructure.util.ContextUtil.getLocale());

        Paragraph deptoParagraph = new Paragraph(departamento, titleFont);
        deptoParagraph.setAlignment(Element.ALIGN_CENTER);
        deptoParagraph.setSpacingAfter(20);
        doc.add(deptoParagraph);

        Paragraph folioParagraph = new Paragraph(String.valueOf(ws.getSolicitud().getSolFolio()), smalFont);
        folioParagraph.setAlignment(Element.ALIGN_RIGHT);
        folioParagraph.setSpacingAfter(10);
        doc.add(folioParagraph);

        Paragraph fechaParagraph = new Paragraph(DateUtil.getFechaCiudad(fecha), font);
        fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
        fechaParagraph.setSpacingAfter(10);
        doc.add(fechaParagraph);

        String parrafo1 = "Me dirijo a usted con el propósito de presentar "
                + ("2".equals(aca.getAlumno().getAluSexo())
                ? "al Sr. "
                : "a la Srta. ") + aca.getAlumno().getNombreStd() + ", Cédula Nacional de Identidad N°. "
                + aca.getAlumno().getAluRut() + '-' + aca.getAlumno().getAluDv()
                + ',' + ("2".equals(aca.getAlumno().getAluSexo())
                ? " alumno"
                : " alumna") + " regular de la carrera de " + aca.getNombreCarrera()
                + ", quien está en condiciones de realizar su "
                + practica.getAsignatura().getAsiNom()
                + " con una duración de "
                + ContextUtil.getDAO().getPracticaPersistence(ActionUtil.getDBUser()).getHoras(practica.getAsignatura().getAsiCod())
                + " horas efectivas de trabajo y sujeto a las normas de la institución acogedora.";

        Paragraph p1 = new Paragraph(parrafo1, font);
        p1.setAlignment(Element.ALIGN_JUSTIFIED);
        p1.setSpacingAfter(10);
        doc.add(p1);

        String instituciones = aca.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 33 ? "pertenecientes al Estado." : "públicas y/o privadas.";
        String parrafo2 = "Esta actividad forma parte del acercamiento a la realidad organizacional, que creemos fundamental en el proceso de formación de los alumnos, para conocer en forma directa y la dinámica de las organizaciones. Sin duda que esto será posible en la medida que logremos la ayuda y cooperación de la instituciones " + instituciones;
        Paragraph p2 = new Paragraph(parrafo2, font);
        p2.setAlignment(Element.ALIGN_JUSTIFIED);
        p2.setSpacingAfter(10);
        doc.add(p2);

        String parrafo3 = "Dejo constancia que los alumnos en práctica están amparados por las disposiciones  contenidas en la Ley N°. 16.744, que se refiere al Seguro de Accidentes del Trabajo y Enfermedades Profesionales, que en su artículo N°. 3 lo hace extensivo a los alumnos que se encuentran en las condiciones antes dichas. La reglamentación pertinente consta en el Decreto Supremo N°: 313 del Ministerio del Trabajo y Previsión Social, publicado en el Diario Oficial del 12 de mayo de 1973.";
        Paragraph p3 = new Paragraph(parrafo3, font);
        p3.setAlignment(Element.ALIGN_JUSTIFIED);
        p3.setSpacingAfter(10);
        doc.add(p3);

        String parrafo4 = "En el marco de esta actividad el estudiante deberá realizar funciones propias de su carrera, supervisadas por quién su institución designe, quién posteriormente deberá evaluar el desempeño de nuestro estudiante, para lo cual se le enviarán las pautas de evaluación una vez haya concluido la duración de la práctica.\n";
        Paragraph p4 = new Paragraph(parrafo4, font);
        p4.setAlignment(Element.ALIGN_JUSTIFIED);
        p4.setSpacingAfter(10);
        doc.add(p4);

        String parrafo5 = "Por lo anterior, agradeceré otorgar las facilidades para que el estudiante cumpla con éxito la finalidad de esta práctica, que se espera sea de mutuo beneficio..\n";
        Paragraph p5 = new Paragraph(parrafo5, font);
        p5.setAlignment(Element.ALIGN_JUSTIFIED);
        p5.setSpacingAfter(50);
        doc.add(p5);

        PdfPTable footerTable = new PdfPTable(new float[]{1, 1});
        footerTable.setWidthPercentage(100);

        PdfPCell cell1 = new PdfPCell(new Phrase("Saluda atentamente a Ud.,", font));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPadding(0);
        footerTable.addCell(cell1);

        PdfPTable innerTable = new PdfPTable(1);
        innerTable.setWidthPercentage(100);
        
        String archivo;

        switch (aca.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip()) {
            case 16:
                if (aca.getAcaCodMen() == 2) {
                    archivo = "ICE";
                } else {
                    archivo = "ICA";
                }
                break;
            case 35:
                archivo = "CP";
                break;

            case 33:
                archivo = "AP";
                break;
            default:
                archivo = "";
        }

        Image img = Image.getInstance( ServletActionContext.getServletContext().getRealPath("/images/local/practicas/firma-timbre"
                        + archivo + ".jpg")); 
        
        img.scaleToFit(100, 100); // Escalar la imagen si es necesario
        PdfPCell imageCell = new PdfPCell(img);
        imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        imageCell.setBorder(PdfPCell.NO_BORDER);
        innerTable.addCell(imageCell);

        PdfPCell nameCell = new PdfPCell(new Phrase(autoridad.getFuncionario().getTraNombreSimple(), font));
        nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        nameCell.setBorder(PdfPCell.NO_BORDER);
        innerTable.addCell(nameCell);

        PdfPCell positionCell = new PdfPCell(new Phrase(autoridad.getLabel(), font));
        positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        positionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        positionCell.setBorder(PdfPCell.NO_BORDER);
        innerTable.addCell(positionCell);

        PdfPCell cell2 = new PdfPCell(innerTable);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setBorder(PdfPCell.NO_BORDER);
        footerTable.addCell(cell2);

        doc.add(footerTable);

        doc.close();

        CommonCertificacionUtil.writeFile(buffer, SystemParametersUtil.PATH_ATTACH_SOLICITUDES + name);
        buffer.close();

        LogUtil.setLog(aca.getId().getAcaRut(),practica.getSolicitud().getSolFolio() );
        return CommonArchivoUtil.getFile(name, "sol");
    }
}
