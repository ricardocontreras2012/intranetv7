/*
 * @(#)AlumnoCertificacionEmitirLogroEnTramiteService.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package service.alumno;

import com.lowagie.text.*;
import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_JUSTIFIED;
import static com.lowagie.text.Element.ALIGN_LEFT;
import static com.lowagie.text.Element.ALIGN_RIGHT;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.Font.UNDERLINE;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import static com.lowagie.text.PageSize.LETTER;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Alumno;
import domain.model.Tramite;
import domain.model.Unidad;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.BarCodeUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import static infrastructure.util.common.CommonCertificacionUtil.newParrafo;
import infrastructure.util.common.CommonSimpleMessageUtil;
import static org.apache.struts2.ServletActionContext.getServletContext;
import static java.security.Security.addProvider;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;

/**
 *
 * @author Ricardo Contreras S.
 */
public final class AlumnoCertificacionEmitirLogroEnTramiteService {

    /**
     * Method description
     *
     *
     *
     * @param correl
     * @return
     */
    public ActionInputStreamUtil service(Integer correl){

        Map<String, String> mapParams = CommonCertificacionUtil.getParams(correl);
        
        Integer tramite = Integer.parseInt(mapParams.get("tramite"));
        Integer tCert = Integer.parseInt(mapParams.get("tcert"));
        String obs = mapParams.get("glosa");
        Integer folio = Integer.parseInt(mapParams.get("folio"));
        String name = mapParams.get("archivo");
        Integer genera = Integer.parseInt(mapParams.get("genera"));
        String type = mapParams.get("type");
        String session = mapParams.get("session");
        String desPrint = mapParams.get("desPrint");
        String logro = mapParams.get("logro");
        Integer certCorrel = Integer.parseInt(mapParams.get("certCorrel"));
        Integer monto = Integer.parseInt(mapParams.get("monto"));

        String description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(correl, tCert, genera, type, session, tramite, obs, folio, name, desPrint, logro, certCorrel, CommonCertificacionUtil.getPagoString(monto)));
    }

    public static InputStream getInput(Integer correl, Integer tCert, Integer genera, String type, String session, Integer codTramite, String obs,
            Integer folio, String name, String desPrint, String logro, Integer certCorrel, String pagado) {

        try {
            AluCar aluCar = CommonCertificacionUtil.getAluCar(correl);
            AluCarId id = aluCar.getId();
            Alumno alumno = aluCar.getAlumno();
            String fechaAdm;
            Unidad facultad = aluCar.getUnidadFacultad();
            String urlWeb = facultad.getUniUrl();
            Date fecha = DateUtil.getSysdate();
            String fechaString =  DateUtil.getFechaCiudad(fecha);

            if (fecha != null) {
                Tramite tramite = ContextUtil.getTramiteMap().get(codTramite);

                if (tramite != null) {
                    String verificador = CommonCertificacionUtil.getVerificador(folio);

                    fechaAdm = DateUtil.getFechaEnPalabras(ContextUtil.getDAO().getExpedienteLogroPersistence(type).getFechaTramite(id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(), id.getAcaSemIng(), certCorrel));

                    String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());

                    String certifico = "Certifico que " + prefijoAlumno
                            + alumno.getNombre()
                            + ", Cédula Nacional de Identidad N° "
                            + alumno.getAluRut() + '-'
                            + alumno.getAluDv() + ", dió término a los requisitos administrativos "
                            + desPrint + " con fecha " + fechaAdm + ".";

                    String tram = "En la actualidad, el " + logro.toLowerCase() + " respectivo se encuentra en su proceso de tramitación administrativa.";
                    String extiende = "Se extiende el presente certificado para ser presentado en: Trámite "
                            + tramite.getTraPrefijo() + ' ' + tramite.getTraDescripcion() + ((obs != null) ? "\n" + obs : "");

                    CommonCertificacionUtil.registraLog(aluCar, folio, verificador, fecha, tCert, aluCar.getParametros().getAgnoAct(), aluCar.getParametros().getSemAct(), codTramite, certifico, obs, genera, type);

                    Document document = new Document(LETTER);

                    document.setMargins(100.0f, 100.0f, 50.0f, 50.0f);
                    // /// FONTS
                    String path = getServletContext().getRealPath("/fonts/local/tahoma.ttf");
                    register(path, "tahoma_font");
                    Font normal = getFont("tahoma_font", 10.0f, NORMAL);
                    Font titulo = getFont("tahoma_font", 12.0f, NORMAL);
                    Font subrayado = getFont("tahoma_font", 12.0f, UNDERLINE);
                    Font big = getFont("tahoma_font", 18.0f, NORMAL);

                    // //
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    PdfWriter writer = PdfWriter.getInstance(document, buffer);
                    CommonCertificacionUtil.BackgroundImage bi = new CommonCertificacionUtil.BackgroundImage("cert", facultad.getUniCod());
                    writer.setPageEvent(bi);
                    addProvider(new BouncyCastleProvider());

                    document.open();
                    putHeader(document, titulo, normal, subrayado, big, folio, verificador, aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()));
                    putBody(document, normal, certifico, tram + "\n" + extiende, fechaString, urlWeb);
                    BarCodeUtil.putBarCode(writer.getDirectContent(), folio, verificador, 0, pagado);
                    document.close();

                    CommonCertificacionUtil.writeFile(buffer, SystemParametersUtil.PATH_CERT + name);
                    buffer.close();
                    ////

                    Files.createSymbolicLink(Paths.get(PATH_ATTACH_MESSAGES + name), Paths.get(PATH_CERT + name));
                    
                    CommonSimpleMessageUtil.send(name, session, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genera, "Registrador Curricular", "RC","",
                            "", "C4", "CERTIFICADO DE " + logro.toUpperCase() + " EN TRAMITE", "TM_CERT");

                    // Ojo por ahora 1 pero despues puede ser el que correponda al carrito
                    ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).setEstadoCarrito(correl, 1, "EM");
                    LogUtil.setLog(genera, alumno.getAluRut());

                    return CommonArchivoUtil.getFile(name, "cert");
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
    private static void putHeader(Document document, Font titulo, Font normal, Font subrayado, Font negrita,
            Integer folio, String codigo, String facultad)
            {

        Paragraph parrafo1 = newParrafo(149, 80);
        parrafo1.setAlignment(ALIGN_LEFT);
        parrafo1.add(new Chunk("REPÚBLICA DE CHILE", titulo));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(0, 10);
        parrafo2.setAlignment(ALIGN_CENTER);
        parrafo2.add(new Chunk("UNIVERSIDAD DE SANTIAGO DE CHILE", titulo));
        document.add(parrafo2);

        Paragraph parrafo3 = newParrafo(0, 10);
        parrafo3.setAlignment(ALIGN_CENTER);
        parrafo3.add(new Chunk(facultad, subrayado));
        document.add(parrafo3);

        Paragraph parrafo4 = newParrafo(0, 10);
        parrafo4.setAlignment(ALIGN_CENTER);
        parrafo4.add(new Chunk("REGISTRO CURRICULAR", titulo));
        document.add(parrafo4);

        Paragraph parrafo5 = newParrafo(146, 50);
        parrafo5.setAlignment(ALIGN_LEFT);
        parrafo5.add(new Chunk("CERTIFICADO", negrita));
        document.add(parrafo5);

        Paragraph parrafo6 = newParrafo(150, 10);
        parrafo6.setAlignment(ALIGN_RIGHT);
        parrafo6.add(new Chunk("FOLIO N° " + folio, normal));
        document.add(parrafo6);

        Paragraph parrafo7 = newParrafo(120, 5);
        parrafo7.setAlignment(ALIGN_RIGHT);
        parrafo7.add(new Chunk("CODIGO DE VERIFICACION: " + codigo, normal));
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
    private static void putBody(Document document, Font normal, String certifico, String extiende, String fecha, String web)
            {
        Paragraph parrafo1 = newParrafo(0, 40);
        parrafo1.setAlignment(ALIGN_JUSTIFIED);
        parrafo1.add(new Chunk(certifico, normal));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(0, 30);
        parrafo2.setAlignment(ALIGN_JUSTIFIED);
        parrafo2.add(new Chunk(extiende, normal));
        document.add(parrafo2);

        Paragraph parrafo3 = newParrafo(0, 50);
        parrafo3.setAlignment(ALIGN_LEFT);
        parrafo3.add(new Chunk("La autenticidad de este certificado podrá ser verificada en " + web,
                normal));
        document.add(parrafo3);

        Paragraph parrafo4 = newParrafo(0, 100);

        parrafo4.setAlignment(ALIGN_LEFT);
        parrafo4.add(new Chunk(fecha, normal));
        document.add(parrafo4);
    }
}
