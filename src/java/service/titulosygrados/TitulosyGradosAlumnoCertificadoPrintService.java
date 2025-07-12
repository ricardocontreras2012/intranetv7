package service.titulosygrados;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfArray;
import com.lowagie.text.pdf.PdfBoolean;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfIndirectObject;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfStream;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.ExpedienteLogro;
import domain.model.LaborRealizada;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FontsPDFUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import java.awt.color.ICC_Profile;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static java.security.Security.addProvider;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Optional;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo
 */
public class TitulosyGradosAlumnoCertificadoPrintService {

    static final int TCERT = 40;

    FontsPDFUtil fontsUtil = (FontsPDFUtil) getServletContext().getAttribute("fontsUtil");

    Font TNR_16 = fontsUtil.crearFont("times", 16, Font.BOLD);
    Font TNR_12 = fontsUtil.crearFont("times", 12, Font.NORMAL);
    Font TNR_12B = fontsUtil.crearFont("times", 12, Font.BOLD);
    Font TNR_12BI = fontsUtil.crearFont("times", 18, Font.BOLDITALIC);

    public ActionInputStreamUtil service(GenericSession genericSession,
            String key, Integer pos, String rol, Integer resol, String fecha) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        ExpedienteLogro exp = saveExp(ws.getExpedienteLogroList().get(pos), rol, resol, fecha);
        String name;
        String description;
        Integer folio = CommonCertificacionUtil.getFolio();
        name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, TCERT);
        description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(genericSession, ws.getAluCar(), exp, folio, name));
    }

    private ExpedienteLogro saveExp(ExpedienteLogro exp, String rol, Integer resol, String fecha) {
        Date fechaStd = DateUtil.stringToDate(fecha, "yyyy-MM-dd");
        ContextUtil.getDAO().getExpedienteLogroPersistence(ActionUtil.getDBUser()).saveExpediente(exp, rol, resol, fechaStd);
        return ContextUtil.getDAO().getExpedienteLogroPersistence(ActionUtil.getDBUser()).find(exp);
    }

    public InputStream getInput(GenericSession genericSession, AluCar aca, ExpedienteLogro exp, Integer folio, String name) throws Exception {

        Integer genera = genericSession.getRut();
        Date fecha = getSysdate();

        Document doc = new Document(PageSize.LETTER);
        doc.setMargins(80.0f, 80.0f, 80.0f, 80.0f);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(doc, buffer);

        /// REQUISITOS PDF-A ////////////////////////////////////////////////
        // Configurar versión PDF y etiquetado
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        writer.setTagged();

        // Configurar MarkInfo para PDF etiquetado
        PdfDictionary markInfo = new PdfDictionary();
        markInfo.put(PdfName.MARKED, PdfBoolean.PDFTRUE);
        writer.getExtraCatalog().put(PdfName.MARKINFO, markInfo);

        // Crear metadatos XMP
        writer.createXmpMetadata();

        // Metadatos básicos
        writer.getInfo().put(PdfName.TITLE, new PdfString("CERTIFICADO DE " + exp.getPlanLogro().getLogro().getTlogro().getTloDesLarga().toUpperCase()));
        writer.getInfo().put(PdfName.CREATOR, new PdfString("FAE-USACH"));
        writer.getInfo().put(PdfName.AUTHOR, new PdfString("FAE-USACH"));
        writer.getInfo().put(PdfName.PRODUCER, new PdfString("OpenPDF"));
        ////////////////////////////// FIN REQUISITOS PDF-A /////////////////
        
        String iniciales = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getIniciales(genericSession.getRut(), "Funcionario de Títulos y Grados");

        TitulosyGradosAlumnoCertificadoPrintService.FooterPageEvent hf = new TitulosyGradosAlumnoCertificadoPrintService.FooterPageEvent(fecha, iniciales);
        writer.setPageEvent(hf);
        addProvider(new BouncyCastleProvider());

        doc.open();

        /// REQUISITOS PDF-A ////////////////////////////////////////////////
        // Cargar perfil ICC (sRGB)
        ICC_Profile iccProfile = PdfUtil.loadICCProfile(getServletContext().getRealPath("/images/sRGB2014.icc"));

        // Configurar OutputIntent con el perfil ICC
        PdfStream iccStream = new PdfStream(iccProfile.getData());
        iccStream.put(PdfName.N, new PdfNumber(3));
        iccStream.put(PdfName.ALTERNATE, PdfName.DEVICERGB);
        iccStream.flateCompress();

        PdfIndirectObject iccIndirect = writer.addToBody(iccStream);

        PdfDictionary outputIntent = new PdfDictionary(PdfName.OUTPUTINTENT);
        outputIntent.put(PdfName.S, PdfName.GTS_PDFA1);
        outputIntent.put(new PdfName("OutputConditionIdentifier"), new PdfString("sRGB IEC61966-2.1"));
        outputIntent.put(PdfName.DESTOUTPUTPROFILE, iccIndirect.getIndirectReference());

        PdfArray outputIntents = new PdfArray();
        outputIntents.add(outputIntent);
        writer.getExtraCatalog().put(PdfName.OUTPUTINTENTS, outputIntents);

        // Generar y asignar XMP corregido
        String xmpMetadata = PdfUtil.createValidXMPMetadata("CERTIFICADO DE " + exp.getPlanLogro().getLogro().getTlogro().getTloDesLarga().toUpperCase(), aca.getAlumno().getNombreStd());
        writer.setXmpMetadata(xmpMetadata.getBytes(StandardCharsets.UTF_8));
        ////////////////////////////// FIN REQUISITOS PDF-A /////////////////

        Image backgroundImage = getInstance(getServletContext().getRealPath("/images/local/background/certificado-logro.png"));
        // Establecer la imagen como fondo, adaptándola al tamaño de la página carta
        // Se escala la imagen a las dimensiones de la página tamaño carta
        backgroundImage.scaleToFit(doc.getPageSize().getWidth(), doc.getPageSize().getHeight());
        // Posicionar la imagen en la esquina inferior izquierda
        backgroundImage.setAbsolutePosition(0, 0);
        // Añadir la imagen de fondo
        doc.add(backgroundImage);

        putContent(doc, aca, exp);
        doc.close();

        CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);

        buffer.close();

        LogUtil.setLog(genera, aca.getId().getAcaRut());

        return CommonArchivoUtil.getFile(name, "cert");
    }

    private void putContent(Document doc, AluCar aluCar, ExpedienteLogro exp) {
        Alumno alumno = aluCar.getAlumno();
        LaborRealizada sec = ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("SG");

        Paragraph titulo = new Paragraph("CERTIFICADO DE " + exp.getPlanLogro().getLogro().getTlogro().getTloDesLarga().toUpperCase(), TNR_16);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(titulo);

        doc.add(new Paragraph("\n\n\n", TNR_12));

        Paragraph cuerpo = new Paragraph();
        cuerpo.setAlignment(Paragraph.ALIGN_JUSTIFIED);

        cuerpo.setFirstLineIndent(30f);

        String tipo = exp.getPlanLogro().getLogro().getTlogro().getTloDes();

        String secStr = "1".equals(sec.getFuncionario().getTraSexo()) ? "La " : "El ";

        cuerpo.add(new Chunk(secStr + FormatUtil.initCapAll(sec.getLabel()) + " de la Universidad de Santiago de Chile, conforme con lo dispuesto en el Decreto con Fuerza de Ley 29 de 2023, del Ministerio de Educación, que aprueba el Estatuto de la Corporación de Educación Superior, adecuado al Título II de la Ley 21.094, sobre Universidades Estatales, y de acuerdo con la Resolución Exenta 1.118 de 21 de marzo de 2025, del Rector, que delega de atribuciones en las autoridades que indica, certifica que con fecha " + DateUtil.getFechaEnPalabras(exp.getExplFecResol()) + " se confirió a " + ("1".equals(alumno.getAluSexo()) ? "doña " : "don "), TNR_12));
        cuerpo.add(new Chunk(alumno.getNombreStd(), TNR_12B));
        cuerpo.add(new Chunk(", RUT " + formatearConPuntos(alumno.getAluRut()) + "-" + alumno.getAluDv()
                + ("Diplomado".equals(tipo) ? " con una duración de " + ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getHorasCromoMalla(aluCar.getId().getAcaCodCar(), aluCar.getAcaCodMen(), aluCar.getAcaCodPlan()) + " horas cronológicas " : "")
                + ", el " + exp.getPlanLogro().getLogro().getTlogro().getTloDesLarga().toLowerCase() + " de:\n\n", TNR_12));

        doc.add(cuerpo);

        doc.add(new Paragraph("\n\n\n", TNR_12));

        String logro1 = "F".equals(exp.getExplGenero()) ? exp.getPlanLogro().getPlalLin1F() : exp.getPlanLogro().getPlalLin1M();
        String logro2 = "F".equals(exp.getExplGenero())
                ? Optional.ofNullable(exp.getPlanLogro().getPlalLin2F()).orElse("")
                : Optional.ofNullable(exp.getPlanLogro().getPlalLin2M()).orElse("");

        Paragraph logroParrafo1 = new Paragraph(logro1.toUpperCase(), TNR_12BI);
        logroParrafo1.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(logroParrafo1);

        Paragraph logroParrafo2 = new Paragraph(logro2.toUpperCase(), TNR_12BI);
        logroParrafo2.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(logroParrafo2);

        String distincion = "";

        if ("Grado".equals(tipo)) {
            int tipoCarrera = aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip();

            if (tipoCarrera == 20 || tipoCarrera == 30) {
                distincion = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getDistincion(aluCar.getId().getAcaRut(), aluCar.getId().getAcaCodCar(), aluCar.getId().getAcaAgnoIng(), aluCar.getId().getAcaSemIng(), aluCar.getAcaCodMen(), aluCar.getAcaCodPlan(), exp.getId().getExplCorrel());
            }
        }

        doc.add(new Paragraph("\n\n\n", TNR_12));
        Paragraph textoFinal = new Paragraph();
        textoFinal.setFirstLineIndent(30f);
        textoFinal.add(new Chunk(("".equals(distincion) ? "" : " " + distincion) + ".\n\n", TNR_12));
        textoFinal.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(textoFinal);

        doc.add(new Paragraph("\n\n\n\n\n", TNR_12));

        Paragraph firmaLinea1 = new Paragraph(sec.getFuncionario().getTraNombreSimple(), TNR_12B);
        firmaLinea1.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(firmaLinea1);

        Paragraph firmaLinea2 = new Paragraph(sec.getLabel(), TNR_12);
        firmaLinea2.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(firmaLinea2);

        Paragraph firmaLinea3 = new Paragraph("UNIVERSIDAD DE SANTIAGO DE CHILE", TNR_12);
        firmaLinea3.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(firmaLinea3);               
    }

    public static String formatearConPuntos(int numero) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(','); // aunque no se usa decimal aquí, es buena práctica setearlo

        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        formatter.setGroupingSize(3);
        formatter.setGroupingUsed(true);

        return formatter.format(numero);
    }

    public class FooterPageEvent extends PdfPageEventHelper {

        Date fecha;
        String iniResp;

        public FooterPageEvent(Date fecha, String iniResp) {
            this.fecha = fecha;
            this.iniResp = iniResp;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("En " + DateUtil.getFechaCiudad(fecha), TNR_12);            
            Phrase iniciales = new Phrase(iniResp, TNR_12);

            // Posición X: a la derecha, considerando margen derecho
            float x = document.right();
            // Posición Y: 30 puntos desde el borde inferior
            float y = document.bottom() + 40;

            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer, x, y, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, iniciales, x, y-20, 0);
        }
    }
}
