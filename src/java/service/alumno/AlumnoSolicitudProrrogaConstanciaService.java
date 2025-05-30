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

/**
 *
 * @author rcontreras
 */
public class AlumnoSolicitudProrrogaConstanciaService {

    public static ActionInputStreamUtil service(GenericSession genericSession, AlumnoSession sesionAlumno, String key) throws Exception {

        String name;
        InputStream input;
        String description;

        Integer folio = CommonCertificacionUtil.getFolio();
        name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, 93);
        input = getInput(genericSession, sesionAlumno, folio, key);
        description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }

    /**
     *
     * @param genericSession
     * @param report
     * @param folio
     * @param key
     * @return
     * @throws java.lang.Exception
     */
    private static InputStream getInput(GenericSession genericSession, AlumnoSession sesionAlumno, Integer folio, String key) throws Exception {
               
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        Solicitud solicitud = ws.getSolicitud();
        Integer sol = ws.getSolicitud().getSolFolio();
        new SolicitudSupport(solicitud).setAprobada();
        String user = ActionUtil.getDBUser();
        CommonUtil.setAgnoSemAct(ws);
        
        HibernateUtil.beginTransaction(user);
        ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser()).save(solicitud);
        ContextUtil.getDAO().getSacarreraPersistence(user).prorroga(aluCar.getId(), ws.getAgnoAct(), ws.getSemAct(), sol);
        HibernateUtil.commitTransaction();

        String verificador = CommonCertificacionUtil.getVerificador(folio);

        Date fecha = getSysdate();
        String fechaString = getFechaCiudad(fecha);
        String glosaPrincipal;

        glosaPrincipal = getGlosaPrincipal(aluCar, ws.getAgnoAct(), ws.getSemAct(), fecha);

        String file = "Constancia_Prorroga_" + aluCar.getId().getAcaRut() + "_" + solicitud + ".pdf";

        String fileTmp = getAttachFileName(file, "_" + 0, folio);
        String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + ws.getAgnoAct() + "-" + format("%05d", sol) + ".pdf";

        CommonConstanciaUtil.writePdf(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp, sol, verificador, aluCar, glosaPrincipal, "", fechaString);
        Files.copy(Paths.get(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);

        CommonSimpleMessageUtil.send(fileTmp, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(), genericSession.getRut(), "Registrador Curricular", genericSession.getUserType(), glosaPrincipal,
                "", "PPL", "Prórroga de Período Lectivo", "TM_SIT");

        LogUtil.setLog(genericSession.getRut());
        CommonAlumnoUtil.resetWorkSession(genericSession, sesionAlumno, key, ActionUtil.getDBUser());

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
                + " de la carrera " + aluCar.getNombreCarrera() + "(" + aluCar.getId().getAcaCodCar() + ")"
                + ", ha presentado solicitud de prórroga de período lectivo el "
                + getFormatedDate(fecha, "dd/MM/yyyy")
                + " del " + sem + "/" + agno
                + " y ha sido aceptada con fecha " + getFormatedDate(fecha, "dd/MM/yyyy") + ".";

        return glosa;
    }
}
