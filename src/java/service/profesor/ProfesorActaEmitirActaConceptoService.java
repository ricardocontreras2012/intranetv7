/*
 * @(#)ProfesorActaEmitirActaConceptoService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.isNotNull;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import domain.model.ActaNominaView;
import java.util.Comparator;

/**
 * Servicio encargado de emitir un acta de concepto para los estudiantes,
 * registrando las calificaciones conceptuales y actualizando el estado de cada acta.
 * El proceso incluye la validación de si se puede emitir el acta, la asignación de
 * calificaciones conceptuales y el registro de cambios en la base de datos.
 * 
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorActaEmitirActaConceptoService {

    /**
     * Método principal que maneja la emisión de un acta de concepto para los estudiantes
     * de un curso, asignando las calificaciones conceptuales y actualizando el estado
     * del acta en la base de datos.
     *
     * @param genericSession Sesión de trabajo.
     * @param map Mapa con los parámetros recibidos de la interfaz (por ejemplo, calificaciones conceptuales).
     * @param key Llave para acceder a los datos de la sesión.
     * @return El estado de la acción, indicando si el proceso fue exitoso.
     */
    public static String service(GenericSession genericSession, Map<String, String[]> map, String key) {
        int nActa = -1; // Variable para almacenar el número de acta actualizado
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();

        // Verificar si se puede emitir el acta
        if (ws.isPuedeEmitir()) {
            // Iniciar transacción
            beginTransaction(user);
            
            // Ordenar la lista de actas por el folio de acta
            ws.getNominaActa().sort(Comparator.comparing(ActaNominaView::getAcalFolio));

            // Iterar sobre cada acta para asignar calificaciones conceptuales y actualizar estado
            for (ActaNominaView acta : ws.getNominaActa()) {
                if ("G".equals(acta.getAcalEst())) {  // Solo procesar actas con estado "G" (Generada)
                    String[] tmp = map.get("concepto_" + acta.getAluRut());

                    // Si no se proporciona un concepto, asignar un valor por defecto ("SC" para Sin Calificación)
                    if (!isNotNull(tmp)){
                        tmp = new String[1];
                        tmp[0] = "SC";  // Sin Calificación
                    }

                    // Asignar la calificación conceptual a la acta
                    acta.setAcanConcep(tmp[0].toUpperCase(ContextUtil.getLocale()));
                    ContextUtil.getDAO().getActaCalificacionNominaPersistence(user).putCalificacionConcepto(acta);

                    // Si el folio de acta cambia, actualizar el estado del acta
                    if (acta.getAcalFolio() != nActa) {
                        nActa = acta.getAcalFolio();
                        ContextUtil.getDAO().getActaCalificacionPersistence(user).putActaEstado(
                                nActa, acta.getAcalAgno(), acta.getAcalSem(), "E");  // Estado "E" para emitido
                    }
                }
            }

            // Confirmar la transacción
            commitTransaction();

            // Marcar que el acta ya no se puede emitir
            ws.setPuedeEmitir(false);

            // Registrar la acción en el log
            LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());
        }

        // Retornar estado de éxito
        return SUCCESS;
    }
}
