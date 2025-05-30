/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariaproyectos;

import com.opensymphony.xwork2.Action;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ActionResultSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioTopeHorarioService {

    public static String service(GenericSession genericSession, Integer rut, String dia,
            String fechaInicio, String fechaTermino,
            String horaInicio, String horaTermino, String key) {
        
        WorkSession ws = genericSession.getWorkSession(key);
        String tope = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getTopeHorarioConvenio(rut, dia, fechaInicio, fechaTermino, horaInicio, horaTermino);

        ActionResultSupport actionSupport = new ActionResultSupport();

        actionSupport.setActionResult("");
        if (tope == null || "OK".equals(tope)) {
            actionSupport.setActionStatus("Success");
        } else {
            actionSupport.setActionStatus("Error");
            actionSupport.setActionErrorMsg(tope);
        }

        ws.setActionSupport(actionSupport);

        return Action.SUCCESS;
    }
}
