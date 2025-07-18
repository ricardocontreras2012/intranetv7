/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.InscripcionSupport;

/**
 *
 * @author Ricardo
 */
public class CommonInscripcionGetInscripcionInnerService {
     public String service(GenericSession genericSession,
            String key) {
      
        WorkSession ws = genericSession.getWorkSession(key);
    
        AluCar aluCar = ws.getAluCar();
        aluCar.getParametros().setAgnoIns(ws.getAgnoAct());
        aluCar.getParametros().setSemIns(ws.getSemAct());

        InscripcionSupport insSup = new InscripcionSupport(aluCar,genericSession);
        aluCar.setInsList(insSup.getInscripcionFull(ws.getAgnoAct(), ws.getSemAct()));
        return SUCCESS;
    }    
}
