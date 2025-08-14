/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convenio.secretariaproyectos;

import com.opensymphony.xwork2.Action;
import session.GenericSession;
import session.ProyectoSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioGetProyectoService {

    public String service(GenericSession genericSession, ProyectoSession ps,  String codigo, String key) {
        ps.setProyecto(ContextUtil.getDAO().getProyectoRepository(ActionUtil.getDBUser()).findProyecto(codigo));

        return Action.SUCCESS;

    }
}
