/*
 * @(#)AlumnoEmitirAlumnoRegularService.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package service.certificacion.alumno;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_JUSTIFIED;
import static com.lowagie.text.Element.ALIGN_LEFT;
import static com.lowagie.text.Element.ALIGN_RIGHT;
import com.lowagie.text.Font;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfArray;
import com.lowagie.text.pdf.PdfBoolean;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfIndirectObject;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfStream;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.Tramite;
import domain.model.Unidad;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.security.Security.addProvider;
import java.util.Date;
import java.util.Map;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import static infrastructure.util.BarCodeUtil.putBarCode;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FontsPDFUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import static infrastructure.util.SystemParametersUtil.C1;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import static infrastructure.util.common.CommonCertificacionUtil.newParrafo;
import infrastructure.util.common.CommonSimpleMessageUtil;
import java.awt.color.ICC_Profile;
import java.nio.charset.StandardCharsets;
import static org.apache.struts2.ServletActionContext.getServletContext;


/**
 *
 * @author Ricardo Contreras S.
 */
public final class AlumnoEmitirAlumnoRegularService {

    FontsPDFUtil fontsUtil = (FontsPDFUtil) getServletContext().getAttribute("fontsUtil");

    Font TA_10 = fontsUtil.crearFont("tahoma", 10, Font.NORMAL);
    Font TA_12 = fontsUtil.crearFont("tahoma", 12, Font.NORMAL);
    Font TA_12U = fontsUtil.crearFont("tahoma", 12, Font.UNDERLINE);
    Font TA_18 = fontsUtil.crearFont("tahoma", 18, Font.NORMAL);

    /**
     * Method description
     *
     *
     * @param correl
     *
     * @return
     */
    public ActionInputStreamUtil service(Integer correl) {

        try {

            Map<String, String> mapParams = CommonCertificacionUtil.getParams(correl);

            Integer tramite = Integer.parseInt(mapParams.get("tramite"));
            Integer agno = Integer.parseInt(mapParams.get("agno"));
            Integer sem = Integer.parseInt(mapParams.get("sem"));
            String obs = mapParams.get("glosa");
            Integer folio = Integer.parseInt(mapParams.get("folio"));
            String name = mapParams.get("archivo");
            Integer genera = Integer.parseInt(mapParams.get("genera"));
            String type = mapParams.get("type");
            String session = mapParams.get("session");
            Integer monto = Integer.parseInt(mapParams.get("monto"));
            String description = FormatUtil.getMimeType(name);

            return new ActionInputStreamUtil(name, description, getInput(correl, genera, type, session, tramite, agno, sem, obs, folio, name, CommonCertificacionUtil.getPagoString(monto)));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream getInput(Integer correl, Integer genera, String type, String session, Integer codTramite, Integer agnoCert, Integer semCert, String obs, Integer folio, String name, String pagado) {

        try {
            AluCar aluCar = CommonCertificacionUtil.getAluCar(correl);
            Alumno alumno = aluCar.getAlumno();
            String glosaLog;

            Integer agnoMat = aluCar.getParametros().getAgnoMat();
            Integer semMat = aluCar.getParametros().getSemMat();

            String esFue = (agnoMat * 10 + semMat > agnoCert * 10 + semCert) ? "fue" : "es";

            if (aluCar.estaMatriculado(agnoCert, semCert)) {
                Unidad facultad = aluCar.getUnidadFacultad();
                String urlWeb = facultad.getUniUrl();
                Date fecha = getSysdate();
                String fechaString = DateUtil.getFechaCiudad(fecha);

                if (fecha != null) {
                    Tramite tramite = ContextUtil.getTramiteMap().get(codTramite);

                    if (tramite != null) {
                        String verificador = CommonCertificacionUtil.getVerificador(folio);

                        String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());
                        String prefijoCarrera = CommonCertificacionUtil.getPrefijoCarrera(aluCar.getPlan().getMencion().getCarrera().getTprograma().getTprFlagCarrera());

                        String certifico = "Certifico que " + prefijoAlumno
                                + alumno.getNombre()
                                + ", Cédula Nacional de Identidad N° "
                                + alumno.getAluRut() + '-'
                                + alumno.getAluDv() + ", ingresó " + prefijoCarrera + " de "
                                + aluCar.getNombreCarrera() + " el "
                                + CommonCertificacionUtil.getSemestre(aluCar.getPlan().getRegimen().getRegCod(),
                                        aluCar.getId().getAcaSemIng()) + " año "
                                + aluCar.getId().getAcaAgnoIng() + " y " + esFue + " "
                                + prefijoAlumno.substring(3, prefijoAlumno.length())
                                + "regular del "
                                + CommonCertificacionUtil.getSemestre(aluCar.getPlan().getRegimen().getRegCod(),
                                        semCert) + " año " + agnoCert + '.';
                        String extiende = "Se extiende el presente certificado para ser presentado en: Trámite "
                                + tramite.getTraPrefijo() + ' ' + tramite.getTraDescripcion() + ((obs != null) ? "\n" + obs : "");

                        glosaLog = FormatUtil.initCapital(esFue) + " " + ("1".equals(aluCar.getAlumno().getAluSexo())
                                ? "alumna"
                                : "alumno") + " regular el " + semCert + '/' + agnoCert;

                        CommonCertificacionUtil.registraLog(aluCar, folio, verificador, fecha, C1, agnoCert, semCert, codTramite, glosaLog, obs, genera, type);

                        Document document = new Document(LETTER);

                        document.setMargins(100.0f, 100.0f, 50.0f, 50.0f);

                        // //
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        PdfWriter writer = getInstance(document, buffer);

                        /// REQUISITOS PDF-A //////////////////////////////////////////////
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
                        writer.getInfo().put(PdfName.TITLE, new PdfString("CERTIFICADO DE ALUMNO REGULAR"));
                        writer.getInfo().put(PdfName.CREATOR, new PdfString("FAE-USACH"));
                        writer.getInfo().put(PdfName.AUTHOR, new PdfString("FAE-USACH"));
                        writer.getInfo().put(PdfName.PRODUCER, new PdfString("OpenPDF"));
                        ////////////////////////////// FIN REQUISITOS PDF-A /////////////////

                        CommonCertificacionUtil.BackgroundImage bi = new CommonCertificacionUtil.BackgroundImage("cert", facultad.getUniCod());
                        writer.setPageEvent(bi);
                        addProvider(new BouncyCastleProvider());

                        document.open();

                        /// REQUISITOS PDF-A //////////////////////////////////////////////
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
                        String xmpMetadata = PdfUtil.createValidXMPMetadata("CERTIFICADO DE ALUMNO REGULAR", alumno.getNombreStd());
                        writer.setXmpMetadata(xmpMetadata.getBytes(StandardCharsets.UTF_8));
                        ////////////////////////////// FIN REQUISITOS PDF-A /////////////////                        

                        putHeader(document, TA_12, TA_10, TA_12U, TA_18, folio, verificador, aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()));
                        putBody(document, TA_10, certifico, extiende, fechaString, urlWeb);
                        putBarCode(writer.getDirectContent(), folio, verificador, 0, pagado);
                        document.close();

                        ///// CON FIRMA DIGITAL
                        //CommonCertificacionUtil.writeFile(buffer, PATH_TEMP_FILES + "tmp_"+name);
                        //buffer.close();
                        //SignUtil.sign(PATH_TEMP_FILES + "tmp_"+name, PATH_CERT + name, folio, alumno.getAluRut()+"-"+alumno.getAluDv(), fecha );
                        /////
                        //// SIN FIRMA DIGITAL
                        CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);
                        buffer.close();
                        //// 

                        Files.createSymbolicLink(Paths.get(PATH_ATTACH_MESSAGES + name), Paths.get(PATH_CERT + name));

                        CommonSimpleMessageUtil.send(name, session, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genera, "Registrador Curricular", "RC", "",
                                "", "C1", "CERTIFICADO DE ALUMNO REGULAR", "TM_CERT");

                        // Ojo por ahora 1 pero despues puede ser el que correponda al carrito
                        ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).setEstadoCarrito(correl, 1, "EM");
                        LogUtil.setLog(genera, alumno.getAluRut());

                        return CommonArchivoUtil.getFile(name, "cert");
                    }
                }
            }
        } catch (Throwable e) { //NO BORRAR
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method description
     *
     *
     * @param document
     * @param titulo
     * @param normal
     * @param subrayado
     * @param negrita
     * @param folio
     * @param codigo
     *
     * @throws Exception
     */
    private void putHeader(Document document, Font titulo, Font normal, Font subrayado, Font negrita, Integer folio, String codigo, String facultad) throws Exception {

        Paragraph parrafo1 = newParrafo(149, 80, titulo );
        parrafo1.setAlignment(ALIGN_LEFT);
        parrafo1.add(new Chunk("REPÚBLICA DE CHILE"));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(0, 10, titulo);
        parrafo2.setAlignment(ALIGN_CENTER);
        parrafo2.add(new Chunk("UNIVERSIDAD DE SANTIAGO DE CHILE"));
        document.add(parrafo2);

        Paragraph parrafo3 = newParrafo(0, 10, subrayado);
        parrafo3.setAlignment(ALIGN_CENTER);
        parrafo3.add(new Chunk(facultad));
        document.add(parrafo3);

        Paragraph parrafo4 = newParrafo(0, 10, titulo);
        parrafo4.setAlignment(ALIGN_CENTER);
        parrafo4.add(new Chunk("REGISTRO CURRICULAR"));
        document.add(parrafo4);

        Paragraph parrafo5 = newParrafo(146, 50, negrita);
        //Paragraph parrafo5 = newParrafo(146, 60);
        parrafo5.setAlignment(ALIGN_LEFT);
        parrafo5.add(new Chunk("CERTIFICADO"));
        document.add(parrafo5);

        Paragraph parrafo6 = newParrafo(150, 10, normal);
        parrafo6.setAlignment(ALIGN_RIGHT);
        parrafo6.add(new Chunk("FOLIO N° " + folio));
        document.add(parrafo6);

        Paragraph parrafo7 = newParrafo(120, 5, normal);
        parrafo7.setAlignment(ALIGN_RIGHT);
        parrafo7.add(new Chunk("CODIGO DE VERIFICACION: " + codigo));
        document.add(parrafo7);
    }

    /**
     * Method description
     *
     *
     * @param document
     * @param normal
     * @param certifico
     * @param extiende
     * @param fecha
     *
     * @throws Exception
     */
    private void putBody(Document document, Font normal, String certifico, String extiende, String fecha, String web)
            throws Exception {
        Paragraph parrafo1 = newParrafo(0, 40, normal);

        parrafo1.setAlignment(ALIGN_JUSTIFIED);
        parrafo1.add(new Chunk(certifico));
        document.add(parrafo1);

        //Paragraph parrafo2 = newParrafo(0, 40);
        Paragraph parrafo2 = newParrafo(0, 30, normal);
        parrafo2.setAlignment(ALIGN_JUSTIFIED);
        parrafo2.add(new Chunk(extiende));
        document.add(parrafo2);

        //Paragraph parrafo3 = newParrafo(0, 60);
        Paragraph parrafo3 = newParrafo(0, 50, normal);
        parrafo3.setAlignment(ALIGN_LEFT);
        parrafo3.add(new Chunk("La autenticidad de este certificado podrá ser verificada en " + web));
        document.add(parrafo3);

        Paragraph parrafo4 = newParrafo(0, 100, normal);

        parrafo4.setAlignment(ALIGN_LEFT);
        parrafo4.add(new Chunk(fecha));
        document.add(parrafo4);
    }    
}
