/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.certificacion.alumno;

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
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import static infrastructure.util.common.CommonCertificacionUtil.newParrafo;
import infrastructure.util.common.CommonSimpleMessageUtil;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionEmitirRankRegularService {
public ActionInputStreamUtil service(Integer correl){

       Map<String, String> mapParams = CommonCertificacionUtil.getParams(correl);

        Integer tramite = Integer.parseInt(mapParams.get("tramite"));
        String ranking = mapParams.get("ranking");
        String obs = mapParams.get("glosa");
        Integer folio = Integer.parseInt(mapParams.get("folio"));
        String name = mapParams.get("archivo");
        Integer genera = Integer.parseInt(mapParams.get("genera"));
        String type = mapParams.get("type");
        String session = mapParams.get("session");
        String description = FormatUtil.getMimeType(name);
        Integer monto = Integer.parseInt(mapParams.get("monto"));

        return new ActionInputStreamUtil(name, description, getInput(correl, tramite, obs, ranking, folio, name, genera, type, CommonCertificacionUtil.getPagoString(monto), session));      
    }

    private InputStream getInput(
            Integer correl, Integer codTramite, String obs, String ranking, Integer folio, String name, Integer genera, String type, String pagado, String key) {

        try {
            AluCar aluCar = CommonCertificacionUtil.getAluCar(correl);
            Alumno alumno = aluCar.getAlumno();
            String glosaLog;
            Unidad facultad = aluCar.getUnidadFacultad();
            String urlWeb = facultad.getUniUrl();
            Date fecha = getSysdate();
            String fechaString =  DateUtil.getFechaCiudad(fecha);

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
                            + alumno.getAluDv() +", "
                            + prefijoCarrera + " de "
                            + aluCar.getNombreCarrera() + ", tiene el siguiente ranking de promoción.\n\n\n"+ranking;

                    String extiende = "";

                    extiende += "Se extiende el presente certificado para ser presentado en: Trámite "
                            + tramite.getTraPrefijo() + ' ' + tramite.getTraDescripcion() + ((obs != null) ? "\n" + obs : "");

                    glosaLog = ranking;

                    
                    CommonCertificacionUtil.registraLog(aluCar, folio, verificador, fecha, SystemParametersUtil.C7, null, null, codTramite, glosaLog, obs, genera, type);

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
                    
                    Files.createSymbolicLink(Paths.get(PATH_ATTACH_MESSAGES + name), Paths.get(PATH_CERT + name));

                    CommonSimpleMessageUtil.send(name, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genera, "Registrador Curricular", "RC","",
                            "", "C7", "CERTIFICADO DE RANKING ALUMNO REGULAR", "TM_CERT");

                    // Ojo por ahora 1 pero despues puede ser el que correponda al carrito
                    ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).setEstadoCarrito(correl, 1, "EM");
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
    private void putHeader(Document document, Font titulo, Font normal, Font subrayado, Font negrita,
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
    private void putBody(Document document, Font normal, String certifico, String extiende, String fecha, String web)
            {
        Paragraph parrafo1 = newParrafo(0, 40);
        parrafo1.setAlignment(ALIGN_JUSTIFIED);
        parrafo1.add(new Chunk(certifico, normal));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(0, 20);
        parrafo2.setAlignment(ALIGN_JUSTIFIED);
        parrafo2.add(new Chunk(extiende, normal));
        document.add(parrafo2);

        Paragraph parrafo3 = newParrafo(0, 50);
        parrafo3.setAlignment(ALIGN_LEFT);
        parrafo3.add(new Chunk("La autenticidad de este certificado podrá ser verificada en " + web,
                normal));
        document.add(parrafo3);

        Paragraph parrafo4 = newParrafo(0, 80);
        parrafo4.setAlignment(ALIGN_LEFT);
        parrafo4.add(new Chunk(fecha, normal));
        document.add(parrafo4);
    }    

}
