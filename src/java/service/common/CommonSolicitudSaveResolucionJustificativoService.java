/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.util.TextParseUtil;
import domain.model.Alumno;
import domain.model.Curso;
import domain.model.Profesor;
import domain.model.Solicitud;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.HibernateUtil;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import infrastructure.util.MailUtil;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonCursoUtil;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonSolicitudSaveResolucionJustificativoService {

    /**
     *
     * @param genericSession
     * @param key
     * @param parameters
     * @return
     * @throws Exception
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        Integer folio = ws.getSolicitud().getSolFolio();
        String resolucion = "R";
        String respuesta = "Rechazada";

        AtomicBoolean hasA = new AtomicBoolean(false);
        AtomicBoolean hasR = new AtomicBoolean(false);

        // Primero filtramos las claves que comienzan con 'sit_' y 'obs_'
        Map<String, String[]> cursos = parameters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("sit_"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, String[]> observaciones = parameters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("obs_"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        HibernateUtil.beginTransaction(ActionUtil.getDBUser());

        cursos.forEach((cursoKey, cursoValue) -> {
            int cursoId = Integer.parseInt(cursoKey.substring(4));
            String obsKey = "obs_" + cursoId;

            String[] obsValue = observaciones.get(obsKey);

            ContextUtil.getDAO().getSolicitudJustificativoRepository(ActionUtil.getDBUser())
                    .doUpdate(folio, ws.getJustificativoList().get(cursoId).getCurso().getId(), obsValue[0], cursoValue[0]);

            if ("A".equals(cursoValue[0])) {
                sendMessageProfesor(ws.getSolicitud(), ws.getJustificativoList().get(cursoId).getCurso());
                hasA.set(true);
            }

            if ("R".equals(cursoValue[0])) {
                hasR.set(true);
            }

        });

        commitTransaction();

        if (hasA.get() && hasR.get()) {
            resolucion = "AP"; // Mezcla de aprobadas y rechazadas
            respuesta = "Aprobada Parcialmente";
        } else if (hasA.get()) {
            resolucion = "A"; // Solo aprobadas
            respuesta = "Aprobada";
        } else if (hasR.get()) {
            resolucion = "R"; // Solo rechazadas
            respuesta = "Rechazada";
        }

        HibernateUtil.beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getSolicitudRepository(ActionUtil.getDBUser()).saveResolucion(folio, resolucion, respuesta, 40);
        commitTransaction();

        sendMessageAlumno(ws.getSolicitud());
        
        LogUtil.setLog(genericSession.getRut(), folio + " > " + resolucion);

        return SUCCESS;
    }

    private void sendMessageProfesor(Solicitud sol, Curso curso) {
        Alumno alumno = sol.getAluCar().getAlumno();
        String subject = "Solicitud de Justificativo prueba PEP para el periodo "+
                DateUtil.getFormattedDate(sol.getSolFechaInicio(),"dd-mm-yyyy") + " " + DateUtil.getFormattedDate(sol.getSolFechaTermino(),"dd-mm-yyyy");
        String body = "La solicitud de justificación por inasistencia a PEP del curso " +curso.getNombreCorto()+ " "+
                ("1".equals(alumno.getAluSexo())?" la alumna ":"del alumno ")+
                CommonAlumnoUtil.getNombreSocial(alumno) + " correspondiente al periodo del "+DateUtil.getFormattedDate(sol.getSolFechaInicio(),"dd-mm-yyyy") + " al " + DateUtil.getFormattedDate(sol.getSolFechaTermino(),"dd-mm-yyyy")+ " ha sido aceptada por esta unidad académica.";

        CommonCursoUtil.getProfesores(curso).stream()
                .map(Profesor::getProfEmail)
                .map(TextParseUtil::commaDelimitedStringToSet)
                .flatMap(Set::stream)
                .forEach(email -> MailUtil.sendEmail(email, subject, body));
    }

    private void sendMessageAlumno(Solicitud sol) {
        Alumno alumno = sol.getAluCar().getAlumno();
        String correo = alumno.getAluEmailUsach();
                
        MailUtil.sendEmail(correo, "Solicitud de Justificativo prueba PEP para el periodo " + DateUtil.getFormattedDate(sol.getSolFechaInicio(),"dd-mm-yyyy") + " " + DateUtil.getFormattedDate(sol.getSolFechaTermino(),"dd-mm-yyyy"), "Estimado alumn@, su solicitud ha sido resuelta. Ver resultado en intranet");
    }
}