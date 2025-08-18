/*
 * @(#)GetMallaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetMallaService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public String service(GenericSession genericSession, String key) throws Exception {
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();

        genericSession.setMallaContainer(aluCar.getMalla(genericSession.getUserType()));
        LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut());

        return SUCCESS;
    }
}
