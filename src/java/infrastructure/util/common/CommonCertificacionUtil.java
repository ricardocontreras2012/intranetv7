/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.AluCar;
import domain.model.LogCertificacion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import domain.repository.LogCertificacionRepository;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import static infrastructure.util.SystemParametersUtil.CERTIFICATE_BACKGROUND_IMAGE_PATH;
import static infrastructure.util.SystemParametersUtil.CONTANCIA_BACKGROUND_IMAGE_PATH;
import domain.model.CertificacionView;
import java.util.HashMap;

/**
 *
 * @author rcontreras
 */
public class CommonCertificacionUtil {

    private static final Map<Integer, String> PREFIJOS_MAP = new HashMap<>();

    static {
        PREFIJOS_MAP.put(1, "Certificado_Alumno_Regular_");
        PREFIJOS_MAP.put(2, "No_impedimento_");
        PREFIJOS_MAP.put(3, "Certificado_Alumno_Egresado_");
        PREFIJOS_MAP.put(4, "Titulo_en_Tramite_");
        PREFIJOS_MAP.put(5, "Grado_en_Tramite_");
        PREFIJOS_MAP.put(7, "Informe_de_Calificaciones_I3_");
        PREFIJOS_MAP.put(9, "Informe_de_Calificaciones_I4_");
        PREFIJOS_MAP.put(11, "Ranking_egresado_");
        PREFIJOS_MAP.put(14, "Ranking_regular_");
        PREFIJOS_MAP.put(21, "Informe_de_Calificaciones_I7_");
        PREFIJOS_MAP.put(30, "Postitulo_en_Tramite_");
        PREFIJOS_MAP.put(32, "Diplomado_en_Tramite_");
        PREFIJOS_MAP.put(40, "Certificado_Logro_");
        PREFIJOS_MAP.put(50, "Informe_de_Convalidaciones_");
        PREFIJOS_MAP.put(60, "ISO_27001_");
    }

    public static Integer getFolio() {
        return ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getSecuenciaCertificado();
    }

    public static String getVerificador(Integer folio) {
        return ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getVerificadorCertificado(folio);
    }

    public static String getProfijoAlumon(String sexo) {
        return "1".equals(sexo) ? "la alumna " : "el alumno ";
    }

    public static String getPrefijoCarrera(String flag) {
        return "S".equals(flag) ? "de la carrera" : "del programa";
    }

    public static String getNameFile(AluCar aluCar, Integer folio, Integer tipo) {
        return getPrefijoArchivo(tipo) + aluCar.getAlumno().getAluRut() + "-" + aluCar.getAlumno().getAluDv() + "_" + folio + ".pdf";
    }

    public static String getPrefijoArchivo(Integer tipo) {
        return PREFIJOS_MAP.getOrDefault(tipo, "");
    }

    /**
     * Method description
     *
     * @param aluCar
     * @param folio
     * @param verificador
     * @param sysDate
     * @param obs
     * @param agno
     * @param sem
     * @param tramite
     * @param glosa
     * @param emite
     * @param tipoCertificado
     * @param userType
     */
    public static void registraLog(AluCar aluCar, Integer folio, String verificador, Date sysDate, Integer tipoCertificado, Integer agno, Integer sem, Integer tramite, String glosa, String obs, Integer emite, String userType) {
        LogCertificacion log = new LogCertificacion();
        log.setLcertFolio(folio);
        log.setLcertRut(aluCar.getId().getAcaRut());
        log.setLcertCodCar(aluCar.getId().getAcaCodCar());
        log.setLcertAgnoIng(aluCar.getId().getAcaAgnoIng());
        log.setLcertSemIng(aluCar.getId().getAcaSemIng());
        log.setLcertMencion(aluCar.getAcaCodMen());
        log.setLcertSem(sem);
        log.setLcertAgno(agno);
        log.setLcertUser(userType);
        log.setLcertRutEmite(emite);
        log.setLcertTcertificado(tipoCertificado);
        log.setLcertTramite(tramite);
        log.setLcertFecha(sysDate);
        log.setLcertGlosa(glosa);
        log.setLcertObs(obs);
        log.setLcertSem(aluCar.getParametros().getSemAct());
        log.setLcertAgno(aluCar.getParametros().getAgnoAct());
        log.setLcertVerificador(verificador);

        LogCertificacionRepository logRepository = ContextUtil.getDAO().getLogCertificacionRepository(ActionUtil.getDBUser());
        beginTransaction(ActionUtil.getDBUser());
        logRepository.save(log);
        commitTransaction();
    }

    public static class BackgroundImage extends PdfPageEventHelper {

        private String fondo;

        public BackgroundImage(String fondo) {
            this.fondo = fondo;
        }

        public BackgroundImage(String tipo, Integer facultad) {
            switch (tipo) {
                case "cert":
                    this.fondo = CERTIFICATE_BACKGROUND_IMAGE_PATH.replace("###", String.valueOf(facultad));
                    break;
                case "const":
                    this.fondo = CONTANCIA_BACKGROUND_IMAGE_PATH.replace("###", String.valueOf(facultad));
            }
        }

        /**
         * Method description
         *
         *
         * @param writer
         * @param document
         */
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Image image = getInstance(getServletContext().getRealPath(fondo));

                image.setAbsolutePosition(50f, 65f);
                image.scaleAbsolute(512.0f, 692.0f);
                document.add(image);
            } catch (DocumentException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void writeFile(ByteArrayOutputStream buffer, String file) throws Exception {
        byte[] bufferByte = buffer.toByteArray();

        try (InputStream pdfStream = new ByteArrayInputStream(bufferByte);
                OutputStream outStream = new FileOutputStream(file)) {

            byte[] temp = new byte[1024];
            int bytesRead;
            while ((bytesRead = pdfStream.read(temp)) != -1) {
                outStream.write(temp, 0, bytesRead);
            }
        }

        new File(file).setReadable(true, false);
    }

    public static Paragraph newParrafo(int posX, int spaceBefore) {
        Paragraph parrafo = new Paragraph();

        parrafo.setIndentationLeft(posX);
        parrafo.setFirstLineIndent(0F);
        parrafo.setSpacingBefore(spaceBefore);

        return parrafo;
    }

    public static Paragraph newParrafo(int posX, int spaceBefore, Font font) {
        Paragraph parrafo = new Paragraph("", font);

        parrafo.setIndentationLeft(posX);
        parrafo.setFirstLineIndent(0F);
        parrafo.setSpacingBefore(spaceBefore);

        return parrafo;
    }

    public static Integer getValor(List<CertificacionView> lista, Integer tcert) {
        //Iterator< CertificacionView> it = lista.iterator();
        Integer valor = 0;

        /// OJO
        /*while (it.hasNext()) {
            CertificacionView cv = it.next();
            if (cv.getCertTipo() == tcert) {
                valor = cv.getCertValor();
            }
        }*/
        return valor;
    }

    public static AluCar getAluCar(Integer correl) {
        AluCar aluCar = ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).getAluCarCertificado(correl);
        aluCar = ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).find(aluCar.getId());
        aluCar.setInitValues();

        return aluCar;

    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getMap(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Map.class);
    }

    public static Type getTypeToken() {
        return new TypeToken<Map<String, Object>>() {
        }.getType();
    }

    public static String getRetCertificado(Integer monto, String obs) {
        return monto > 0 ? "PA" : (obs.isEmpty() ? "SG" : "CG");
    }

    public static String getPagoString(Integer monto) {
        /// OJO
        //return (monto>0)?"Certificado Pagado":"Certificado Gratuito";
        return "";
    }

    public static Map<String, String> getParams(Integer correl) {

        Object[] obj = ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).getCertificado(correl);

        String params = (String) obj[2];
        params = params.substring(1, params.length() - 1);

        return getMap(params);
    }

    public static String getSemestre(int regimen, int semestre) {
        String semOrd;
        switch (semestre) {
            case 1:
                semOrd = "Primer";
                break;
            case 2:
                semOrd = "Segundo";
                break;
            case 3:
                semOrd = "Tercer";
                break;
            default:
                semOrd = "";
                break;
        }

        String nombreRegimen = (regimen == 2) ? " Semestre" : " Trimestre";

        return semOrd + nombreRegimen;
    }
}
