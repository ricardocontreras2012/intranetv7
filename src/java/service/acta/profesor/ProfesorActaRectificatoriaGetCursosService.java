/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.acta.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class ProfesorActaRectificatoriaGetCursosService {

    public String service(GenericSession genericSession, String key, String keyParent) {

        WorkSession ws = new WorkSession(ActionUtil.getDBUser());
        genericSession.getSessionMap().put(key, ws);
        WorkSession wsParent = genericSession.getWorkSession(keyParent);
        ws.setCursoList(ContextUtil.getDAO().getProfesorRepository(ActionUtil.getDBUser()).findCursosActaRectificatoria(wsParent.getProfesor().getProfRut()));
        ws.setProfesor(wsParent.getProfesor());
        
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
