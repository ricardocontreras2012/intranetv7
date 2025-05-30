/*
 * @(#)ConsultaMisDatosGetMisDatosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.consulta;


import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Externo;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;


/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ConsultaMisDatosGetMisDatosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @return Action status.
     */
    public static String service(GenericSession genericSession) {
        Externo externo = ContextUtil.getDAO().getExternoPersistence(ActionUtil.getDBUser()).find(genericSession.getRut());

        //externo.setExtEmail(externo.getExtEmail());

        return SUCCESS;
    }
}
