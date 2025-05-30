/*
 * @(#)CommonAlumnoGetCurricularesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetCurricularesService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        ws.setCcalidad(ContextUtil.getDAO().getCcalidadPersistence(ActionUtil.getDBUser()).findxCalidad(
                aluCar));

        ws.getAluCar().setRequisitoLogroAdicionalList();
        LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut());
        return SUCCESS;
    }
}
