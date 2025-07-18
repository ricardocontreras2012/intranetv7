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
import infrastructure.util.common.CommonUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioNewConvenioService {

    public String service(GenericSession genericSession, ProyectoSession ps, String key) {
        ps.setProyectoList(ContextUtil.getDAO().getProyectoPersistence(ActionUtil.getDBUser()).find(genericSession.getRut()));
        CommonUtil.setAgnoSemAct(genericSession.getWorkSession(key));
        
        return Action.SUCCESS;
    }
}
