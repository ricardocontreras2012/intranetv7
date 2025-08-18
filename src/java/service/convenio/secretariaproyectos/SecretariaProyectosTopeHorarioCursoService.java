/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convenio.secretariaproyectos;

import com.opensymphony.xwork2.Action;
import domain.model.Curso;
import domain.model.CursoId;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ActionResultSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosTopeHorarioCursoService {

    public String service(GenericSession genericSession, Integer rut, Integer pos, String inicio, String termino, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ActionResultSupport actionSupport = new ActionResultSupport();
        Curso curso = ws.getCursoList().get(pos);
        CursoId id = curso.getId();
        ws.setCurso(curso);
        String tope = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getTopeHorarioConvenioCurso(rut, inicio, termino, id.getCurAsign(), id.getCurElect(), id.getCurCoord(), id.getCurSecc(), id.getCurAgno(), id.getCurSem());

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
