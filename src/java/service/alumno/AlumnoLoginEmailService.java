/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Ricardo
 */
public class AlumnoLoginEmailService {

    public String service(GenericSession genericSession, String email, String key) {
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).setEmail(genericSession.getWorkSession(key).getAluCar().getId().getAcaRut(), email);
        commitTransaction();
        return "stack";
    }
}
