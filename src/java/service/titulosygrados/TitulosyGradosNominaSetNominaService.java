/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ExpedienteLogro;
import domain.model.ExpedienteNomina;
import domain.model.Logro;
import domain.model.PlanLogro;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;

/**
 *
 * @author rcontreras
 */
public class TitulosyGradosNominaSetNominaService {

    public String service(GenericSession genericSession, Integer tipo, Integer agno, String nomina, String fecha, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        if (ws.getExpedienteLogro() == null) {
            ExpedienteLogro expedienteLogro = new ExpedienteLogro();
            PlanLogro planLogro = new PlanLogro();
            expedienteLogro.setPlanLogro(planLogro);
            ExpedienteNomina expedienteNomina = new ExpedienteNomina();

            Logro logro = ContextUtil.getDAO().getLogroPersistence(ActionUtil.getDBUser()).find(tipo);
            logro.setLogrCod(tipo);

            expedienteNomina.setExpnCorrel(ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSecuenciaExpedienteNomina());
            expedienteNomina.setExpnNumero(nomina);
            expedienteNomina.setExpnAgno(agno);
            expedienteNomina.setExpnFecha(DateUtil.stringToDate(fecha));
            expedienteNomina.setExpnLogro(tipo);
            expedienteLogro.setNomina(expedienteNomina);
            expedienteLogro.getPlanLogro().setLogro(logro);

            ws.setExpedienteLogro(expedienteLogro);

        }
        return SUCCESS;
    }

}
