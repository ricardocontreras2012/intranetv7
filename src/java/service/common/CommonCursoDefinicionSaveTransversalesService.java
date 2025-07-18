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
import java.util.function.Consumer;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionSaveTransversalesService {

    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtener la lista de cursos
        List<Curso> cursoList = ws.getCursoList();

        // Iniciar la transacción para agregar y eliminar transversales
        beginTransaction(ActionUtil.getDBUser());

        // Agregar transversales
        procesarTransversales(cursoList, parameters, true,
                (id) -> ContextUtil.getDAO().getCursoPersistence(ActionUtil.getDBUser())
                        .addTransversal(id.getCurAsign(), id.getCurElect(), id.getCurCoord(),
                                id.getCurSecc(), id.getCurAgno(), id.getCurSem()));

        // Eliminar transversales
        procesarTransversales(cursoList, parameters, false,
                (id) -> ContextUtil.getDAO().getCursoPersistence(ActionUtil.getDBUser())
                        .removeTransversal(id.getCurAsign(), id.getCurElect(), id.getCurCoord(),
                                id.getCurSecc(), id.getCurAgno(), id.getCurSem()));

        // Confirmar transacciones
        commitTransaction();

        // Actualizar la lista de cursos (cerrados, transversales y espejos)
        CommonCursoUtil.getCursos(genericSession, "*", key);

        return SUCCESS;
    }

    /**
     * Procesa la adición o eliminación de transversales en la lista de cursos
     * utilizando una expresión lambda que define la operación que se debe
     * realizar.
     *
     * @param cursoList La lista de cursos a procesar.
     * @param parameters Los parámetros que determinan si se debe agregar o
     * eliminar el curso.
     * @param agregar Si es `true`, agrega transversales; si es `false`, elimina
     * transversales.
     * @param action La operación a realizar (agregar o eliminar transversal).
     */
    private void procesarTransversales(List<Curso> cursoList, Map<String, String[]> parameters,
            boolean agregar, Consumer<CursoId> action) {
        cursoList.stream()
                .filter(curso -> (agregar && parameters.get("ck_" + cursoList.indexOf(curso)) != null)
                || (!agregar && parameters.get("ck_" + cursoList.indexOf(curso)) == null))
                .forEach(curso -> {
                    CursoId id = curso.getId();
                    if ((agregar && "C".equals(curso.getCurTipo())) || (!agregar && "T".equals(curso.getCurTipo()))) {
                        // Realizar la operación definida por la lambda
                        action.accept(id);
                    }
                });
    }

}
