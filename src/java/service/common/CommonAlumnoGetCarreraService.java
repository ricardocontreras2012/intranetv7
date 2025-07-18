/*
 * @(#)CommonAlumnoGetCarreraService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import action.common.CommonAlumnoGetCarreraAction;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetCarreraService {

    /**
     * Method Servicio
     *
     * @param action
     * @param gs Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(CommonAlumnoGetCarreraAction action, GenericSession gs, Integer pos,
            String key)
            {
                
        WorkSession ws = gs.getWorkSession(key);
        AluCar aluCar = ws.getAluCarList().get(pos);

        aluCar.setAlumno(ws.getAlumno());
        aluCar.setInitValues();
        aluCar.setCarga(gs);        
        ws.setAluCar(aluCar);
        ws.setCursoList(aluCar.getCarga());
        action.setActionNested(ws.getActionNested());

        return SUCCESS;

    }
}
