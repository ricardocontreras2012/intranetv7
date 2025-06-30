/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Funcionario;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import domain.repository.FuncionarioPersistence;
import session.GenericSession;
import session.TeleTrabajoSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.ActionUtil.retReLogin;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Administrador
 */
public class TeleTrabajoLoginService {

    public static String service(ActionCommonSupport action, Map<String, Object> sesion, Integer rut, String passwd, String key) {
        String retValue = SUCCESS;

        try {
            if (sesion != null && key != null) {

                FuncionarioPersistence funcionarioPersistence
                        = ContextUtil.getDAO().getFuncionarioPersistence(ActionUtil.getDBUser());

                Funcionario funcionario = funcionarioPersistence.findTeletrabajo(rut, passwd);

                if (funcionario != null) {
                    GenericSession genericSession = new GenericSession(ActionUtil.getDBUser(), rut, passwd, 0);

                    WorkSession ws = new WorkSession(ActionUtil.getDBUser());

                    genericSession.setSessionMap(new HashMap<>());
                    genericSession.getSessionMap().put(key, ws);
                    genericSession.setDv(funcionario.getFunDv());
                    genericSession.setPaterno(funcionario.getFunPaterno());
                    genericSession.setMaterno(funcionario.getFunMaterno());
                    genericSession.setNombres(funcionario.getFunNombre());
                    genericSession.setNombre(funcionario.getNombre());

                    ServletActionContext.getRequest().getSession().setMaxInactiveInterval(1800);

                    TeleTrabajoSession teleTrabajoSession = new TeleTrabajoSession();

                    // Usuario validado y asignacion de su contexto de trabajo.
                    sesion.put("genericSession", genericSession);
                    sesion.put("teleTrabajoSession", teleTrabajoSession);

                    LogUtil.setLog(rut);

                } else {
                    action.addActionError(action.getText("error.rut.password"));
                    retValue = retError();
                }
            } else {
                retValue = retReLogin();
            }

            return retValue;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
