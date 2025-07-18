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
import java.util.Date;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.util.DateUtil.getFechaCiudad;
import static infrastructure.util.DateUtil.getFormattedDate;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.HibernateUtil;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import static java.util.Arrays.asList;
import session.AlumnoSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonSimpleMessageUtil;
import infrastructure.util.common.CommonUtil;
import infrastructure.util.common.CommonConstanciaUtil;

/**
 *
 * @author rcontreras
 */
public class AlumnoSolicitudRetiroConstanciaService {

    public ActionInputStreamUtil service(GenericSession genericSession, AlumnoSession sesionAlumno, String key) throws Exception {

        String name;
        InputStream input;
        String description;

        Integer folio = CommonCertificacionUtil.getFolio();
        name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, 91);

        input = getInput(genericSession, sesionAlumno, folio, key);
        description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);

    }

    /**
     *
     * @param genericSession
     * @param report
     * @param key
     * @return
     * @throws java.lang.Exception
     */
    private InputStream getInput(GenericSession genericSession, AlumnoSession sesionAlumno, Integer folio, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        Solicitud solicitud = ws.getSolicitud();
        Integer sol = solicitud.getSolFolio();
        String user = ActionUtil.getDBUser();
        CommonUtil.setAgnoSemAct(ws);
        new SolicitudSupport(solicitud).setAprobada();
        
        HibernateUtil.beginTransaction(user);               
        ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser()).save(solicitud);
        ContextUtil.getDAO().getSacarreraPersistence(user).retiroSinExp(aluCar.getId(), ws.getAgnoAct(), ws.getSemAct(), sol);
        HibernateUtil.commitTransaction();

        String verificador = CommonCertificacionUtil.getVerificador(folio);

        Date fecha = getSysdate();
        String fechaString = getFechaCiudad(fecha);
        String glosaPrincipal;
        String glosaFinal = getGlosaFinal(aluCar);

        glosaPrincipal = getGlosaPrincipal(aluCar, ws.getAgnoAct(), ws.getSemAct(), fecha, 10);

        String file = "Constancia_Retiro_" + aluCar.getId().getAcaRut() + "_" + sol + ".pdf";

        String fileTmp = getAttachFileName(file, "_" + 0, folio);
        String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + ws.getAgnoAct() + "-" + format("%05d", sol) + ".pdf";
       
        CommonConstanciaUtil.writePdf(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp, sol, verificador, aluCar, glosaPrincipal, glosaFinal, fechaString);
        Files.copy(Paths.get(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);
        
        CommonSimpleMessageUtil.send(fileTmp, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(),  genericSession.getRut(), "Registrador Curricular", genericSession.getUserType(), glosaPrincipal,
                glosaFinal, "RT", "Retiro Temporal sin Expresión de Causa", "TM_SIT");

        LogUtil.setLog(genericSession.getRut());
        CommonAlumnoUtil.resetWorkSession(genericSession, sesionAlumno, key, user);
        return CommonArchivoUtil.getFile(fileCopia, "sit");        
    }

    /**
     *
     * @param aluCar
     * @param agno
     * @param sem
     * @param fecha
     * @param tipo
     * @return
     */
    public String getGlosaPrincipal(AluCar aluCar, Integer agno, Integer sem, Date fecha, Integer tipo) {
        String glosa;
        Alumno alumno = aluCar.getAlumno();

        String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());

        glosa = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                + alumno.getAluRut() + "-" + alumno.getAluDv()
                + " de la carrera " + aluCar.getNombreCarrera() + "(" + aluCar.getId().getAcaCodCar() + ")"
                + ", ha presentado solicitud de retiro temporal " + (tipo == 10 ? "sin" : "con") + " expresión de causa el "
                + getFormattedDate(fecha, "dd/MM/yyyy")
                + " del " + sem + "/" + agno
                + " y ha sido aceptada con fecha " + getFormattedDate(fecha, "dd/MM/yyyy") + ".";

        return glosa;
    }

    /**
     *
     * @param aluCar
     * @return
     */
    public String getGlosaFinal(AluCar aluCar) {
        String s1;

        if (asList(43, 50, 53, 55).contains(aluCar.getAaingreso().getAaiViaIng().getViiCod())) {
            s1 = "Debe concurrir con éste documento a la Sociedad de Desarrollo Tecnológico(SDT) para finiquitar su estado financiero.";
        } else {
            s1 = "Para finalizar este proceso debe concurrir con éste documento al Departamento de Finanzas de la Universidad, Sección Recaudación de Matrícula, luego si es beneficiario de “FONDO SOLIDARIO DE CRÉDITO UNIVERSITARIO”, diríjase al Departamento de Beneficios Estudiantiles (VRAE). Si es “BECADO”, comunique la aceptación del retiro temporal a la Asistente Social de la Facultad.";
        }

        return s1;
    }
}
