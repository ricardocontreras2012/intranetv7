/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class CommonCursoDefinicionModifyElectivoService {
   public static String service(GenericSession genericSession, Integer asign, String elect, String electivo, Integer area, Integer minor, String key) {       
        WorkSession ws = genericSession.getWorkSession(key);
              
        ContextUtil.getDAO().getElectivoPersistence(ActionUtil.getDBUser()).modify(asign,elect, electivo, minor, area, ws.getAgnoAct(), ws.getSemAct());                
        MiCarreraSupport carrera = ws.getMiCarreraSupport();                        
        ws.setElectivoList(ContextUtil.getDAO().getElectivoPersistence(ActionUtil.getDBUser()).find(carrera.getTcrCtip(), carrera.getEspCod(), ws.getAgnoAct(), ws.getSemAct(), genericSession.getRut(), genericSession.getUserType()));
        
        return SUCCESS;
    }
}
