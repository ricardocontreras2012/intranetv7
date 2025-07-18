/*
 * @(#)EgresadoLaboralesGetMisDatosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.egresado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.FichaLaboralPersistence;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesGetMisDatosService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param es
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, EgresadoSession es, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        FichaLaboralPersistence fichaLaboralPersistence
                = ContextUtil.getDAO().getFichaLaboralPersistence(ActionUtil.getDBUser());

        es.setFichaLaboralList(
                fichaLaboralPersistence.find(ws.getAluCar().getAlumno().getAluRut()));

        return SUCCESS;
    }
}
