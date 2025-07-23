/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import static infrastructure.util.FormatUtil.isNotNull;
import infrastructure.util.HibernateUtil;
import infrastructure.util.common.CommonActaUtil;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaActaEmitirService {

    public String service(ActionCommonSupport action, GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        // Extraer parámetros con validación
        Integer agno = parseInt(parameters.get("agno")[0]);
        Integer sem = parseInt(parameters.get("sem")[0]);
        Integer practica = parseInt(parameters.get("practica")[0]);
        Integer porcEmp = parseInt(parameters.get("porc_emp")[0]);
        Integer porcCoord = parseInt(parameters.get("porc_coord")[0]);

        // Inicializar variables
        beginTransaction(ActionUtil.getDBUser());
        Integer folio = crearActa(practica, agno, sem, porcEmp, porcCoord);

        try {
            // Procesar prácticas seleccionadas utilizando streams
            ws.getPracticaList().stream()
                    .filter(practicaItem -> isNotNull(parameters.get("emp_" + ws.getPracticaList().indexOf(practicaItem)))
                    && isNotNull(parameters.get("coord_" + ws.getPracticaList().indexOf(practicaItem))))
                    .forEach(practicaItem -> {
                        BigDecimal notaEmp = new BigDecimal(parameters.get("emp_" + ws.getPracticaList().indexOf(practicaItem))[0].replace(",", "."));
                        BigDecimal notaCoord = new BigDecimal(parameters.get("coord_" + ws.getPracticaList().indexOf(practicaItem))[0].replace(",", "."));

                        // Agregar a la nómina
                        ContextUtil.getDAO().getPracticaRepository(ActionUtil.getDBUser())
                                .agregarNomina(practicaItem.getAluCar().getId(), folio, notaEmp, notaCoord);
                    });

            // Confirmar la transacción
            commitTransaction();

            // Mensaje de éxito
            action.addActionMessage(action.getText("message.acta.emitida"));
            return SUCCESS;

        } catch (Exception e) {
            // Revertir la transacción en caso de error
            HibernateUtil.rollbackTransaction();
            throw e; // Relanzar excepción para manejo superior si es necesario
        }
    }

    private Integer crearActa(Integer practica, Integer agno, Integer sem, Integer porcEmp, Integer porcCoord) {
        Integer folio = CommonActaUtil.getFolio(ActionUtil.getDBUser());
        ContextUtil.getDAO().getPracticaRepository(ActionUtil.getDBUser()).crearActa(folio, practica, agno, sem, porcEmp, porcCoord, "N");

        return folio;
    }
}
