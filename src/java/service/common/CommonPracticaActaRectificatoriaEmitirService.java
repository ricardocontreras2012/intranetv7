/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.PracticaRepository;
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
import infrastructure.util.common.CommonActaUtil;
import java.util.stream.IntStream;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaActaRectificatoriaEmitirService {

    public String service(ActionCommonSupport action, GenericSession genericSession, Map<String, String[]> parameters, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        Integer agno = parseInt(parameters.get("agno")[0]);
        Integer sem = parseInt(parameters.get("sem")[0]);
        Integer practica = parseInt(parameters.get("practica")[0]);
        Integer porc_emp = parseInt(parameters.get("porc_emp")[0]);
        Integer porc_coord = parseInt(parameters.get("porc_coord")[0]);

        String retValue = SUCCESS;

        Integer folio;
        folio = crearActa(practica, agno, sem, porc_emp, porc_coord);
        
        PracticaRepository practicaRepository = ContextUtil.getDAO().getPracticaRepository(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());
        IntStream.range(0, ws.getPracticaList().size())
            .filter(i -> isNotNull(parameters.get("emp_" + i)) && isNotNull(parameters.get("coord_" + i)))
            .forEach(i -> {
                BigDecimal notaEmp = new BigDecimal(parameters.get("emp_" + i)[0].replace(",", "."));
                BigDecimal notaCoord = new BigDecimal(parameters.get("coord_" + i)[0].replace(",", "."));

                practicaRepository.agregarNomina(
                        ws.getPracticaList().get(i).getAluCar().getId(), folio, notaEmp, notaCoord);
            });
        commitTransaction();
        action.addActionMessage(action.getText("message.acta.emitida"));

        return retValue;
    }

    private Integer crearActa(Integer practica, Integer agno, Integer sem, Integer porcEmp, Integer porcCoord) {
        Integer folio = CommonActaUtil.getFolio(ActionUtil.getDBUser());
        ContextUtil.getDAO().getPracticaRepository(ActionUtil.getDBUser()).crearActa(folio, practica, agno, sem, porcEmp, porcCoord,"R");

        return folio;
    }
}
