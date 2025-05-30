/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

/**
 *
 * @author Ricardo
 */
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.ExpedienteLogro;
import domain.model.LaborRealizada;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TITULACION;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import session.GenericSession;
import session.WorkSession;

public class yyyS {

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        InputStream input;
        String name;
        String description;

        WorkSession ws = genericSession.getWorkSession(key);
        ExpedienteLogro expl = ws.getExpedienteLogro();

        name = "CARATULA_nnn_zzz" + ".pdf";
        description = FormatUtil.getMimeType(name);
        input = getInput(genericSession, key, name, expl);

        return new ActionInputStreamUtil(name, description, input);
    }

    private static InputStream getInput(GenericSession genericSession,
            String key, String name, ExpedienteLogro expl)
            throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aca = ws.getAluCar();
        Alumno alumno = aca.getAlumno();

        float margin = 28.35f * 2; // 1 cm en puntos
        Document document = new Document(PageSize.LETTER, margin, margin, margin / 2, margin / 2);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, buffer);
        document.open();

        PdfContentByte cb = writer.getDirectContent();
        float pageWidth = document.getPageSize().getWidth();
        float colWidth = (pageWidth - 2 * margin) / 12f;

        // Logo
        Image logo = Image.getInstance(ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH));
        Font fontCondensed = new Font(Font.HELVETICA, 8, Font.NORMAL);
        PdfPTable headerTable = new PdfPTable(4);
        headerTable.setWidthPercentage(100);

        headerTable.setWidths(new float[]{2, 5, 3, 7.3f});

        // Celda 1: Logo
        logo.scaleToFit(100, 50);

        PdfPCell logoCell = new PdfPCell(logo, false);
        logoCell.setBorder(Rectangle.NO_BORDER);
        logoCell.setVerticalAlignment(Element.ALIGN_TOP);
        logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerTable.addCell(logoCell);

        // Celda 2: Textos principales centrados
        PdfPCell textosCell = new PdfPCell();
        textosCell.setBorder(Rectangle.NO_BORDER);
        textosCell.setVerticalAlignment(Element.ALIGN_TOP);
        textosCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Paragraph p1 = new Paragraph("UNIVERSIDAD DE SANTIAGO DE CHILE", fontCondensed);
        p1.setAlignment(Element.ALIGN_CENTER);
        Paragraph p2 = new Paragraph("REGISTRO ACADÉMICO", fontCondensed);
        p2.setAlignment(Element.ALIGN_CENTER);
        Paragraph p3 = new Paragraph("TÍTULOS Y GRADOS", fontCondensed);
        p3.setAlignment(Element.ALIGN_CENTER);

        textosCell.addElement(p1);
        textosCell.addElement(p2);
        textosCell.addElement(p3);

        headerTable.addCell(textosCell);

        // Calda 3: Espacio
        PdfPCell emptyCell = new PdfPCell(new Phrase(""));
        emptyCell.setBorder(PdfPCell.NO_BORDER);
        headerTable.addCell(emptyCell);

        // Celda 4: Campos con línea
        Font fontNormal = new Font(Font.HELVETICA, 11, Font.NORMAL);
        PdfPTable tablaCampos = new PdfPTable(1);
        tablaCampos.setWidthPercentage(100);
        String[] campos = {"ROL USACH N°", "APROBADO", "RESOLUCIÓN N°", "DEL"};
        /*for (String campo : campos) {
            PdfPCell cell = new PdfPCell(new Phrase(campo, fontNormal));
            cell.setBorder(Rectangle.NO_BORDER);
            tablaCampos.addCell(cell);
        }*/

        for (String campo : campos) {
            Phrase phrase = new Phrase();
            phrase.add(new Chunk(campo + " ", fontNormal));

            // Crear un Chunk con espacios y aplicar línea subrayada
            Chunk linea = new Chunk("               ", fontNormal);
            // Grosor de la línea 0.5f, posición -2f (ajustar si es necesario)
            linea.setUnderline(0.5f, -2f);

            phrase.add(linea);

            PdfPCell cell = new PdfPCell(phrase);
            cell.setBorder(Rectangle.NO_BORDER);
            tablaCampos.addCell(cell);
        }

        PdfPCell camposCell = new PdfPCell(tablaCampos);
        camposCell.setBorder(Rectangle.NO_BORDER);
        camposCell.setVerticalAlignment(Element.ALIGN_TOP);
        camposCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        headerTable.addCell(camposCell);

        document.add(headerTable);

        // Título central
        Font fontBold = new Font(Font.HELVETICA, 15, Font.BOLD);
        Paragraph titulo = new Paragraph("EXPEDIENTE DE " + expl.getPlanLogro().getLogro().getTlogro().getTloDes().toUpperCase(), fontBold);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        // Cuadro derecho
        float cuadroX = margin + colWidth * 7;
        float cuadroY = document.getPageSize().getHeight() - margin - 180;
        float cuadroWidth = colWidth * 5;
        float cuadroHeight = 90;
        cb.rectangle(cuadroX, cuadroY, cuadroWidth, cuadroHeight);
        cb.stroke();

        // Nombre del alumno
        Paragraph nombre = new Paragraph(alumno.getNombreStd(), fontBold);
        nombre.setSpacingBefore(140);
        nombre.setAlignment(Element.ALIGN_CENTER);
        document.add(nombre);

        // Línea bajo el nombre
        cb.moveTo(margin * 1.25f, cuadroY - 20);
        cb.lineTo(pageWidth - margin * 1.25f, cuadroY - 20);
        cb.stroke();

        // Subtítulo bajo nombre
        Font fontSmall = new Font(Font.HELVETICA, 9, Font.NORMAL);
        Paragraph subtituloNombre = new Paragraph("Apellidos y Nombres Completos", fontSmall);
        subtituloNombre.setAlignment(Element.ALIGN_CENTER);
        document.add(subtituloNombre);

        // Dirección
        Paragraph direccion = new Paragraph(alumno.getAluDirecAlu().toUpperCase(), fontNormal);
        direccion.setAlignment(Element.ALIGN_CENTER);
        document.add(direccion);

        // Línea bajo dirección
        cb.moveTo(margin * 1.25f, cuadroY - 40);
        cb.lineTo(pageWidth - margin * 1.25f, cuadroY - 40);
        cb.stroke();

        Paragraph subtituloDireccion = new Paragraph("Domicilio", fontSmall);
        subtituloDireccion.setAlignment(Element.ALIGN_CENTER);
        document.add(subtituloDireccion);

        // Firma interesado
        Paragraph firma = new Paragraph("FIRMA INTERESADO", fontSmall);
        firma.setAlignment(Element.ALIGN_RIGHT);
        firma.setIndentationRight(colWidth * 2);
        document.add(firma);

        cb.moveTo(margin + colWidth * 8, cuadroY - 10);
        cb.lineTo(margin + colWidth * 11, cuadroY - 10);
        cb.stroke();

        // Datos adicionales
        Paragraph rut = new Paragraph("CÉDULA DE IDENTIDAD: " + FormatUtil.getRutFormateado(alumno.getAluRut()), fontNormal);
        rut.setAlignment(Element.ALIGN_LEFT);
        document.add(rut);

        Paragraph telefono = new Paragraph("TELÉFONO: " + alumno.getAluFonoAlu(), fontNormal);
        telefono.setAlignment(Element.ALIGN_LEFT);
        document.add(telefono);

        Paragraph email = new Paragraph("E-MAIL: " + ((alumno.getAluEmail() == null || alumno.getAluEmail().isEmpty()) ? alumno.getAluEmailUsach() : alumno.getAluEmailUsach() + "," + alumno.getAluEmail()), fontNormal);
        email.setAlignment(Element.ALIGN_LEFT);
        document.add(email);

        Paragraph solicita = new Paragraph("SOLICITA: " + expl.getPlanLogro().getPlalNomLogro().toUpperCase(), fontNormal);
        solicita.setAlignment(Element.ALIGN_LEFT);
        document.add(solicita);

        Paragraph especialidad = new Paragraph("ESPECIALIDAD: " + (aca.getPlan().getMencion().getMenNom() == null ? "====================================" : aca.getPlan().getMencion().getMenNom().toUpperCase()), fontNormal);
        especialidad.setAlignment(Element.ALIGN_LEFT);
        document.add(especialidad);

        Paragraph facultad = new Paragraph(aca.getAluCarFunction().getNombreFacultad().toUpperCase(), fontNormal);
        facultad.setAlignment(Element.ALIGN_LEFT);
        document.add(facultad);

        // Cuadro para datos del alumno
        cb.rectangle(margin, cuadroY - 140, colWidth * 12, 120);
        cb.stroke();

        // USO EXCLUSIVO DE TÍTULOS Y GRADOS
        Paragraph usoExclusivo = new Paragraph("USO EXCLUSIVO DE TÍTULOS Y GRADOS", new Font(Font.HELVETICA, 10, Font.BOLD));
        usoExclusivo.setAlignment(Element.ALIGN_CENTER);
        document.add(usoExclusivo);

        // Tabla de aranceles e impuestos
        Paragraph aranceles = new Paragraph("ARANCELES E IMPUESTOS", fontBold);
        aranceles.setAlignment(Element.ALIGN_LEFT);
        document.add(aranceles);

        PdfPTable tablaAranceles = new PdfPTable(2);
        tablaAranceles.setWidthPercentage(60);
        tablaAranceles.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablaAranceles.addCell(new PdfPCell(new Phrase("Solicitud", fontNormal)));
        tablaAranceles.addCell(new PdfPCell(new Phrase("__________", fontNormal)));
        tablaAranceles.addCell(new PdfPCell(new Phrase("Certificado", fontNormal)));
        tablaAranceles.addCell(new PdfPCell(new Phrase("__________", fontNormal)));
        tablaAranceles.addCell(new PdfPCell(new Phrase("Diploma", fontNormal)));
        tablaAranceles.addCell(new PdfPCell(new Phrase("__________", fontNormal)));
        tablaAranceles.addCell(new PdfPCell(new Phrase("TOTAL", fontNormal)));
        tablaAranceles.addCell(new PdfPCell(new Phrase("__________", fontNormal)));
        document.add(tablaAranceles);

        // Observaciones
        Paragraph observaciones = new Paragraph("OBSERVACIONES", fontBold);
        observaciones.setSpacingBefore(10);
        document.add(observaciones);

        // Registros
        Paragraph registros = new Paragraph("REGISTROS", fontBold);
        registros.setAlignment(Element.ALIGN_RIGHT);
        registros.setSpacingBefore(10);
        document.add(registros);

        PdfPTable tablaRegistros = new PdfPTable(2);
        tablaRegistros.setWidthPercentage(80);
        tablaRegistros.setHorizontalAlignment(Element.ALIGN_RIGHT);
        String[] registrosArr = {
            "V°B° Legal", " ",
            "V°B° Administrativo", " ",
            "Ficha (Ingreso)", " ",
            "A V B° Académico", " ",
            "Enrolamiento", " ",
            "Certificación", " ",
            "Ficha (Registro)", " ",
            "Calígrafo", " ",
            "Envío o Firmas", " ",
            "Ficha (Kardex)", " "
        };
        for (int i = 0; i < registrosArr.length; i += 2) {
            tablaRegistros.addCell(new PdfPCell(new Phrase(registrosArr[i], fontNormal)));
            PdfPCell cell = new PdfPCell(new Phrase(" "));
            cell.setFixedHeight(15);
            tablaRegistros.addCell(cell);
        }
        document.add(tablaRegistros);

        LaborRealizada jtg = ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("JTG");

        // Firma jefe de títulos y grados
        Paragraph jefe = new Paragraph(jtg.getFuncionario().getTraNombreSimple(), fontNormal);
        jefe.setAlignment(Element.ALIGN_CENTER);
        jefe.setSpacingBefore(20);
        document.add(jefe);

        cb.moveTo(margin * 1.25f, document.getPageSize().getHeight() - margin * 5.5f);
        cb.lineTo(margin * 1.25f + colWidth * 5, document.getPageSize().getHeight() - margin * 5.5f);
        cb.stroke();

        Paragraph cargo = new Paragraph(jtg.getLabel(), fontNormal);
        cargo.setAlignment(Element.ALIGN_CENTER);
        document.add(cargo);

        // Cerrar documento
        document.close();

        CommonCertificacionUtil.writeFile(buffer, PATH_TITULACION + name);

        LogUtil.setLog(genericSession.getRut(), aca.getId().getAcaRut());
        return CommonArchivoUtil.getFile(name, "tit");

    }
}
