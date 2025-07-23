/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariaproyectos;

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
public class SecretariaProyectosConvenioGetConveniosService {

    public String service(GenericSession genericSession, ProyectoSession ps, String key) {
        ps.setConvenioList(ContextUtil.getDAO().getConvenioRepository(ActionUtil.getDBUser()).getMisConvenios(genericSession.getRut()));
        LogUtil.setLog(genericSession.getRut());        
        return Action.SUCCESS;
    }
}
