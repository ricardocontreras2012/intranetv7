/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.Solicitud;
import infrastructure.support.SolicitudSupport;
import java.io.InputStream;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.util.Arrays.asList;
import java.util.Date;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getFechaCiudad;
import static infrastructure.util.DateUtil.getFormatedDate;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import infrastructure.util.HibernateUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonArchivoUtil;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonSimpleMessageUtil;
import infrastructure.util.common.CommonUtil;
import infrastructure.util.common.CommonConstanciaUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rcontreras
 */
public class AlumnoSolicitudRenunciaConstanciaService {

    public static ActionInputStreamUtil service(GenericSession genericSession, AlumnoSession sesionAlumno, String motivo, String key) throws Exception {
        String name;
        InputStream input;
        String description;

        Integer folio = CommonCertificacionUtil.getFolio();
        name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, 94);
        input = getInput(genericSession, sesionAlumno, folio, motivo, key);
        description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }

    /**
     *
     * @param genericSession
     * @param report
     * @param motivo
     * @param key
     * @return
     * @throws java.lang.Exception
     */
    private static InputStream getInput(GenericSession genericSession, AlumnoSession sesionAlumno, Integer folio, String motivo, String key) throws Exception {
    
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        Solicitud solicitud = ws.getSolicitud();                
        Integer solFolio = ws.getSolicitud().getSolFolio();
        solicitud.setSolMotivo(StringUtils.abbreviate(motivo, 2000));
        new SolicitudSupport(solicitud).setAprobada();
        
        String user = ActionUtil.getDBUser();
        
        HibernateUtil.beginTransaction(user);
        CommonUtil.setAgnoSemAct(ws);
        ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser()).save(solicitud);
        ContextUtil.getDAO().getSacarreraPersistence(user).renuncia(aluCar.getId(), ws.getAgnoAct(), ws.getSemAct(), motivo, solFolio);
        HibernateUtil.commitTransaction();

        String verificador = CommonCertificacionUtil.getVerificador(folio);

        Date fecha = getSysdate();
        String fechaString = getFechaCiudad(fecha);
        String glosaPrincipal;
        String glosaFinal = getGlosaFinal(aluCar);

        glosaPrincipal = getGlosaPrincipal(aluCar, ws.getAgnoAct(), ws.getSemAct(), fecha);

        String file = "Constancia_Renuncia_" + aluCar.getId().getAcaRut() + "_" + solicitud
                + ".pdf";

        String fileTmp = getAttachFileName(file, "_" + 0, folio);
        String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + ws.getAgnoAct() + "-" + format("%05d", solFolio) + ".pdf";

        CommonConstanciaUtil.writePdf(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp, solFolio, verificador, aluCar, glosaPrincipal, glosaFinal, fechaString);
        Files.copy(Paths.get(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);

        CommonSimpleMessageUtil.send(fileTmp, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Registrador Curricular", genericSession.getUserType(), glosaPrincipal,
                glosaFinal, "RC", "Renuncia a la Carrera", "TM_SIT");

        LogUtil.setLog(genericSession.getRut());
        CommonAlumnoUtil.resetWorkSession(genericSession, sesionAlumno, key, user);

        return CommonArchivoUtil.getFile(fileCopia, "sit");      
        //ws.setLogCertificacion(new LogCertificacion());                
    }

    /**
     *
     * @param aluCar
     * @param agno
     * @param sem
     * @param fecha
     * @return
     */
    public static String getGlosaPrincipal(AluCar aluCar, Integer agno, Integer sem, Date fecha) {
        String glosa;
        Alumno alumno = aluCar.getAlumno();

        String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());

        glosa = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                + alumno.getAluRut() + "-" + alumno.getAluDv()
                + ", ha presentado solicitud de renuncia a la carrera de "
                + aluCar.getNombreCarrera() + "(" + aluCar.getId().getAcaCodCar() + ") el "
                + getFormatedDate(fecha, "dd/MM/yyyy") + " del " + sem + "/" + agno
                + " y ha sido aceptada con fecha " + getFormatedDate(fecha, "dd/MM/yyyy") + ".";

        return glosa;
    }

    /**
     *
     * @param aluCar
     * @return
     */
    public static String getGlosaFinal(AluCar aluCar) {
        String s1;
        if (asList(43, 50, 53, 55).contains(aluCar.getAaingreso().getAaiViaIng().getViiCod())) {
            s1 = "Debe concurrir con éste documento a la Sociedad de Desarrollo Tecnológico(SDT) para finiquitar su estado financiero.";
        } else {
            s1 = "Para finalizar este proceso debe concurrir con éste documento al Departamento de Finanzas de la Universidad, Sección Recaudación de Matrícula, luego si es beneficiario de “FONDO SOLIDARIO DE CRÉDITO UNIVERSITARIO”, diríjase al Departamento de Beneficios Estudiantiles (VRAE). Si es “BECADO”, comunique la aceptación de la renuncia a la carrera a la Asistente Social de la Facultad.";
        }

        return s1;
    }
}
