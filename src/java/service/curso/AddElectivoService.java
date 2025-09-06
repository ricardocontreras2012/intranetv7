/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso;

import action.curso.AddElectivoAction;
import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Administrador
 */

public class AddElectivoService {
    public String service(GenericSession genericSession, Integer asign, String elect, String electivo, Integer minor, Integer area, String tipo, String key, AddElectivoAction action) {
    try {    
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();
        beginTransaction(user);
        ContextUtil.getDAO().getElectivoRepository(user).add(asign, elect, electivo, minor, area, tipo, ws.getAgnoAct(), ws.getSemAct());
        commitTransaction();
                
        MiCarreraSupport carrera = ws.getMiCarreraSupport();
        
        if ((carrera.getTcrCtip() == 16) && (carrera.getMencion().getId().getMenCodMen() == 2)) {
            action.setIsEconomia(true);
        } else {
            action.setIsEconomia(false);
        }
        
        ws.setElectivoList(ContextUtil.getDAO().getElectivoRepository(user).find(carrera.getTcrCtip(), carrera.getEspCod(), ws.getAgnoAct(), ws.getSemAct(), genericSession.getRut(), genericSession.getUserType()));
    } catch (Exception e){
    }
        
        return SUCCESS;
    }
}
