/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.CursoId;
import java.util.List;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.common.CommonCursoUtil;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionSaveTransversalesService {

    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<Curso> cursos = ws.getCursoList();

        // Agregar transversales (nuevos)
        beginTransaction(ActionUtil.getDBUser());
        cursos.stream()
              .filter(c -> isChecked(parameters, cursos.indexOf(c)) && "C".equals(c.getCurTipo()))
              .forEach(c -> {
                  CursoId id = c.getId();
                  ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).addTransversal(
                      id.getCurAsign(), id.getCurElect(), id.getCurCoord(),
                      id.getCurSecc(), id.getCurAgno(), id.getCurSem()
                  );
              });
        commitTransaction();

        // Eliminar transversales
        beginTransaction(ActionUtil.getDBUser());
        cursos.stream()
              .filter(c -> !isChecked(parameters, cursos.indexOf(c)) && "T".equals(c.getCurTipo()))
              .forEach(c -> {
                  CursoId id = c.getId();
                  ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).removeTransversal(
                      id.getCurAsign(), id.getCurElect(), id.getCurCoord(),
                      id.getCurSecc(), id.getCurAgno(), id.getCurSem()
                  );
              });
        commitTransaction();

        // Refrescar lista de cursos
        CommonCursoUtil.getCursos(genericSession, "*", key);
        
        
System.out.println("supppppp");

        return SUCCESS;
    }

    private static boolean isChecked(Map<String, String[]> parameters, int index) {
        return parameters.get("ck_" + index) != null;
    }
}
