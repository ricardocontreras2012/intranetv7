/*
 * @(#)EgresadoLoginSeleccionarIngresoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import static service.egresado.EgresadoLoginService.getComplemento;
import session.GenericSession;


/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EgresadoLoginSeleccionarIngresoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos         Numero del registro seleccionado en el formulario.
     * @param key         LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Integer pos, String key) {
        getComplemento(genericSession,
                genericSession.getWorkSession(key).getAluCarList().get(pos), key);

        return SUCCESS;
    }
}
