/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Electivo;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ActionResultSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionGetElectivoService {

    public static String service(GenericSession genericSession, Integer asign, String elect, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ActionResultSupport actionSupport = new ActionResultSupport();
        actionSupport.setActionResult("");
        actionSupport.setActionStatus("NULL");

        Electivo electivo = ContextUtil.getDAO().getElectivoPersistence(ActionUtil.getDBUser()).find(asign, elect, ws.getAgnoAct(), ws.getSemAct());

        if (electivo != null) {
            actionSupport.setActionResult(electivo.getEleNom());
            actionSupport.setActionStatus("Success");
        }
        ws.setActionSupport(actionSupport);

        return SUCCESS;
    }

}
