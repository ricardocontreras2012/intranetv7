/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.solicitud.expediente.alumno;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.AluCar;
import domain.model.Alumno;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import static infrastructure.util.FormatUtil.sanitizeFileName;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TITULACION;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonSequenceUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Alvaro
 */
public class AlumnoGeneraSolicitudGeneroService {

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        InputStream input;
        String name;
        String description;
        
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aca = ws.getAluCar();
        String datoAlu = aca.getId().getAcaRut()+"-"+aca.getId().getAcaCodCar()+"-"+aca.getId().getAcaAgnoIng()+"-"+aca.getId().getAcaSemIng();
        String logro = ws.getExpedienteLogro().getPlanLogro().getLogro().getLogrDes();
        Integer folio = CommonSequenceUtil.getDocumentSeq();

        name = sanitizeFileName(datoAlu + "-" + logro + "-Solicitud-de-Genero-Template-" + folio +".pdf");
        description = FormatUtil.getMimeType(name);
        input = getInput(genericSession, key, name);

        return new ActionInputStreamUtil(name, description, input);
    }

    private InputStream getInput(GenericSession genericSession,
            String key, String name)
            throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aca = ws.getAluCar();
        Alumno alumno = aca.getAlumno();
        
        float margin = 28.35f * 2;
        Document document = new Document(PageSize.LETTER, margin, margin, margin / 2, margin / 2);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, buffer);

        // Abrir el documento
        document.open();

        String path = getServletContext().getRealPath("/fonts/local/");
        register(path + "OpenSans-Regular.ttf", "open_font");
        register(path + "OpenSans_Condensed-Medium.ttf", "open_condensed_font");
        register(path + "OpenSans-SemiBold.ttf", "open_bold_font");

        Font fontRegular = getFont("open_font", 12.0f, NORMAL);
        Font fontBold = getFont("open_bold_font", 12.0f, com.lowagie.text.Font.BOLD);
        Font fontCondensed = getFont("open_condensed_font", 12.0f, NORMAL);

        // Establecer logotipo
        Image logo = Image.getInstance(ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH));

        // Añadir texto
        Paragraph paragraph = new Paragraph("REPUBLICA DE CHILE\nUNIVERSIDAD DE SANTIAGO DE CHILE\nFACULTAD DE ADMINISTRACIÓN Y ECONOMÍA\nREGISTRO CURRICULAR", fontCondensed);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        document.add(new Paragraph("\n\n\n"));

        // Títulos
        Paragraph title1 = new Paragraph("SOLICITUD PARA INDICAR GÉNERO EN EL "+ws.getExpedienteLogro().getPlanLogro().getLogro().getTlogro().getTloDes().toUpperCase(), fontBold);
        title1.setAlignment(Element.ALIGN_CENTER);
        document.add(title1);

        document.add(new Paragraph("\n\n"));

        String alumnoDoc = alumno.getAluRut() + "-" + alumno.getAluDv();
        String alumnoNombre = alumno.getAluPaterno() + " " + alumno.getAluMaterno() + ", " + alumno.getAluNombre();

        String texto = "De acuerdo con la Resolución N° 5203, del 27.05.2014, que incorpora la variable género en "
                + "la denominación de los Grados Académicos y Títulos Profesionales que otorga la "
                + "Universidad, es necesario precisar la denominación que desea en su "+ws.getExpedienteLogro().getPlanLogro().getLogro().getTlogro().getTloDes().toLowerCase()+". \n\n"
                + "Para dar cumplimiento a esta Resolución, usted debe marcar X en una de las siguientes "
                + "posibilidades para su "+ws.getExpedienteLogro().getPlanLogro().getLogro().getTlogro().getTloDes().toLowerCase()+": \n\n";
        
        Paragraph parrafo = new Paragraph(texto, fontRegular);
        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(parrafo);

        //cuadrado
        PdfPTable cuadro = new PdfPTable(1);
        cuadro.setTotalWidth(14f); // Ancho físico de 1 cm
        cuadro.setLockedWidth(true);  // Necesario para fijar ancho exacto
        PdfPCell cuadradoCell = new PdfPCell();
        cuadradoCell.setFixedHeight(14f); // Alto de 1 cm
        cuadradoCell.setPadding(0);
        cuadradoCell.setBorder(Rectangle.BOX);
        cuadro.addCell(cuadradoCell);
        
        //celda cuadro
        
        PdfPCell celdaCuadro = new PdfPCell(cuadro);
        celdaCuadro.setPadding(2f);
        celdaCuadro.setBorder(Rectangle.NO_BORDER);

        //tabla       
        PdfPTable table = new PdfPTable(3);
        float[] columnWidths = {25f, 8f, 67f};
        table.setWidths(columnWidths);
        table.setWidthPercentage(100);
        
        PdfPCell cel01 = new PdfPCell(new Phrase("Género Masculino"));
        cel01.setBorder(Rectangle.NO_BORDER);
        table.addCell(cel01);
        
        table.addCell(celdaCuadro);
        PdfPCell celda2 = new PdfPCell(new Phrase(ws.getExpedienteLogro().getPlanLogro().getPlalLin1M()+" "+ws.getExpedienteLogro().getPlanLogro().getPlalLin2M()));
        celda2.setBorder(Rectangle.NO_BORDER);
        table.addCell(celda2);
        
        PdfPCell cel02 = new PdfPCell(new Phrase("Género Femenino"));
        cel02.setBorder(Rectangle.NO_BORDER);
        table.addCell(cel02);
        
        table.addCell(celdaCuadro);
        PdfPCell celda4 = new PdfPCell(new Phrase(ws.getExpedienteLogro().getPlanLogro().getPlalLin1F()+" "+ws.getExpedienteLogro().getPlanLogro().getPlalLin2F()));
        celda4.setBorder(Rectangle.NO_BORDER);
        table.addCell(celda4);
        
        document.add(table);

        texto = "\nUna vez marcada la preferencia, la denominación elegida se registrará en toda documentación " +
        "que usted solicite a futuro.\n\n";
        parrafo = new Paragraph(texto, fontRegular);
        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(parrafo);
        
        document.add(new Paragraph("Esta solicitud debe ser incluida en el expediente de "+ws.getExpedienteLogro().getPlanLogro().getLogro().getTlogro().getTloDes().toLowerCase()+".\n\n", fontRegular));
        
        document.add(new Paragraph("Datos del solicitante:\n\n", fontRegular));
        
        PdfPTable tableDatos = new PdfPTable(2);
        float[] columnWidths2 = {20f, 80f};
        tableDatos.setWidths(columnWidths2);
        tableDatos.setWidthPercentage(100);
        float celHeight = 30f;
        
        PdfPCell cel1 = new PdfPCell(new Phrase("Nombre"));
        cel1.setBorder(Rectangle.NO_BORDER);
        cel1.setFixedHeight(celHeight);
        cel1.setVerticalAlignment(Element.ALIGN_BOTTOM);
        tableDatos.addCell(cel1);
        
        PdfPCell cel2 = new PdfPCell(new Phrase(alumnoNombre));
        cel2.setBorder(Rectangle.BOTTOM);
        cel2.setFixedHeight(celHeight);
        cel2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableDatos.addCell(cel2);
        
        PdfPCell cel3 = new PdfPCell(new Phrase("RUT"));
        cel3.setBorder(Rectangle.NO_BORDER);
        cel3.setFixedHeight(celHeight);
        cel3.setVerticalAlignment(Element.ALIGN_BOTTOM);
        tableDatos.addCell(cel3);
        
        PdfPCell cel4 = new PdfPCell(new Phrase(alumnoDoc));
        cel4.setBorder(Rectangle.BOTTOM);
        cel4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableDatos.addCell(cel4);
        
        PdfPCell cel5 = new PdfPCell(new Phrase("Fecha"));
        cel5.setBorder(Rectangle.NO_BORDER);
        cel5.setFixedHeight(celHeight);
        cel5.setVerticalAlignment(Element.ALIGN_BOTTOM);
        tableDatos.addCell(cel5);
        
        PdfPCell cel6 = new PdfPCell(new Phrase(DateUtil.getFechaEnPalabras(getSysdate())));
        cel6.setBorder(Rectangle.BOTTOM);
        cel6.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableDatos.addCell(cel6);
        
        PdfPCell cel7 = new PdfPCell(new Phrase("Firma"));
        cel7.setBorder(Rectangle.NO_BORDER);
        cel7.setFixedHeight(50f);
        cel7.setVerticalAlignment(Element.ALIGN_BOTTOM);
        tableDatos.addCell(cel7);
        
        PdfPCell cel8 = new PdfPCell(new Phrase(" "));
        cel8.setBorder(Rectangle.BOTTOM);
        tableDatos.addCell(cel8);
        
        document.add(tableDatos);

        // Logo arriba a la izquierda
        float pageHeight = document.getPageSize().getHeight();

        logo.scaleToFit(100, 100);
        logo.scalePercent(54f);
        float logoHeight = logo.getScaledHeight();
        float logoPosX = margin; // margen izquierdo
        float logoPosY = pageHeight - margin / 2 - logoHeight - 10; // arriba
        logo.setAbsolutePosition(logoPosX, logoPosY);
        document.add(logo);


        // Cerrar documento
        document.close();

        CommonCertificacionUtil.writeFile(buffer, PATH_TITULACION + name);

        LogUtil.setLog(genericSession.getRut(), aca.getId().getAcaRut());
        return CommonArchivoUtil.getFile(name, "tit");
    }
}
