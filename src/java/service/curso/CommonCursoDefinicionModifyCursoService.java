/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso;

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
public class CommonCursoDefinicionModifyCursoService {
/**
     * Method Servicio
     *
     * @param genericSession
     * @param pos
     * @param cupo
     * @param inicio
     * @param termino
     * @param diurno
     * @param vesp
     * @param key
     * @return Action status
     * @throws java.lang.Exception
     */
    public String service(GenericSession genericSession, Integer pos, Integer cupo, String inicio, String termino,
            String diurno, String vesp, String key) throws Exception{
        WorkSession ws = genericSession.getWorkSession(key);

        CursoId id = ws.getCursoList().get(pos).getId();

        String jornada = "";
        if (diurno != null) {
            jornada += diurno;
        }

        if (vesp != null) {
            jornada += vesp;
        }
        
        ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).modifyCurso(id.getCurAsign(), id.getCurElect(), id.getCurCoord(), id.getCurSecc(), ws.getAgnoAct(), ws.getSemAct(), cupo, inicio, termino, genericSession.getRut(), jornada);
        
        ws.getCursoList().set(pos, ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).find(id));
        ws.setPos(pos);
        return SUCCESS;
    }
}

