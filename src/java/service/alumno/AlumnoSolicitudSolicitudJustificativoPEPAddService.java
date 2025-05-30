/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.Solicitud;
import infrastructure.support.SolicitudSupport;
import static java.lang.Integer.parseInt;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Usach
 */
public class AlumnoSolicitudSolicitudJustificativoPEPAddService {
     /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);        
        String user = ActionUtil.getDBUser();
        String inicio = parameters.get("fechaInicio")[0];
        String termino = parameters.get("fechaTermino")[0];        
        String glosa = parameters.get("glosa")[0];
            
        Solicitud solicitud = ws.getSolicitud();
        Integer folio = solicitud.getSolFolio();
        solicitud.setSolFechaInicio(DateUtil.stringToDate(inicio));
        solicitud.setSolFechaTermino(DateUtil.stringToDate(termino));
        solicitud.setSolMotivo(StringUtils.abbreviate(glosa, 2000));
        new SolicitudSupport(solicitud).setGenerada();
        
        beginTransaction(user);
        ContextUtil.getDAO().getSolicitudPersistence(user).save(solicitud);
        commitTransaction();

        beginTransaction(user);
        String field;
        Curso curso;
        String acum = "";
        Integer cursoPos;
        
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            field = entry.getKey();

            if (field.startsWith("curso_")) {
                int pos = field.lastIndexOf('_');
                int row = parseInt(field.substring(pos + 1));                
                cursoPos = Integer.parseInt(parameters.get("curso_" + row)[0]);
                
                curso = ws.getCursoSolicitudList().get(cursoPos);
                ContextUtil.getDAO().getSolicitudJustificativoPersistence(user).doSave(folio, curso.getId());

                acum = acum + curso.getNombreFull() + " :: ";
            }
        }
        commitTransaction();

        beginTransaction(user);
        ContextUtil.getDAO().getSolicitudPersistence(user).modify(folio, acum);
        commitTransaction();
         
        LogUtil.setLog(genericSession.getRut(), folio);

        return SUCCESS;
    }
}
