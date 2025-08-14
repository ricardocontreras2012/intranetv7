/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.examen.alumno;

import domain.model.AluCar;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class AlumnoExamenAPConfirmacionService {

    public String service(GenericSession genericSession, String key) {
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();
        String examenStr = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getExamenAP(aluCar.getId());

        if (examenStr.length() > 1) {
            return "mostrar";
        } else {
            return "confirmar";
        }
    }
}
