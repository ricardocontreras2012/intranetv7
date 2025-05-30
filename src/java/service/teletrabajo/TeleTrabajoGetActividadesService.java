/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.FuncionarioTeletrabajo;
import java.text.ParseException;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class TeleTrabajoGetActividadesService {
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, Integer pos, String key) throws ParseException {
        FuncionarioTeletrabajo funTel = teleSession.getFuncionarioList().get(pos);
        teleSession.setFuncionarioTeletrabajo(funTel);
        teleSession.setActividadList(ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").find(genericSession.getRut(), funTel.getFtelRut()));

        return SUCCESS;
    }
}
