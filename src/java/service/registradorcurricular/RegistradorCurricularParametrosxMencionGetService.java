/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.registradorcurricular;

import com.opensymphony.xwork2.Action;
import domain.model.Mencion;
import domain.model.ParametroMencion;
import session.GenericSession;
import session.RegistradorSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Usach
 */
public class RegistradorCurricularParametrosxMencionGetService {

    public String service(GenericSession genericSession, RegistradorSession rs, Integer pos, String key) {        
        WorkSession ws = genericSession.getWorkSession(key);
        Mencion mencion = ws.getMencionList().get(pos);
        
        ParametroMencion pmen
                = ContextUtil.getDAO().getParametroMencionRepository(ActionUtil.getDBUser()).getParameter(mencion.getId().getMenCodCar(), mencion.getId().getMenCodMen());
                
        rs.setParametroMencion(pmen);
        rs.setMencion(mencion);
        
        LogUtil.setLog(genericSession.getRut(), mencion.getNombreCarreraFull());
        return Action.SUCCESS;
    }    
}
