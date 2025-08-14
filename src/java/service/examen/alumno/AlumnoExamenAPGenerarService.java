/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.examen.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class AlumnoExamenAPGenerarService {

    public String service(GenericSession genericSession, String key) {
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();
        ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).generaExamenAP(aluCar.getId());

        return SUCCESS;
    }
}
