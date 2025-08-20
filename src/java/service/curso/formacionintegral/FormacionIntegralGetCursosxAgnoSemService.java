/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso.formacionintegral;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonHorarioUtil;

/**
 *
 * @author Administrador
 */
public class FormacionIntegralGetCursosxAgnoSemService {

    public String service(GenericSession genericSession, String key, Integer agno, Integer sem) {

        WorkSession ws = genericSession.getWorkSession(key);

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);

        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(agno, sem));
        ws.setCursoList(ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).findxPerfilPeriodo(agno, sem, genericSession.getRut(), genericSession.getUserType()));              
        ws.setAsignaturaList(ContextUtil.getDAO().getAsignaturaRepository(ActionUtil.getDBUser()).findFI(genericSession.getRut(), genericSession.getUserType()));

        return SUCCESS;
    }
}
