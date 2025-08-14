/*
 * @(#)ProfesorActaEmitirActaNumericaService.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package service.acta.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.math.BigDecimal;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import domain.model.ActaNominaView;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import domain.repository.ActaCalificacionRepository;

/**
 * Servicio encargado de emitir un acta numérica para los estudiantes,
 * registrando las calificaciones y actualizando el estado de cada acta. El
 * proceso incluye la validación de si se puede emitir el acta, la asignación de
 * calificaciones numéricas y el registro de cambios en la base de datos.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorActaEmitirActaNumericaService {

    /**
     * Método principal que maneja la emisión de un acta numérica para los
     * estudiantes de un curso, asignando las calificaciones y actualizando el
     * estado del acta en la base de datos.
     *
     * @param genericSession Sesión de trabajo.
     * @param map Mapa con los parámetros recibidos de la interfaz (por ejemplo,
     * calificaciones).
     * @param key Llave para acceder a los datos de la sesión.
     * @return El estado de la acción, indicando si el proceso fue exitoso.
     */
    public String service(GenericSession genericSession, Map<String, String[]> map, String key) {
        // Obtener sesión de trabajo y usuario
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();

        // Persistencia de acta
        ActaCalificacionRepository actaCalifRepository = ContextUtil.getDAO().getActaCalificacionRepository(user);

        if (!ws.isPuedeEmitir()) {
            return SUCCESS;
        }

        beginTransaction(user);

        AtomicInteger nActa = new AtomicInteger(-1);

        // Ordenar lista de actas por folio y procesarlas
        ws.getNominaActa().stream()
                .filter(acta -> "G".equals(acta.getAcalEst())) // Filtrar solo actas generadas (estado "G")
                .sorted(Comparator.comparing(ActaNominaView::getAcalFolio))
                .forEach(acta -> {
                    String[] tmp = map.get("nota_" + acta.getAluRut());
                    if ("".equals(tmp[0])) {
                        tmp = new String[]{"1.0"}; // Si es null, se asigna el array con "1.0"
                    }
                    
                    acta.setAcanFinal(new BigDecimal(tmp[0]));

                    ContextUtil.getDAO().getActaCalificacionNominaRepository(user).putCalificacionNumerica(acta);

                    // Si el folio cambia, actualizar el estado del acta
                    if (acta.getAcalFolio() != nActa.get()) {
                        nActa.set(acta.getAcalFolio());
                        actaCalifRepository.putActaEstado(nActa.get(), acta.getAcalAgno(), acta.getAcalSem(), "E");
                    }
                });

        commitTransaction();

        // Finalizar actualización

        actaCalifRepository.fix(nActa.get());

        // Marcar que el acta no se puede emitir y registrar log
        ws.setPuedeEmitir(false);
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }
}
