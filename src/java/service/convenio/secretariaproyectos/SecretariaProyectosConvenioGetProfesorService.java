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
public class SecretariaProyectosConvenioGetProfesorService {

    public String service(GenericSession genericSession, Integer rut, String key) {
        genericSession.getWorkSession(key).setProfesor(ContextUtil.getDAO().getProfesorRepository(ActionUtil.getDBUser()).find(rut));

        return Action.SUCCESS;

    }
}
