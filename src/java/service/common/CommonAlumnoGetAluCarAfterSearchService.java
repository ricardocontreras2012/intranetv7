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

/**
 *
 * @author rcontreras
 */
public class CommonAlumnoGetAluCarAfterSearchService {

    public static String service(GenericSession gs, String key) {
        AluCar aluCar = gs.getWorkSession(key).getAluCar();

        aluCar.setCarga(gs);                     
        String parentKey = gs.getWorkSession(key).getKeyParent(); 

        WorkSession ws = gs.getWorkSession(parentKey);                       
        ws.setAluCar(aluCar);
        
        return SUCCESS;
    }
}
