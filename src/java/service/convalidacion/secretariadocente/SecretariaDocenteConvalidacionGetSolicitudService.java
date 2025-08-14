/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convalidacion.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.SecretariaSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteConvalidacionGetSolicitudService {

 public String service(GenericSession genericSession, SecretariaSession secreSession, Integer pos, String key) {
     
        WorkSession ws = genericSession.getWorkSession(key);
        secreSession.setConvalidacion( secreSession.getConvalidaciones().get(pos));
        AluCar aluCar = ws.getAluCar();
        
        secreSession.setPorAprobar(ContextUtil.getDAO().getConvalidacionSolicitudAsignRepository(ActionUtil.getDBUser()).getPorConvalidar(aluCar, secreSession.getConvalidacion().getCosCorrel()));

        return SUCCESS;
    }
}
