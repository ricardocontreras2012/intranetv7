/*
 * @(#)EgresadoEstudiosViewEstudioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.FichaEstudioPersistence;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;


/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoEstudiosViewEstudioService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param es
     * @param correl
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, EgresadoSession es, Integer correl, String key) {
        WorkSession             ws             = genericSession.getWorkSession(key);
        FichaEstudioPersistence fichaEstudioPersistence =
            ContextUtil.getDAO().getFichaEstudioPersistence(ActionUtil.getDBUser());

            es.setFichaEstudio(fichaEstudioPersistence.find(ws.getAluCar().getAlumno().getAluRut(),
                    correl));

            return SUCCESS;
    }
}
