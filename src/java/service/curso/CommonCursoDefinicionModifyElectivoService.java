/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso;

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
   public String service(GenericSession genericSession, Integer asign, String elect, String electivo, Integer area, Integer minor, String tipo, String key) {       
        WorkSession ws = genericSession.getWorkSession(key);
              
        ContextUtil.getDAO().getElectivoRepository(ActionUtil.getDBUser()).modify(asign, elect, electivo, minor, area, tipo, ws.getAgnoAct(), ws.getSemAct());                
        MiCarreraSupport carrera = ws.getMiCarreraSupport();                        
        ws.setElectivoList(ContextUtil.getDAO().getElectivoRepository(ActionUtil.getDBUser()).find(carrera.getTcrCtip(), carrera.getEspCod(), ws.getAgnoAct(), ws.getSemAct(), genericSession.getRut(), genericSession.getUserType()));
        
        return SUCCESS;
    }
}
