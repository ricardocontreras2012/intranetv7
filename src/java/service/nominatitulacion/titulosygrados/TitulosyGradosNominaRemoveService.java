/*
 * @(#)TitulosyGradosNominaRemoveService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.nominatitulacion.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import java.util.stream.IntStream;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaRemoveService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param agno
     * @param nomina
     * @param tipo
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, Integer agno, String nomina, Integer tipo, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        beginTransaction(ActionUtil.getDBUser());

        IntStream.range(0, ws.getExpedienteLogroList().size())
                .filter(i -> parameters.get("ck_" + i) != null)
                .mapToObj(i -> ws.getExpedienteLogroList().get(i).getId().getExplRut())
                .forEach(rut -> ContextUtil.getDAO()
                .getExpedienteLogroRepository(ActionUtil.getDBUser())
                .removeNomina(rut, tipo, nomina, agno));

        commitTransaction();

        return SUCCESS;
    }
}
