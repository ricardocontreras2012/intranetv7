/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.CursoId;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionToolService {

    public String serviceGet(GenericSession genericSession, String busqueda, String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        CursoId id = ws.getCurso().getId();

        switch (busqueda) {
            case "HOR":
                ws.setHorarioList(ContextUtil.getDAO().getHorarioRepository(ActionUtil.getDBUser()).getHorario(id));
                ws.setSalaList(ContextUtil.getDAO().getSalaRepository(ActionUtil.getDBUser()).findPropias(genericSession.getRut()));
                break;
            case "PR":
                ws.setDocenteHorarioList(ContextUtil.getDAO().getDocenteHorarioRepository(ActionUtil.getDBUser()).findDocente(id, "T"));
                ws.setHorarioList(ContextUtil.getDAO().getHorarioRepository(ActionUtil.getDBUser()).findxTipo(id, 'C'));
                break;
            case "AY":
                ws.setDocenteHorarioList(ContextUtil.getDAO().getDocenteHorarioRepository(ActionUtil.getDBUser()).findDocente(id, "E"));
                ws.setHorarioList(ContextUtil.getDAO().getHorarioRepository(ActionUtil.getDBUser()).findxTipo(id, 'A'));
        }
        return SUCCESS;
    }
}