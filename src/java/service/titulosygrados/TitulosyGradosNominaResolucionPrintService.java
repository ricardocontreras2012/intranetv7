package service.titulosygrados;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import domain.model.Administrativo;
import domain.model.AluCar;
import domain.model.AluCarFunctionsView;
import domain.model.Alumno;
import domain.model.ExpedienteLogro;
import domain.model.LaborRealizada;
import domain.model.Tlogro;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getFormattedDate;
import infrastructure.util.FormatUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonUsersUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.struts2.ServletActionContext;
import session.GenericSession;
import session.WorkSession;

public class TitulosyGradosNominaResolucionPrintService {

    static Font TNR_6 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 6);
    static Font TNR_6_UNDERLINED = FontFactory.getFont(FontFactory.TIMES_ROMAN, 6, Font.UNDERLINE);    
    static Font TNR_7 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7);    
    static Font TNR_8 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);    
    static Font TNR_9 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9);
    static Font TNR_9B = FontFactory.getFont(FontFactory.TIMES_BOLD, 9);
    static Font TNR_9B_UNDERLINED =  FontFactory.getFont(FontFactory.TIMES_BOLD, 9, Font.UNDERLINE);    
    static Font TNR_10 =           FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
    static Font TNR_10B = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        InputStream input;
        String name;
        String description;

        WorkSession ws = genericSession.getWorkSession(key);
        String nomina = ws.getExpedienteLogroList().get(0).getNomina().getExpnNumero();
        int agno = ws.getExpedienteLogroList().get(0).getNomina().getExpnAgno();

        input = getInput(genericSession, nomina, agno, key);
        name = "RESOLUCION_NOMINA_" + nomina + "_" + agno + ".pdf";
        description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }

    private InputStream getInput(GenericSession genericSession, String nomina, Integer agno,
            String key)
            throws Exception {
   
        WorkSession ws = genericSession.getWorkSession(key);
        ExpedienteLogro dummyExp = ws.getExpedienteLogroList().get(0);
        Tlogro tlogro = dummyExp.getPlanLogro().getLogro().getTlogro();

        Document document = new Document(PageSize.LETTER);
        float margin = 80f; // márgenes
        document.setMargins(margin, margin, margin, margin-40);

        // PdfWriter y buffer para el documento PDF
        InputStream pdfStream = null;  // Inicializamos pdfStream como null
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        // Crear PdfWriter para manejar el flujo de salida
        PdfWriter writer = PdfWriter.getInstance(document, buffer);

        // Abrir el documento aquí, para asegurarse de que el documento esté listo para agregar contenido.
        document.open();

        try {
            // **Membrete con imagen**
            String imagePath = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_SOMOS_PATH);

            // **Evento para agregar el membrete en cada página**
            PdfPageEventHelper event = new PdfPageEventHelper() {
                @Override
                public void onEndPage(PdfWriter writer, Document document) {
                    try {
                        PdfContentByte cb = writer.getDirectContent();
                        Image image = Image.getInstance(imagePath);
                        image.scaleAbsolute(110f, 25f); // Escala la imagen
                        image.setAbsolutePosition(margin + 50, PageSize.LETTER.getHeight() - 60); // Ubica la imagen
                        cb.addImage(image);
                    } catch (DocumentException | IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            writer.setPageEvent(event);

            float headerAreaTop = PageSize.LETTER.getHeight() - 70; // Zona debajo de la imagen
            float headerAreaBottom = PageSize.LETTER.getHeight() - 120;
            float headerAreaLeft = margin + 40; // Alineado a la izquierda
            float headerAreaRight = margin + 190f;

            // **Usamos ColumnText para crear una zona restringida**
            ColumnText columnText = new ColumnText(writer.getDirectContent());
            columnText.setSimpleColumn(headerAreaLeft, headerAreaBottom, headerAreaRight, headerAreaTop);

            // **Párrafo de encabezado centrado dentro de la zona pero en la parte izquierda**            
            Paragraph headerBlock = new Paragraph(
                    "REPÚBLICA DE CHILE\n"
                    + "UNIVERSIDAD DE SANTIAGO DE CHILE\n"
                    + "SECRETARÍA GENERAL", TNR_7
            );
            headerBlock.setAlignment(Element.ALIGN_CENTER); // Centrado dentro del área
            headerBlock.setLeading(8f);
            headerBlock.setSpacingBefore(0); // Espacio antes del bloque

            // Añadir el párrafo al área especificada
            columnText.addElement(headerBlock);
            columnText.go();  // Establecer la zona para el texto

            document.add(new Paragraph("\n"));
            
            float leftIndent = PageSize.LETTER.getWidth() / 2 - margin -12;
            
            Paragraph confHeader = new Paragraph("CONFIERE " + tlogro.getTloDesLargaPlural().toUpperCase() + " A", TNR_9B);
            Paragraph indicaHeader = new Paragraph("PERSONAS QUE INDICA", TNR_9B_UNDERLINED);
            Paragraph stgoHeader = new Paragraph("SANTIAGO,",TNR_9B);
            
            confHeader.setIndentationLeft(leftIndent);
            indicaHeader.setIndentationLeft(leftIndent);
            stgoHeader.setIndentationLeft(leftIndent);
            stgoHeader.setSpacingBefore(5.5f);
            
            document.add(confHeader);
            document.add(indicaHeader);
            document.add(stgoHeader);

            // VISTOS con sangría solo en la primera línea desde la mitad de la página
            Phrase vistosPhrase = new Phrase("VISTOS: ", TNR_9B);
            vistosPhrase.add(new Phrase(
                    "El Decreto con Fuerza de Ley 29 de 2023, del Ministerio de Educación, que aprueba el Estatuto de la Universidad de Santiago de Chile, adecuado al Título II de la Ley 21.094, sobre Universidades Estatales; la Resolución Exenta 5.721 de 2024, de la Contralora Universitaria de la Universidad de Santiago de Chile, que exime del control de legalidad a las materias que indica; la Resolución Exenta 1.118 de 2025, del Rector de la Universidad de Santiago de Chile, que delega atribuciones en la autoridad que indica; El Oficio N°127 de 2025, del Rector de la Universidad de Santiago de Chile, que registra la firma de la autoridad ante el Ministerio de Educación; y la Resolución 36 de 2024, de la Contraloría General de la República, sobre exención del trámite de toma de razón en las materias que indican.\n\n",
                    TNR_9
            ));
            
            Paragraph vistos = new Paragraph(vistosPhrase);
            vistos.setSpacingBefore(9f);
            
            // Sangría solo en la primera línea desde el centro de la página
            vistos.setFirstLineIndent(leftIndent); // Sangría en la primera línea
            vistos.setAlignment(Element.ALIGN_JUSTIFIED); // Justificado
            vistos.setLeading(12f);
            document.add(vistos);

            // RESUELVO con sangría solo en la primera línea desde la mitad de la página            
            Paragraph resuelvo = new Paragraph("RESUELVO:\n\n", TNR_9B);
            resuelvo.setFirstLineIndent(leftIndent); // Sangría en la primera línea desde el centro de la página
            resuelvo.setSpacingBefore(3);
            document.add(resuelvo);

            // Confierase           
            Phrase confPhrase = new Phrase("1.   CONFIÉRASE", TNR_9B);
            confPhrase.add(new Phrase(" a la(s) siguiente(s) persona(s) los " + tlogro.getTloDesLargaPlural().toLowerCase() + " que a continuación indican:\n\n", TNR_9));

            Paragraph conf = new Paragraph(confPhrase);
            conf.setFirstLineIndent(leftIndent);  // Sangría desde el centro de la página
            conf.setSpacingBefore(3);
            conf.setLeading(12f);
            conf.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(conf);

            // Tabla de personas
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(6);
            float[] columnWidths = {0.4f, 2.5f, 1.0f, 2.5f, 1.2f};
            table.setWidths(columnWidths);

            // Encabezados de tabla
            Font tableHeaderFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 8);
            String[] headers = {"N°", "Nombre completo", "RUT", tlogro.getTloDesLarga(), "Otorgamiento"};
            for (String headerText : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(headerText, tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            AtomicInteger index = new AtomicInteger(1);
            ws.getExpedienteLogroList().forEach(exp -> {
                Alumno alu = exp.getAluCar().getAlumno();
                String nombre = CommonUsersUtil.getNombreTyG(alu.getAluPaterno(), alu.getAluMaterno(), alu.getAluNombre());
                
                String rut = alu.getAluRut() + "-" + alu.getAluDv();

                String logro1 = "F".equals(exp.getExplGenero()) ? exp.getPlanLogro().getPlalLin1F() : exp.getPlanLogro().getPlalLin1M();
                String logro2 = "F".equals(exp.getExplGenero())
                        ? Optional.ofNullable(exp.getPlanLogro().getPlalLin2F()).orElse("")
                        : Optional.ofNullable(exp.getPlanLogro().getPlalLin2M()).orElse("");

                String logro = logro1 + " " + logro2;
                //String rol = exp.getExplRol();
                String fecha = getFormattedDate(exp.getExplFecLogroAdm(), "dd-MM-yyyy");

                agregarCelda(table, String.valueOf(index.getAndIncrement()));
                agregarCelda(table, nombre);
                agregarCelda(table, rut);
                agregarCelda(table, logro);
                //agregarCelda(table, rol);
                agregarCelda(table, fecha);
            });

            document.add(table);

            // Incorporase
            Phrase incorporaPhrase = new Phrase("2.   INCORPÓRESE", TNR_9B);
            incorporaPhrase.add(new Phrase(" la presente Resolución a cada uno de los expedientes de las personas señaladas en el numeral primero precedente, según corresponda.", TNR_9));
            Paragraph incorpora = new Paragraph(incorporaPhrase);
            incorpora.setFirstLineIndent(leftIndent);  // Sangría desde el centro de la página
            incorpora.setSpacingBefore(6);
            incorpora.setLeading(12f);
            incorpora.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(incorpora);

            // Emítase            
            Phrase emitaPhrase = new Phrase("3.   EMÍTESE", TNR_9B);
            emitaPhrase.add(new Phrase(" las certificaciones de los " + tlogro.getTloDesLargaPlural().toLowerCase() + " antes individualizados a las personas señaladas en el numeral primero precedente.", TNR_9));
            Paragraph emita = new Paragraph(emitaPhrase);
            emita.setFirstLineIndent(leftIndent);  // Sangría desde el centro de la página
            emita.setSpacingBefore(6);
            emita.setLeading(12f);
            emita.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(emita);

            // Comuníquese
            Phrase comunicaPhrase = new Phrase("4.   COMUNÍQUESE", TNR_9B);
            comunicaPhrase.add(new Phrase(" la presente Resolución, una vez totalmente tramitada, a las autoridades de la Universidad de Santiago de Chile que corresponda.\n\n", TNR_9));
            Paragraph comunica = new Paragraph(comunicaPhrase);
            comunica.setFirstLineIndent(leftIndent);  // Sangría desde el centro de la página
            comunica.setSpacingBefore(6);
            comunica.setLeading(12f);
            comunica.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(comunica);

            Phrase comyregPhrase = new Phrase("COMUNÍQUESE Y REGÍSTRESE.\n\n\n\n\n", TNR_9B);
            Paragraph comyreg = new Paragraph(comyregPhrase);
            comyreg.setFirstLineIndent(leftIndent);  // Sangría desde el centro de la página
            comyreg.setSpacingBefore(6);
            comyreg.setLeading(12f);
            document.add(comyreg);

            Paragraph signature = new Paragraph();
            signature.setAlignment(Element.ALIGN_CENTER);
            LaborRealizada secGeneral = ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("SG");
            // Add the bold name
            Chunk name = new Chunk(secGeneral.getFuncionario().getTraNombreSimple() + "\n", TNR_10B);
            signature.add(name);
            signature.setLeading(12f);

            // Add the rest of the signature in normal font
            signature.add(new Chunk(secGeneral.getLabel() + "\n(FAC. DELEGADA, FIRMA POR RES. EX. 1.118, DE 2025)\n", TNR_10));
            document.add(signature);

            Paragraph initials = new Paragraph(ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getIniciales(genericSession.getRut(), "Funcionario de Títulos y Grados") + "\n", TNR_8);
            initials.setAlignment(Element.ALIGN_LEFT);
            initials.setSpacingBefore(6);
            document.add(initials);

            Paragraph distributionHeader = new Paragraph("Distribución:\n", TNR_6_UNDERLINED);
            document.add(distributionHeader);
            AluCar aca = dummyExp.getAluCar();
            AluCarFunctionsView acaFuc = CommonAlumnoUtil.getAluCarFunction(aca);
            String facultad = acaFuc.getNombreFacultad().trim();
            Integer uniCod = acaFuc.getUnidadFacultad();

            Administrativo adm = ContextUtil.getDAO().getAdministrativoPersistence(ActionUtil.getDBUser()).getRegistrador(uniCod);

            String distributionText = "1.\t" + "Registro Currucular de la " + facultad + ", " + adm.getAdmEmail() + ";\n"
                    + "2.\tRegistro Académico y Curricular, maria.duran@usach.cl;\n"
                    + "3.\tDirección de Información y Gestión Documental, karina.fuentes.d@usach.cl;\n"
                    + "4.\tUnidad Títulos y Grados, utg@usach.cl;\n"
                    + "5.\tUnidad de Partes, Informaciones y Archivo, upia@usach.cl;\n"
                    + "Memorándum STD " + nomina + "/" + agno;
            Paragraph distribution = new Paragraph(distributionText, TNR_6);
            //distribution.setIndentationLeft(10f);
            document.add(distribution);

            // Cierre del documento después de todas las operaciones
            document.close();

            pdfStream = new ByteArrayInputStream(buffer.toByteArray());

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return pdfStream;        
    }

    private void agregarCelda(PdfPTable table, String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, TNR_8));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);  // Alineación centrada
        cell.setPadding(2);  // Agregar un poco de padding para mejorar la apariencia
        table.addCell(cell);
    }
}