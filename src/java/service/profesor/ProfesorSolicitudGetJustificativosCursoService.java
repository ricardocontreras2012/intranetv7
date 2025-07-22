/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Usach
 */
public class ProfesorSolicitudGetJustificativosCursoService {

    public String service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        /*List<Solicitud> lSol = ContextUtil.getDAO()
                .getSolicitudJustificativoPersistence(ActionUtil.getDBUser())
                .find(ws.getCurso().getId())
                .stream()
                .map(SolicitudJustificativo::getSolicitud)
                .collect(Collectors.toList());

        ws.setSolicitudList(lSol);*/
        
  
        
        ws.setJustificativoList(ContextUtil.getDAO().getSolicitudJustificativoPersistence(ActionUtil.getDBUser()).find(ws.getCurso().getId()));
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
