/*
 * @(#)ProfesorActaGetActaService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import domain.model.Curso;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import domain.model.ActaNominaView;

/**
 * Servicio encargado de obtener el acta de un curso, verificando si el acta ha sido generada
 * y si el usuario puede emitirla. Además, se encarga de determinar el tipo de calificación
 * del curso (numérica o por concepto) y establece los valores correspondientes en la sesión de trabajo.
 * 
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorActaGetActaService {

    /**
     * Servicio principal que maneja la obtención del acta y la verificación de condiciones.
     *
     * @param action Clase (action) que invoca al servicio.
     * @param genericSession Sesión de trabajo que contiene los datos necesarios para la operación.
     * @param key Llave para acceder a los datos de la sesión.
     * @return El estado de la acción, indicando si el acta fue generada o no.
     * @throws Exception Si el servicio genera una excepción durante el procesamiento.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        try {
            // Inicializar valores en la sesión
            ws.setActas(null);
            ws.setPuedeEmitir(false);

            Curso curso = ws.getCurso();
            
            // Obtener la lista de actas generadas para el curso
            List<ActaNominaView> actaList = ContextUtil.getDAO().getActaNominaPersistenceView(ActionUtil.getDBUser()).find(curso.getId());
            String retValue;

            // Verificar si la lista de actas está vacía
            if (actaList.isEmpty()) {
                action.addActionMessage(action.getText("message.acta.no.generada"));
                retValue = "acta_no_generada";
            } else {
                // Verificar si se puede emitir el acta
                if (puedeEmitir(actaList)) {
                    ws.setPuedeEmitir(true);
                }

                // Establecer la lista de actas y el tipo de calificación
                ws.setNominaActa(actaList);

                // Establecer el tipo de acta dependiendo del tipo de calificación
                retValue = "N".equals(curso.getAsignatura().getAsiTipoCal())
                        ? "acta_numerica"
                        : "acta_concepto";
            }

            // Registrar el log con la información del curso
            LogUtil.setLogCurso(genericSession.getRut(), curso);
            
            return retValue;
        } catch (Exception e) {
            // En caso de error, restablecer valores y lanzar la excepción
            ws.setActas(null);
            ws.setPuedeEmitir(false);

            throw e;
        }
    }

    /**
     * Método que verifica si al menos una acta tiene el estado "G" (generada), lo que indica
     * que se puede emitir el acta.
     *
     * @param actas Lista de actas para verificar.
     * @return true si se puede emitir el acta (al menos una acta tiene estado "G"), false en caso contrario.
     */
    private static boolean puedeEmitir(List<ActaNominaView> actas) {
        // Usar un Stream para verificar si alguna acta tiene estado "G" (generada)
        return actas.stream()
                .map(ActaNominaView::getAcalEst)  // Obtener el estado de cada acta
                .anyMatch("G"::equals);           // Verificar si al menos una acta tiene estado "G"
    }
}
