/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.emailNormalizado;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import session.GenericSession;

/**
 *
 * @author Alvaro
 */
public class AlumnoSolicitudExpedienteUpdatePersonalesService {
    public String service(ActionCommonSupport action, GenericSession genericSession, String email, String emailLaboral, String direccion, Integer comuna, String fono, Integer estadoCivil) {
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).setMisDatos(genericSession.getRut(), emailNormalizado(email), emailNormalizado(emailLaboral), direccion, comuna, fono, estadoCivil);
        commitTransaction();
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }  
}
