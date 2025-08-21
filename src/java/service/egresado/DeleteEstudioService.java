/*
 * @(#)DeleteEstudioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Alumno;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;


/**
 *
 * @author Alvaro Romero C.
 */
public class DeleteEstudioService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param correl
     *
     * @return
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Integer correl) {
        String user = ActionUtil.getDBUser();
        Alumno alumno = ContextUtil.getDAO().getAlumnoRepository(user).getMisDatos(genericSession.getRut());

        beginTransaction(user);
        ContextUtil.getDAO().getFichaEstudioRepository(user).deleteEstudio(
            correl, alumno.getAluRut());
        commitTransaction();
        action.addActionMessage(action.getText("message.datos.borrados"));

        return SUCCESS;
    }
}
