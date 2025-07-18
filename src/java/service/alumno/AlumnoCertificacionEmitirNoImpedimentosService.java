/*
 * @(#)AlumnoCertificacionEmitirAlumnoRegularService.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package service.alumno;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_JUSTIFIED;
import static com.lowagie.text.Element.ALIGN_LEFT;
import static com.lowagie.text.Element.ALIGN_RIGHT;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.Font.UNDERLINE;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.Paragraph;
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
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import static infrastructure.util.BarCodeUtil.putBarCode;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.SystemParametersUtil.C2;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import static infrastructure.util.common.CommonCertificacionUtil.newParrafo;
import infrastructure.util.common.CommonSimpleMessageUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public final class AlumnoCertificacionEmitirNoImpedimentosService {

    /**
     * Method description
     *
     *
     * @param correl
     *
     * @return
     */
    public ActionInputStreamUtil service(Integer correl) {

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
        String description = FormatUtil.getMimeType(name);
        Integer monto = Integer.parseInt(mapParams.get("monto"));

        return new ActionInputStreamUtil(name, description, getInput(correl, genera, type, session, tramite, agno, sem, obs, folio, name, CommonCertificacionUtil.getPagoString(monto)));
    }

    private InputStream getInput(Integer correl, Integer genera, String type, String session, Integer codTramite, Integer agnoCert, Integer semCert, String obs,
            Integer folio, String name, String pagado) {

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
                        String prefijoCarrera
                                = CommonCertificacionUtil.getPrefijoCarrera(aluCar.getPlan().getMencion().getCarrera().getTprograma().getTprFlagCarrera());

                        String certifico = "Certifico que " + prefijoAlumno
                                + alumno.getNombre()
                                + ", Cédula Nacional de Identidad N° "
                                + alumno.getAluRut() + '-'
                                + alumno.getAluDv() + ", ingresó " + prefijoCarrera + " de "
                                + aluCar.getNombreCarrera() + " el "
                                + CommonCertificacionUtil.getSemestre(aluCar.getPlan().getRegimen().getRegCod(),
                                        aluCar.getId().getAcaSemIng()) + " año "
                                + aluCar.getId().getAcaAgnoIng() + " y " + esFue + " "
                                + prefijoAlumno.substring(3)
                                + "regular " + (("es".equals(esFue)) ? " del " : " hasta el ")
                                + CommonCertificacionUtil.getSemestre(aluCar.getPlan().getRegimen().getRegCod(),
                                        semCert) + " año " + agnoCert + '.' + "\n\nDe acuerdo a las disposiciones reglamentarias vigentes, no tiene "
                                + "impedimentos académicos ni disciplinarios para continuar estudios en esta Corporación.";

                        String extiende = "Se extiende el presente certificado para ser presentado en: Trámite "
                                + tramite.getTraPrefijo() + ' ' + tramite.getTraDescripcion() + ((obs != null) ? "\n" + obs : "");

                        glosaLog = FormatUtil.initCapital(esFue) + " " + ("1".equals(aluCar.getAlumno().getAluSexo())
                                ? "alumna"
                                : "alumno") + " regular el " + semCert + '/' + agnoCert;

                        CommonCertificacionUtil.registraLog(aluCar, folio, verificador, fecha, C2, agnoCert, semCert, codTramite, glosaLog, obs, genera, type);

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
                        PdfWriter writer = getInstance(document, buffer);
                        CommonCertificacionUtil.BackgroundImage bi = new CommonCertificacionUtil.BackgroundImage("cert", facultad.getUniCod());
                        writer.setPageEvent(bi);
                        addProvider(new BouncyCastleProvider());

                        document.open();
                        putHeader(document, titulo, normal, subrayado, big, folio, verificador, aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()));
                        putBody(document, normal, certifico, extiende, fechaString, urlWeb);
                        putBarCode(writer.getDirectContent(), folio, verificador, 0, pagado);
                        document.close();

                        CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);
                        buffer.close();
                        //// 

                        Files.createSymbolicLink(Paths.get(PATH_ATTACH_MESSAGES + name), Paths.get(PATH_CERT + name));

                        CommonSimpleMessageUtil.send(name, session, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genera, "Registrador Curricular", "RC", "",
                                "", "C2", "CERTIFICADO NO IMPEDIMENTO ACADÉMICO", "TM_CERT");

                        // Ojo por ahora 1 pero despues puede ser el que correponda al carrito
                        ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).setEstadoCarrito(correl, 1, "EM");
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
    private void putHeader(Document document, Font titulo, Font normal, Font subrayado, Font negrita,
            Integer folio, String codigo, String facultad) {

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
    private void putBody(Document document, Font normal, String certifico, String extiende, String fecha, String web) {
        Paragraph parrafo1 = newParrafo(0, 40);

        parrafo1.setAlignment(ALIGN_JUSTIFIED);
        parrafo1.add(new Chunk(certifico, normal));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(0, 30);
        parrafo2.setAlignment(ALIGN_JUSTIFIED);
        parrafo2.add(new Chunk(extiende, normal));
        document.add(parrafo2);

        Paragraph parrafo3 = newParrafo(0, 30);
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
