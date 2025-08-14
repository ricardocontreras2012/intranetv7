/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.login.alumno;

import domain.model.AluCarId;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class AlumnoLoginStackEmailService {

    public String service(GenericSession genericSession, String key) {
        
        AluCarId id = genericSession.getWorkSession(key).getAluCar().getId();

        return (ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getFlagEmail(id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(), id.getAcaSemIng())==0)?"stack":"email";        
    }
}
