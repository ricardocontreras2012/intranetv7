/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.CursoId;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.CursoResumenSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonCursoUtil;

/**
 *
 * @author rcontreras
 */
public class CommonCursoGetCursoResumenService {

    public static String service(GenericSession genericSession, Integer pos, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        CursoResumenSupport crs = ws.getResumenCurso().get(pos);

        CursoId id = new CursoId();

        id.setCurAsign(crs.getAsign());
        id.setCurElect(crs.getElect());
        id.setCurCoord(crs.getCoord());
        id.setCurSecc(crs.getSecc());
        id.setCurAgno(crs.getAgno());
        id.setCurSem(crs.getSem());

        id =  CommonCursoUtil.getIdTransversal(id);
        Curso curso = ContextUtil.getDAO().getCursoPersistence(ActionUtil.getDBUser()).find(id);

        curso.setId(id);

        ws.setCurso(curso);
        return SUCCESS;

    }
}
