/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.TareaActividadTeletrabajo;
import java.util.Map;
import domain.repository.TareaActividadTeletrabajoPersistence;
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
public class TeleTrabajoRemoveTareaService {

    public static String service(ActionCommonSupport action, GenericSession genericSession, TeleTrabajoSession teleSesion, Map<String, String[]> parameters, String key) {

            TareaActividadTeletrabajoPersistence persistence = ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence(ActionUtil.getDBUser());

            beginTransaction("TT");

            for (int i = 0; i < teleSesion.getTareaList().size(); i++) {
                if (parameters.get("ck_" + i) != null) {
                    LogUtil.setLog(genericSession.getRut());
                    persistence.deleteTarea(teleSesion.getTareaList().get(i).getId());
                }
            }

            commitTransaction();

            teleSesion.setTareaList(persistence.find(genericSession.getRut(), teleSesion.getActividadTeletrabajo().getId().getAtelFecha()));
            
            if(teleSesion.getTareaList().isEmpty())
            {
                teleSesion.getActividadTeletrabajo().setAtelEstado("A");

                beginTransaction("TT");
                ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").update(teleSesion.getActividadTeletrabajo());
                commitTransaction();
            }
            else
            {
                Boolean isNoEvidenciado = true;
                for(TareaActividadTeletrabajo tarea : teleSesion.getTareaList())
                {
                    if(tarea.getTatelEvidencia().equals("S"))
                    {
                        isNoEvidenciado = false;
                        break;
                    }
                }
                
                if(isNoEvidenciado)
                {
                    teleSesion.getActividadTeletrabajo().setAtelEstado("A");

                    beginTransaction("TT");
                    ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").update(teleSesion.getActividadTeletrabajo());
                    commitTransaction();
                }
            }

        return SUCCESS;
    }
}
