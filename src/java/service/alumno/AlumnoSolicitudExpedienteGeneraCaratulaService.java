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

public class AlumnoSolicitudExpedienteGeneraCaratulaService {

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        InputStream input;
        String name;
        String description;

        WorkSession ws = genericSession.getWorkSession(key);

        ExpedienteLogro expl = ws.getExpedienteLogro();
        name = "CARATULA_EXPEDIENTE" + ".pdf";
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

        Font fontSmall = new Font(Font.HELVETICA, 9, Font.NORMAL);

        PdfPTable tablaPersonales = new PdfPTable(3);
        float[] columnPersonalesWidths = {65f, 30f, 5f};
        tablaPersonales.setWidths(columnPersonalesWidths);
        tablaPersonales.setWidthPercentage(100);

        PdfPCell celdaPerf1c1 = new PdfPCell(new Phrase(alumno.getNombreStd(), new Font(Font.HELVETICA, 10, Font.BOLD)));
        celdaPerf1c1.setColspan(3);
        celdaPerf1c1.setPadding(5f);
        celdaPerf1c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPerf1c1.setBorder(Rectangle.BOTTOM);
        tablaPersonales.addCell(celdaPerf1c1);

        PdfPCell celdaPerf2c1 = new PdfPCell(new Phrase("Apellidos y Nombres Completos", fontSmall));
        celdaPerf2c1.setColspan(3);
        celdaPerf2c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPerf2c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf2c1);

        PdfPCell celdaPerf3c1 = new PdfPCell(new Phrase(alumno.getAluDirecAlu().toUpperCase(), fontNormal));
        celdaPerf3c1.setColspan(3);
        celdaPerf3c1.setPadding(5f);
        celdaPerf3c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPerf3c1.setBorder(Rectangle.BOTTOM);
        tablaPersonales.addCell(celdaPerf3c1);

        PdfPCell celdaPerf4c1 = new PdfPCell(new Phrase("Domicilio", fontSmall));
        celdaPerf4c1.setColspan(3);
        celdaPerf4c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPerf4c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf4c1);

        PdfPCell celdaPerf5c1 = new PdfPCell(new Phrase("CÉDULA DE IDENTIDAD: " + FormatUtil.getRutFormateado(alumno.getAluRut()), fontNormal));
        celdaPerf5c1.setColspan(3);
        celdaPerf5c1.setPadding(5f);
        celdaPerf5c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf5c1);

        PdfPCell celdaPerf6c1 = new PdfPCell(new Phrase("TELÉFONO: " + alumno.getAluFonoAlu(), fontNormal));
        celdaPerf6c1.setColspan(3);
        celdaPerf6c1.setPadding(5f);
        celdaPerf6c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf6c1);

        PdfPCell celdaPerf7c1 = new PdfPCell(new Phrase("E-MAIL: " + ((alumno.getAluEmail() == null || alumno.getAluEmail().isEmpty()) ? alumno.getAluEmailUsach() : alumno.getAluEmailUsach() + "," + alumno.getAluEmail()), fontNormal));
        celdaPerf7c1.setPadding(5f);
        celdaPerf7c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf7c1);
        PdfPCell celdaPerf7c2 = new PdfPCell(new Phrase("FIRMA INTERESADO", fontSmall));
        celdaPerf7c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPerf7c2.setBorder(Rectangle.TOP);
        tablaPersonales.addCell(celdaPerf7c2);
        PdfPCell celdaPerf7c3 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaPerf7c3.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf7c3);

        PdfPCell celdaPerf8c1 = new PdfPCell(new Phrase("SOLICITA: " + expl.getPlanLogro().getPlalNomLogro().toUpperCase(), fontNormal));
        celdaPerf8c1.setColspan(3);
        celdaPerf8c1.setPadding(5f);
        celdaPerf8c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf8c1);

        PdfPCell celdaPerf9c1 = new PdfPCell(new Phrase("ESPECIALIDAD: " + (aca.getPlan().getMencion().getMenNom() == null ? "====================================" : aca.getPlan().getMencion().getMenNom().toUpperCase()), fontNormal));
        celdaPerf9c1.setColspan(3);
        celdaPerf9c1.setPadding(5f);
        celdaPerf9c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf9c1);

        PdfPCell celdaPerf10c1 = new PdfPCell(new Phrase(aca.getAluCarFunction().getNombreFacultad().trim().toUpperCase(), fontNormal));
        celdaPerf10c1.setColspan(3);
        celdaPerf10c1.setPadding(5f);
        celdaPerf10c1.setBorder(Rectangle.NO_BORDER);
        tablaPersonales.addCell(celdaPerf10c1);

        //cuadrado
        PdfPTable cuadro = new PdfPTable(1);
        cuadro.setTotalWidth(14f); // Ancho físico de 1 cm
        cuadro.setLockedWidth(true);  // Necesario para fijar ancho exacto
        PdfPCell cuadradoCell = new PdfPCell();
        cuadradoCell.setFixedHeight(14f); // Alto de 1 cm
        cuadradoCell.setPadding(0);
        cuadradoCell.setBorder(Rectangle.BOX);
        cuadro.addCell(cuadradoCell);

        //document.add(cuadro);
        PdfPTable tablaTitulosGrados = new PdfPTable(6);
        float[] columnTitulosGradosWidths = {18f, 27f, 5f, 25f, 5f, 20f};
        tablaTitulosGrados.setWidths(columnTitulosGradosWidths);
        tablaTitulosGrados.setWidthPercentage(100);

        // fila cabecera
        PdfPCell celdah1 = new PdfPCell(new Phrase("ARANCELES E IMPUESTOS", new Font(Font.HELVETICA, 10, Font.BOLD)));
        celdah1.setColspan(3);
        celdah1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdah1);
        PdfPCell celdah4 = new PdfPCell(new Phrase("REGISTROS", new Font(Font.HELVETICA, 10, Font.BOLD)));
        celdah4.setColspan(3);
        celdah4.setBorder(Rectangle.NO_BORDER);
        celdah4.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaTitulosGrados.addCell(celdah4);

        // fila 1
        PdfPCell celdaf1c1 = new PdfPCell(new Phrase("Solicitud", fontNormal));
        celdaf1c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf1c1);
        PdfPCell celdaf1c2 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf1c2.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf1c2);
        PdfPCell celdaf1c3 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf1c3.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf1c3);
        PdfPCell celdaf1c4 = new PdfPCell(new Phrase("V°B° Legal", fontNormal));
        celdaf1c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf1c4);
        PdfPCell celdaf1c5 = new PdfPCell(cuadro);
        celdaf1c5.setPadding(2f);
        celdaf1c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf1c5);
        PdfPCell celdaf1c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf1c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf1c6);

        // fila 2
        PdfPCell celdaf2c1 = new PdfPCell(new Phrase("Certificado", fontNormal));
        celdaf2c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf2c1);
        PdfPCell celdaf2c2 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf2c2.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf2c2);
        PdfPCell celdaf2c3 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf2c3.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf2c3);
        PdfPCell celdaf2c4 = new PdfPCell(new Phrase("V°B° Administrativo", fontNormal));
        celdaf2c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf2c4);
        PdfPCell celdaf2c5 = new PdfPCell(cuadro);
        celdaf2c5.setPadding(2f);
        celdaf2c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf2c5);
        PdfPCell celdaf2c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf2c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf2c6);

        // fila 3
        PdfPCell celdaf3c1 = new PdfPCell(new Phrase("Diploma", fontNormal));
        celdaf3c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf3c1);
        PdfPCell celdaf3c2 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf3c2.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf3c2);
        PdfPCell celdaf3c3 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf3c3.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf3c3);
        PdfPCell celdaf3c4 = new PdfPCell(new Phrase("Ficha (Ingreso)", fontNormal));
        celdaf3c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf3c4);
        PdfPCell celdaf3c5 = new PdfPCell(cuadro);
        celdaf3c5.setPadding(2f);
        celdaf3c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf3c5);
        PdfPCell celdaf3c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf3c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf3c6);

        // fila 4
        PdfPCell celdaf4c1 = new PdfPCell(new Phrase("TOTAL", fontNormal));
        celdaf4c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf4c1);
        PdfPCell celdaf4c2 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf4c2.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf4c2);
        PdfPCell celdaf4c3 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf4c3.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf4c3);
        PdfPCell celdaf4c4 = new PdfPCell(new Phrase("A V B° Académico", fontNormal));
        celdaf4c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf4c4);
        PdfPCell celdaf4c5 = new PdfPCell(cuadro);
        celdaf4c5.setPadding(2f);
        celdaf4c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf4c5);
        PdfPCell celdaf4c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf4c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf4c6);

        // fila 5
        PdfPCell celdaf5c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf5c1.setColspan(3);
        celdaf5c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf5c1);
        PdfPCell celdaf5c4 = new PdfPCell(new Phrase("Enrolamiento", fontNormal));
        celdaf5c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf5c4);
        PdfPCell celdaf5c5 = new PdfPCell(cuadro);
        celdaf5c5.setPadding(2f);
        celdaf5c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf5c5);
        PdfPCell celdaf5c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf5c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf5c6);

        // fila 6
        PdfPCell celdaf6c1 = new PdfPCell(new Phrase("OBSERVACIONES", new Font(Font.HELVETICA, 10, Font.BOLD)));
        celdaf6c1.setColspan(3);
        celdaf6c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf6c1);
        PdfPCell celdaf6c4 = new PdfPCell(new Phrase("Certificación", fontNormal));
        celdaf6c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf6c4);
        PdfPCell celdaf6c5 = new PdfPCell(cuadro);
        celdaf6c5.setPadding(2f);
        celdaf6c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf6c5);
        PdfPCell celdaf6c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf6c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf6c6);

        // fila 7
        PdfPCell celdaf7c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf7c1.setColspan(3);
        celdaf7c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf7c1);
        PdfPCell celdaf7c4 = new PdfPCell(new Phrase("Ficha (Registro)", fontNormal));
        celdaf7c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf7c4);
        PdfPCell celdaf7c5 = new PdfPCell(cuadro);
        celdaf7c5.setPadding(2f);
        celdaf7c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf7c5);
        PdfPCell celdaf7c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf7c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf7c6);

        // fila 8
        PdfPCell celdaf8c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf8c1.setColspan(3);
        celdaf8c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf8c1);
        PdfPCell celdaf8c4 = new PdfPCell(new Phrase("Calígrafo", fontNormal));
        celdaf8c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf8c4);
        PdfPCell celdaf8c5 = new PdfPCell(cuadro);
        celdaf8c5.setPadding(2f);
        celdaf8c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf8c5);
        PdfPCell celdaf8c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf8c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf8c6);

        // fila 9
        PdfPCell celdaf9c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf9c1.setColspan(3);
        celdaf9c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf9c1);
        PdfPCell celdaf9c4 = new PdfPCell(new Phrase("Envío o Firmas", fontNormal));
        celdaf9c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf9c4);
        PdfPCell celdaf9c5 = new PdfPCell(cuadro);
        celdaf9c5.setPadding(2f);
        celdaf9c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf9c5);
        PdfPCell celdaf9c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf9c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf9c6);

        // fila 10
        PdfPCell celdaf10c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf10c1.setColspan(3);
        celdaf10c1.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf10c1);
        PdfPCell celdaf10c4 = new PdfPCell(new Phrase("Ficha (Kardex)", fontNormal));
        celdaf10c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf10c4);
        PdfPCell celdaf10c5 = new PdfPCell(cuadro);
        celdaf10c5.setPadding(2f);
        celdaf10c5.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf10c5);
        PdfPCell celdaf10c6 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf10c6.setBorder(Rectangle.BOTTOM);
        tablaTitulosGrados.addCell(celdaf10c6);

        LaborRealizada jtg = ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("JTG");

        // fila nombre
        PdfPCell celdaf12c1 = new PdfPCell(new Phrase(jtg.getFuncionario().getTraNombreSimple(), fontNormal));
        celdaf12c1.setColspan(3);
        celdaf12c1.setBorder(Rectangle.NO_BORDER);
        celdaf12c1.setBorder(Rectangle.BOTTOM);
        celdaf12c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaTitulosGrados.addCell(celdaf12c1);
        PdfPCell celdaf12c4 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf12c4.setColspan(3);
        celdaf12c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf12c4);

        // fila cargo
        PdfPCell celdaf13c1 = new PdfPCell(new Phrase(jtg.getLabel(), fontNormal));
        celdaf13c1.setColspan(3);
        celdaf13c1.setBorder(Rectangle.NO_BORDER);
        celdaf13c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaTitulosGrados.addCell(celdaf13c1);
        PdfPCell celdaf13c4 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaf13c4.setColspan(3);
        celdaf13c4.setBorder(Rectangle.NO_BORDER);
        tablaTitulosGrados.addCell(celdaf13c4);

        PdfPTable tablaGeneral = new PdfPTable(2);
        float[] columnGeneralWidths = {60f, 40f};
        tablaGeneral.setWidths(columnGeneralWidths);
        tablaGeneral.setWidthPercentage(100);

        PdfPCell celdaGen_f0c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaGen_f0c1.setBorder(Rectangle.NO_BORDER);
        celdaGen_f0c1.setColspan(2);
        tablaGeneral.addCell(celdaGen_f0c1);

        PdfPCell celdaGen_f1c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaGen_f1c1.setBorder(Rectangle.NO_BORDER);
        tablaGeneral.addCell(celdaGen_f1c1);

        PdfPCell celdaGen_f1c2 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaGen_f1c2.setFixedHeight(80f);
        tablaGeneral.addCell(celdaGen_f1c2);

        PdfPCell celdaGen_f2c1 = new PdfPCell(new Phrase(" ", fontNormal));
        celdaGen_f2c1.setBorder(Rectangle.NO_BORDER);
        celdaGen_f2c1.setColspan(2);
        tablaGeneral.addCell(celdaGen_f2c1);

        PdfPCell celdaGen_f3c1 = new PdfPCell(tablaPersonales);
        celdaGen_f3c1.setColspan(2);
        celdaGen_f3c1.setPadding(10f);
        tablaGeneral.addCell(celdaGen_f3c1);

        PdfPCell celdaGen_f4c1 = new PdfPCell(new Phrase("USO EXCLUSIVO DE TÍTULOS Y GRADOS", new Font(Font.HELVETICA, 10, Font.BOLD)));
        celdaGen_f4c1.setColspan(2);
        celdaGen_f4c1.setPadding(5f);
        celdaGen_f4c1.setBorder(Rectangle.NO_BORDER);
        celdaGen_f4c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaGeneral.addCell(celdaGen_f4c1);

        PdfPCell celdaGen_f5c1 = new PdfPCell(tablaTitulosGrados);
        celdaGen_f5c1.setColspan(2);
        celdaGen_f5c1.setPadding(10f);
        tablaGeneral.addCell(celdaGen_f5c1);

        document.add(tablaGeneral);

        //document.add(tablaTitulosGrados);
        // Firma jefe de títulos y grados
        /*cb.moveTo(margin * 1.25f, document.getPageSize().getHeight() - margin * 5.5f);
        cb.lineTo(margin * 1.25f + colWidth * 5, document.getPageSize().getHeight() - margin * 5.5f);
        cb.stroke();*/
        // Cerrar documento
        document.close();

        CommonCertificacionUtil.writeFile(buffer, PATH_TITULACION + name);

        LogUtil.setLog(genericSession.getRut(), aca.getId().getAcaRut());
        return CommonArchivoUtil.getFile(name, "tit");

    }
}
