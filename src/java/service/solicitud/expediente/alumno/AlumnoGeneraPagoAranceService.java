package service.solicitud.expediente.alumno;

import com.lowagie.text.*;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import static com.lowagie.text.Image.getInstance;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.Unidad;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
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

import java.io.*;
import java.util.Locale;
import org.apache.struts2.ServletActionContext;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import session.WorkSession;

public class AlumnoGeneraPagoAranceService {
    
    public  ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        InputStream input;
        String name;
        String description;

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aca = ws.getAluCar();
        String datoAlu = aca.getId().getAcaRut()+"-"+aca.getId().getAcaCodCar()+"-"+aca.getId().getAcaAgnoIng()+"-"+aca.getId().getAcaSemIng();
        String logro = ws.getExpedienteLogro().getPlanLogro().getLogro().getLogrDes();
        Integer folio = CommonSequenceUtil.getDocumentSeq();
        
        name = sanitizeFileName(datoAlu + "-" + logro +  "-Pago-de-Arancel-Template-" + folio +".pdf");
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
        Font fontSmall = getFont("open_font", 8.0f, NORMAL);
        Font fontCondensed = getFont("open_condensed_font", 12.0f, NORMAL);

        // Establecer logotipo
        Image logo = Image.getInstance(ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH));

        // Firma
        Unidad facultad = aca.getUnidadFacultad();
        Image firma = getInstance(getServletContext().getRealPath(SystemParametersUtil.CERTIFICATE_REGISTRADOR_IMAGE_PATH.replace("###", facultad.getUniCod().toString())));

        // Añadir texto
        Paragraph paragraph = new Paragraph("REPUBLICA DE CHILE\nUNIVERSIDAD DE SANTIAGO DE CHILE\nFACULTAD DE ADMINISTRACIÓN Y ECONOMÍA\nREGISTRO CURRICULAR", fontCondensed);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        document.add(new Paragraph("\n\n\n"));

        // Títulos
        Paragraph title1 = new Paragraph("PAGO DE ARANCEL", fontBold);
        title1.setAlignment(Element.ALIGN_CENTER);
        document.add(title1);

        Paragraph title2 = new Paragraph("PROCESO DE GRADUACIÓN / TITULACIÓN", fontBold);
        title2.setAlignment(Element.ALIGN_CENTER);
        document.add(title2);

        document.add(new Paragraph("\n\n"));

        String alumnoDoc = alumno.getAluRut()+"-"+alumno.getAluDv();
        String alumnoNombre = alumno.getAluPaterno()+" "+alumno.getAluMaterno()+", "+alumno.getAluNombre();

        document.add(new Paragraph("Cédula Nacional de Identidad N°.: " + alumnoDoc + "\n\n", fontRegular));
        document.add(new Paragraph("Nombre: " + alumnoNombre + "\n\n", fontRegular));
        document.add(new Paragraph("Arancel correspondiente a tramitación para la obtención de Certificado y Diploma de Grado Académico y/o Título Profesional.", fontRegular));

        document.add(new Paragraph("\n"));

        // Total a pagar
        int valor = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getArancelLogro(ws.getExpedienteLogro().getPlanLogro().getLogro().getLogrCod(), ws.getAluCar().getPlan().getMencion().getCarrera().getTprograma().getTprCod());
        String pagoFormateado = String.format(Locale.forLanguageTag("es-CL"), "%,d", valor);
        document.add(new Paragraph("Total a pagar $ " + pagoFormateado, fontBold));

        document.add(new Paragraph("\n\n\n\n\n\n\n\n\n"));

        // Fecha
        document.add(new Paragraph(DateUtil.getFechaCiudad(getSysdate()), fontRegular));

        document.add(new Chunk(new LineSeparator()));
        // Pie de página
        document.add(new Paragraph("Datos de Transferencia:", fontRegular));
        document.add(new Paragraph("\nNombre de la Institución: Universidad de Santiago de Chile\n" +
        "Rut USACH: 60.911.000-7\n" +
        "Banco: Banco Santander\n" +
        "Número de cuenta corriente: 0-000-1903580-8\n" +
        "Correo electrónico envío transferencia: recaudacion.tesoreria@usach.cl\n" +
        "IMPORTANTE. Motivo: Título o Grado", fontSmall));
        

        // Logo arriba a la izquierda
        float pageHeight = document.getPageSize().getHeight();
        float pageWidth = document.getPageSize().getWidth();

        logo.scaleToFit(100, 100);
        logo.scalePercent(54f);
        float logoHeight = logo.getScaledHeight();
        float logoPosX = margin; // margen izquierdo
        float logoPosY = pageHeight - margin / 2 - logoHeight - 10; // arriba
        logo.setAbsolutePosition(logoPosX, logoPosY);
        document.add(logo);

        // Firma abajo a la derecha
        firma.scaleToFit(200, 80);
        logo.scalePercent(20f);
        float firmaWidth = firma.getScaledWidth();
        float firmaPosX = pageWidth - margin - firmaWidth; // derecha
        float firmaPosY = margin + 220; // abajo
        firma.setAbsolutePosition(firmaPosX, firmaPosY);
        document.add(firma);

        // Cerrar documento
        document.close();
        
        CommonCertificacionUtil.writeFile(buffer, PATH_TITULACION + name);

        LogUtil.setLog(genericSession.getRut(), aca.getId().getAcaRut());
        return CommonArchivoUtil.getFile(name, "tit");     
    }
}
