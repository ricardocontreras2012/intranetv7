/*
 * @(#)CommonRequisitoAdicionalLogroRemoveInscripcionesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import domain.repository.InscripcionAdicionalLogroPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonRequisitoAdicionalLogroRemoveInscripcionesService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        InscripcionAdicionalLogroPersistence inscripcionAdicionalPersistence
                = ContextUtil.getDAO().getInscripcionAdicionalLogroPersistence(ActionUtil.getDBUser());

        // Iniciar transacción
        beginTransaction(ActionUtil.getDBUser());

        try {
            // Procesar elementos seleccionados utilizando streams
            ws.getInscripcionAdicionalLogroList().stream()
                    .filter(logro -> parameters.containsKey("ck_" + ws.getInscripcionAdicionalLogroList().indexOf(logro)))
                    .forEach(inscripcionAdicionalPersistence::makeTransient);

            // Confirmar la transacción
            commitTransaction();
        } catch (Exception e) {
            // Revertir la transacción en caso de error
            HibernateUtil.rollbackTransaction();
            throw e; // Relanzar la excepción para manejarla en un nivel superior si es necesario
        }

        return SUCCESS;
    }
}
