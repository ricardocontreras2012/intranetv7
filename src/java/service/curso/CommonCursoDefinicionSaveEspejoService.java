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
import infrastructure.util.common.CommonCursoUtil;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionSaveEspejoService {

    public String service(GenericSession genericSession, Integer transversal, Integer cerrado, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        CursoId ct = ws.getCursoTransversalFullList().get(transversal).getId();
        CursoId cc = ws.getCursoList().get(cerrado).getId();

        ContextUtil.getDAO().getCursoEspejoRepository(ActionUtil.getDBUser()).
                add(ct.getCurAsign(), ct.getCurElect(), ct.getCurCoord(), ct.getCurSecc(), ct.getCurAgno(), ct.getCurSem(),
                        cc.getCurAsign(), cc.getCurElect(), cc.getCurCoord(), cc.getCurSecc(), cc.getCurAgno(), cc.getCurSem());


        CommonCursoUtil.getCursos(genericSession, "E", key); //Espejos
        CommonCursoUtil.getCursos(genericSession, "T*", key); //Transversales

        return SUCCESS;
    }

}
