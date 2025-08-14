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
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioShowConvenioService {

    public String service(GenericSession genericSession, ProyectoSession ps, Integer pos, String key) {
        ps.setConvenio( ContextUtil.getDAO().getConvenioRepository(ActionUtil.getDBUser()).find(ps.getConvenioList().get(pos).getConvNro()));
        LogUtil.setLog(genericSession.getRut(), ps.getConvenio().getConvNro());
        return Action.SUCCESS;
    }
}
