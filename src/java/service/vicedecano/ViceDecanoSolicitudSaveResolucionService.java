/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.vicedecano;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Alumno;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getFormattedDate;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonConstanciaUtil;
import infrastructure.util.common.CommonSimpleMessageUtil;
import infrastructure.util.common.CommonUtil;
import service.alumno.AlumnoSolicitudRetiroConstanciaService;

/**
 *
 * @author Administrador
 */
public class ViceDecanoSolicitudSaveResolucionService {

    public String service(GenericSession genericSession, String key, String resolucion, String respuesta) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        Integer folio = ws.getSolicitud().getSolFolio();

        Integer estado;
        switch (ws.getSolicitud().getTsolicitud().getTsolTipo()) {
            case "MAT":
                estado = "TR".equals(resolucion) ? 65 : 40; //En trámite o Resuelta
                break;
            default:
                estado = 40; //Resuelta
                break;
        }

        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser()).saveResolucion(ws.getSolicitud().getSolFolio(), resolucion, respuesta, estado);
        commitTransaction();

        ///Necesario recuperar aluCar de la BD
        AluCar aluCar = ContextUtil.getDAO().getAluCarPersistence(ActionUtil.getDBUser()).find(ws.getSolicitud().getAluCar().getId());
        aluCar.setAluCarFunction();
        ///

        Integer solicitud = ws.getSolicitud().getSolFolio();
        String user = ActionUtil.getDBUser();
        CommonUtil.setAgnoSemAct(ws);

        Date fecha = ws.getSolicitud().getSolFecha();
        String verificador = CommonCertificacionUtil.getVerificador(folio);
        String fechaString = DateUtil.getFechaCiudad(fecha);

        switch (ws.getSolicitud().getTsolicitud().getTsolCodigo()) {
            case 11:
                if ("A".equals(resolucion)) {
                    ContextUtil.getDAO().getSacarreraPersistence(user).retiroConExp(aluCar.getId(), ws.getAgnoAct(), ws.getSemAct(), solicitud, DateUtil.getFormattedDate(fecha, "dd/MM/yyyy"));
                }
                saveRetiro(genericSession, ws, key, aluCar, fecha, fechaString, solicitud, folio, verificador);
                break;
            case 25:
                if ("A".equals(resolucion) || "AE".equals(resolucion)) {
                    ContextUtil.getDAO().getSacarreraPersistence(user).reincorporacionEliminacion(aluCar.getId(), ws.getAgnoAct(), ws.getSemAct(), solicitud, DateUtil.getFormattedDate(fecha, "dd/MM/yyyy"), respuesta);
                }
                saveReincorporacionEliminacion(genericSession, ws, key, aluCar, solicitud, resolucion, folio, verificador);
                break;
            case 30:
                if ("A".equals(resolucion)) {
                    ContextUtil.getDAO().getSacarreraPersistence(user).reincorporacionAbandono(aluCar.getId(), ws.getAgnoAct(), ws.getSemAct(), solicitud, DateUtil.getFormattedDate(fecha, "dd/MM/yyyy"), respuesta);
                }
                saveReincorporacionAbandono(genericSession, ws, key, aluCar, solicitud, resolucion, folio, verificador);
                break;
            case 35:
                if ("A".equals(resolucion)) {
                    ContextUtil.getDAO().getSacarreraPersistence(user).reincorporacionNoTitulacion(aluCar.getId(), ws.getAgnoAct(), ws.getSemAct(), solicitud, DateUtil.getFormattedDate(fecha, "dd/MM/yyyy"), respuesta);
                }
                saveReincorporacionNoTitulacion(genericSession, ws, key, aluCar, solicitud, resolucion, folio, verificador);
                break;
            case 40:
                saveMatricula(genericSession, key, aluCar, respuesta, resolucion);
                break;
        }

        return SUCCESS;
    }

    private void saveRetiro(GenericSession genericSession, WorkSession ws, String key, AluCar aluCar, Date fecha, String fechaString, Integer solicitud, Integer folio, String verificador) throws Exception {

        AlumnoSolicitudRetiroConstanciaService svc  = new AlumnoSolicitudRetiroConstanciaService();
        String glosaPrincipal = svc.getGlosaPrincipal(aluCar, ws.getAgnoAct(), ws.getSemAct(), fecha, 11);
        String glosaFinal = svc.getGlosaFinal(aluCar);

        String file = "Constancia_Retiro_Con_Expresion_Causa_" + aluCar.getId().getAcaRut() + "_" + solicitud + ".pdf";

        String fileTmp = getAttachFileName(file, "_" + 0, folio);
        String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + ws.getAgnoAct() + "-" + format("%05d", solicitud) + ".pdf";

        CommonConstanciaUtil.writePdf(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp, solicitud, verificador, aluCar, glosaPrincipal, glosaFinal, fechaString);
        Files.copy(Paths.get(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);

        CommonSimpleMessageUtil.send(fileTmp, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Vicedecanato de Docencia", genericSession.getUserType(), glosaPrincipal,
                glosaFinal, "RT", "Retiro Temporal con Expresión de Causa", "TM_SIT");

        LogUtil.setLog(genericSession.getRut(), folio);
    }

    private void saveReincorporacionEliminacion(GenericSession genericSession, WorkSession ws, String key, AluCar aluCar, Integer solicitud, String estado, Integer folio, String verificador) throws Exception {
        Alumno alumno = aluCar.getAlumno();
        String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());
        String fecha = getFormattedDate(getSysdate(), "dd/MM/yyyy");
        String fechaString = DateUtil.getFechaCiudad(getSysdate());
        String status = "";
        String glosaIntermedia = " para el " + ws.getSemAct() + "/" + ws.getAgnoAct();
        String glosaFinal = "Esta reincorporación quedará registrada en su hoja de vida como una oportunidad. Revise su situación en Mis Datos Curricuales en la Intranet Académica.";

        switch (estado) {
            case "A":
                status = "Aceptada";
                break;
            case "AE":
                status = "Aceptada Excepcionalmente";
                break;
            case "R":
                status = "Rechazada";
                glosaIntermedia = "";
                glosaFinal = "";
                break;
        }

        String glosaPrincipal = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                + alumno.getAluRut() + "-" + alumno.getAluDv()
                + " ha solicitado reincorporación a la carrera " + aluCar.getNombreCarrera() + "(" + aluCar.getId().getAcaCodCar() + ")"
                + " por causal académica y que su solicitud ha sido " + status + glosaIntermedia
                + " con fecha " + fecha + ".";

        String file = "Constancia_Solicitud_Rincorporacion_" + aluCar.getId().getAcaRut() + "_" + solicitud + ".pdf";

        String fileTmp = SystemParametersUtil.PATH_ATTACH_MESSAGES + file;
        String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + ws.getAgnoAct() + "-" + format("%05d", solicitud) + ".pdf";

        CommonConstanciaUtil.writePdf(fileTmp, solicitud, verificador, aluCar, glosaPrincipal, "", fechaString);
        Files.copy(Paths.get(fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);

        if (Arrays.asList("A", "AE").contains(estado)) {
            CommonSimpleMessageUtil.send(file, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Vicedecanato de Docencia", genericSession.getUserType(), glosaPrincipal,
                    glosaFinal, "REI", "Reincorporación Eliminación por Causal Académica", "TM_SIT");
        } else {
            String[] files = new String[2];
            files[0] = file;
            files[1] = "R-02563-2019-05-02 Reglamento General de Regimen de Estudios Pregrado_.pdf";
            CommonSimpleMessageUtil.send(files, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Vicedecanato de Docencia", genericSession.getUserType(), glosaPrincipal,
                    glosaFinal, "REI", "Reincorporación Eliminación por Causal Académica", "TM_STD");
        }

        LogUtil.setLog(genericSession.getRut(), folio);
    }

    private void saveReincorporacionAbandono(GenericSession genericSession, WorkSession ws, String key, AluCar aluCar, Integer solicitud, String estado, Integer folio, String verificador) throws Exception {
        Alumno alumno = aluCar.getAlumno();
        String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());
        String fecha = getFormattedDate(getSysdate(), "dd/MM/yyyy");
        String fechaString = DateUtil.getFechaCiudad(getSysdate());
        String status = "";
        String glosaIntermedia = " para el " + ws.getSemAct() + "/" + ws.getAgnoAct();
        String glosaFinal = "Esta reincorporación quedará registrada en su hoja de vida como una oportunidad. Revise su situación en Mis Datos Curricuales en la Intranet Académica.";

        switch (estado) {
            case "A":
                status = "Aceptada";
                break;
            case "R":
                status = "Rechazada";
                glosaIntermedia = "";
                glosaFinal = "";
                break;
        }

        String glosaPrincipal = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                + alumno.getAluRut() + "-" + alumno.getAluDv()
                + " ha solicitado reincorporación a la carrera " + aluCar.getNombreCarrera() + "(" + aluCar.getId().getAcaCodCar() + ")"
                + " por no matrícula y que su solicitud ha sido " + status + glosaIntermedia
                + " con fecha " + fecha + ".";

        String file = "Constancia_Solicitud_Rincorporacion_" + aluCar.getId().getAcaRut() + "_" + solicitud + ".pdf";

        String fileTmp = SystemParametersUtil.PATH_ATTACH_MESSAGES + file;
        String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + ws.getAgnoAct() + "-" + format("%05d", solicitud) + ".pdf";

        CommonConstanciaUtil.writePdf(fileTmp, solicitud, verificador, aluCar, glosaPrincipal, "", fechaString);
        Files.copy(Paths.get(fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);

        if (Arrays.asList("A").contains(estado)) {
            CommonSimpleMessageUtil.send(file, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Vicedecanato de Docencia", genericSession.getUserType(), glosaPrincipal,
                    glosaFinal, "REI", "Reincorporación por No Matrícula", "TM_SIT");
        }

        LogUtil.setLog(genericSession.getRut(), folio);
    }

    private void saveReincorporacionNoTitulacion(GenericSession genericSession, WorkSession ws, String key, AluCar aluCar, Integer solicitud, String estado, Integer folio, String verificador) throws Exception {
        Alumno alumno = aluCar.getAlumno();
        String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());
        String fecha = getFormattedDate(getSysdate(), "dd/MM/yyyy");
        String fechaString = DateUtil.getFechaCiudad(getSysdate());
        String status = "";
        String glosaIntermedia = " para el " + ws.getSemAct() + "/" + ws.getAgnoAct();
        String glosaFinal = "Esta reincorporación quedará registrada en su hoja de vida como una oportunidad. Revise su situación en Mis Datos Curricuales en la Intranet Académica.";

        switch (estado) {
            case "A":
                status = "Aceptada";
                break;
            case "R":
                status = "Rechazada";
                glosaIntermedia = "";
                glosaFinal = "";
                break;
        }

        String glosaPrincipal = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                + alumno.getAluRut() + "-" + alumno.getAluDv()
                + " ha solicitado reincorporación a la carrera " + aluCar.getNombreCarrera() + "(" + aluCar.getId().getAcaCodCar() + ")"
                + " por no titulacón y que su solicitud ha sido " + status + glosaIntermedia
                + " con fecha " + fecha + ".";

        String file = "Constancia_Solicitud_Rincorporacion_" + aluCar.getId().getAcaRut() + "_" + solicitud + ".pdf";

        String fileTmp = SystemParametersUtil.PATH_ATTACH_MESSAGES + file;
        String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + ws.getAgnoAct() + "-" + format("%05d", solicitud) + ".pdf";

        CommonConstanciaUtil.writePdf(fileTmp, solicitud, verificador, aluCar, glosaPrincipal, "", fechaString);
        Files.copy(Paths.get(fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);

        if (Arrays.asList("A").contains(estado)) {
            CommonSimpleMessageUtil.send(file, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Vicedecanato de Docencia", genericSession.getUserType(), glosaPrincipal,
                    glosaFinal, "REI", "Reincorporación por No Titulación", "TM_SIT");
        }

        LogUtil.setLog(genericSession.getRut(), folio);
    }

    private void saveMatricula(GenericSession genericSession, String key, AluCar aluCar, String respuesta, String resolucion) throws Exception {

        String glosaFinal = "\n\nCarolina Nicolas\n"
                + "Vicedecana de Docencia\n"
                + "Facultad de Administración y Economía\n"
                + "Universidad de Santiago de Chile";

        String glosaPrincipal = "";

        switch (resolucion) {
            case "TR":
                glosaPrincipal = "Buenos días/Buenas tardes,\n\n"
                        + "Es un placer saludarle. Le informamos que su solicitud de matrícula fuera de plazo será\n"
                        + "tramitada. Tan pronto como la Universidad habilite el proceso, se le notificará a través de\n"
                        + "este medio y de la Intranet FAE, para que pueda realizar su matrícula dentro del periodo\n"
                        + "establecido por la Universidad.\n\n"
                        + "Saludos cordiales,";
                break;
            case "R":
                glosaPrincipal = "Buenos días/Buenas tardes,\n\n"
                        + "Junto con saludar, se informa que, lamentablemente, su solicitud de matrícula ha sido\n"
                        + "rechazada.\n"
                        + respuesta + "\n\n"
                        + "Saludos cordiales,";
                break;
            case "A":
                glosaPrincipal = "Buenos días/Buenas tardes,\n\n"
                        + "Junto con saludar, le informamos que se ha autorizado su matrícula fuera de plazo. Para\n"
                        + "completar el proceso, deberá acudir de manera presencial a la Unidad de Crédito y\n"
                        + "Cobranzas, de lunes a viernes, ubicada en Enrique\n"
                        + "Kirberg N° 10, frente a la Biblioteca Central de la Usach.\n"
                        + "\n"
                        + "El plazo para realizar el pago de la matrícula es hasta:\n"
                        + respuesta + "\n\n"
                        + "Saludos cordiales,";
                break;
        }

        CommonSimpleMessageUtil.send(new String[0], key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Vicedecanato de Docencia", genericSession.getUserType(), glosaPrincipal,
                glosaFinal, "MAT", "Solicitud de Matrícula Fuera de Plazo", "TM_STD");

        LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut());
    }
}
