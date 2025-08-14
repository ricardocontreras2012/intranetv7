/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.reservasala;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class GetCursoService {
    public String service(GenericSession genericSession, String cursoStr, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = new Curso(cursoStr);
        ws.setCurso(ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).find(
                           curso.getId()));
        return SUCCESS;
    }
}
