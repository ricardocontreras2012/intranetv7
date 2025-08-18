/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convenio.secretariaproyectos;

import com.opensymphony.xwork2.Action;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosGetCursosService {

    public String service(GenericSession genericSession, Integer rut,
            Integer agno, Integer sem, String proyecto, String key) {
        genericSession.getWorkSession(key).setCursoList(ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).find(rut, agno, sem, proyecto));
     
        return Action.SUCCESS;
    }
}
