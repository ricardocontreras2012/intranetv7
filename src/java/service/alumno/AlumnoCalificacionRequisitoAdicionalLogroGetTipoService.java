/*
 * @(#)AlumnoCalificacionRequisitoAdicionalLogroGetTipoService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.TrequisitoLogroAdicional;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.CalificacionAdicionalLogroxInscribirSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ContextUtil;

/**
 * Servicio para obtener el tipo de requisito adicional de logro de un alumno.
 *
 * Esta clase proporciona un servicio que maneja la obtención del tipo de requisito
 * adicional de logro de un alumno específico, validando si el alumno está activo
 * y actualizando la sesión con la información correspondiente. 
 * Si el alumno está activo y el flag de la calificación es "S", se obtiene el requisito 
 * adicional y se establece en la sesión de trabajo.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoCalificacionRequisitoAdicionalLogroGetTipoService {

    /**
     * Método de servicio que maneja la lógica de obtención de requisito adicional de logro.
     *
     * Este método procesa la solicitud para obtener el tipo de requisito adicional de logro
     * para un alumno específico, basándose en su posición dentro de una lista y en su estado activo.
     * Si el alumno está activo y el flag de la calificación es "S", se establece el requisito
     * en la sesión de trabajo. En caso contrario, se devuelve un error.
     *
     * @param action Clase de acción que invoca el servicio, utilizada para agregar mensajes de error.
     * @param genericSession Sesión genérica de trabajo que proporciona la información de sesión actual.
     * @param pos Número del registro seleccionado en el formulario, utilizado para obtener el elemento correspondiente.
     * @param key Llave para acceder a los datos dentro de la sesión de trabajo.
     * @return El estado de la acción, puede ser SUCCESS, ERROR, o "noActivo" en caso de que el alumno no esté activo.
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession,
            Integer pos, String key) {
        String retValue = ERROR;

        // Obtiene la sesión de trabajo actual
        WorkSession ws = genericSession.getWorkSession(key);
        
        // Obtiene la calificación correspondiente según la posición seleccionada
        CalificacionAdicionalLogroxInscribirSupport calificacion =
                ws.getCalificacionRequisitoAdicionalLogroxInscribirList().get(pos);

        // Verifica si el alumno está activo
        if (ws.getAluCar().estaActivo()) {
            // Si el flag de la calificación es "S", se procede a establecer el requisito adicional
            if ("S".equals(calificacion.getFlag())) {
                TrequisitoLogroAdicional requisito = new TrequisitoLogroAdicional();

                // Establece el código y la descripción del requisito adicional
                requisito.setTrlaCod(calificacion.getTipo());
                requisito.setTrlaDes(calificacion.getDes().toUpperCase(ContextUtil.getLocale()));

                // Actualiza el requisito adicional en la sesión de trabajo
                ws.setTrequisitoLogroAdicional(requisito);

                retValue = SUCCESS; // Acción exitosa
            }
        } else {
            // Si el alumno no está activo, se agrega un mensaje de error
            action.addActionError(action.getText("error.alumno.no.activo"));
            retValue = "noActivo"; // Estado de error por alumno no activo
        }

        return retValue;
    }
}
