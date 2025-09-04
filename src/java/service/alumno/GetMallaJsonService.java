/*
 * @(#)GetMallaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import action.alumno.GetMallaJsonAction;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import infrastructure.support.MallaJsonSupport;
import session.GenericSession;
import infrastructure.util.LogUtil;
import java.util.List;

/**
 * Class description
 *
 * @author Alvaro Romero
 * @version 7, 24/08/2025
 */
public final class GetMallaJsonService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param action
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public String service(GenericSession genericSession, String key, GetMallaJsonAction action) throws Exception {
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();
        /*genericSession.setMallaJson(aluCar.getMallaJson(genericSession.getUserType()));
        List<MallaJsonSupport> mallaList = genericSession.getMallaJson();*/
        List<MallaJsonSupport> mallaList = aluCar.getMallaJson(genericSession.getUserType()); // No es necesario deserializar

        action.setMalla(mallaList);

        LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut());

        return SUCCESS;
    }
}
