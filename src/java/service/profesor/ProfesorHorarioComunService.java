/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonHorarioUtil;
import session.GenericSession;
import session.ProfesorSession;
import session.WorkSession;

/**
 *
 * @author Usach
 */
public class ProfesorHorarioComunService {

    public String service(GenericSession genericSession, ProfesorSession ps, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        Curso curso = ws.getCurso();

        String hor = ContextUtil.getDAO().getHorarioPersistence(ActionUtil.getDBUser()).getHorarioCoumn(curso.getId());
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioList(ws.getCursoList()));
        ps.setHorarioComun(hor);        
        
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());
        return SUCCESS;
    }
}
