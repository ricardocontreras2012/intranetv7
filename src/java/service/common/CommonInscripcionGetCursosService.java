/*
 * @(#)CommonInscripcionGetCursosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.DerechoCoordinadorSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonInscripcionGetCursosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, Integer pos, String key) {   
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        DerechoCoordinadorSupport derecho = aluCar.getDerechosCoordinadorInscripcion().get(pos);

        ws.setDerechoCoordinador(derecho);
        aluCar.setCursosInscripcion(genericSession, derecho, ws.getAgnoAct(), ws.getSemAct());
     
        return SUCCESS;
    }
}
