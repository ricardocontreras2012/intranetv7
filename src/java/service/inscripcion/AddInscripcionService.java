/*
 * @(#)AddInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.inscripcion;


import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.InscripcionSupport;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AddInscripcionService {

    /**
     * Method Servicio
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            Integer pos, String key)
            throws Exception {    

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();        
        ws.setCurso(aluCar.getCursosInscripcion().get(pos));                   
        
        InscripcionSupport insSup = new InscripcionSupport(aluCar, genericSession);
        insSup.setSctNivel();
               
        return aluCar.addInscripcionCoordinador(action, genericSession, ws.getCurso(), ws.getDerechoCoordinador());                  
    }
}
