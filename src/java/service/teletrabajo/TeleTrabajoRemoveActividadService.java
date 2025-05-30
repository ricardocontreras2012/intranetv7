/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import domain.repository.ActividadTeletrabajoPersistence;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 *
 * @author Administrador
 */
public class TeleTrabajoRemoveActividadService {

    public static String service(ActionCommonSupport action, GenericSession genericSession, TeleTrabajoSession teleSesion,
            Map<String, String[]> parameters, String key) {

        ActividadTeletrabajoPersistence persistence = ContextUtil.getDAO().getActividadTeletrabajoPersistence(ActionUtil.getDBUser());

        beginTransaction("TT");

        for (int i = 0; i < teleSesion.getActividadList().size(); i++) {
            if (parameters.get("ck_" + i) != null) {
                if(teleSesion.getActividadList().get(i).getAtelEstado().equals("C") || teleSesion.getActividadList().get(i).getAtelEstado().equals("R"))
                {
                    LogUtil.setLog(genericSession.getRut(), teleSesion.getActividadList().get(i).getId().getAtelRut());
                    persistence.deleteActividad(teleSesion.getActividadList().get(i).getId());
                }
            }
        }

        commitTransaction();

        teleSesion.setActividadList(persistence.find(genericSession.getRut(), teleSesion.getFuncionarioTeletrabajo().getFtelRut()));
       
        return SUCCESS;
    }

}
