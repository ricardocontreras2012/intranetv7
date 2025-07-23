/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class CommonCursoDefinicionRemoveEspejosService {

    /**
     * Method Servicio
     *
     * @param genericSession
     * @param parameters
     * @param key
     * @return Action status
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        beginTransaction(ActionUtil.getDBUser());

        // Eliminar los curso espejo seleccionados
        ws.getCursoEspejoList().stream()
                .filter(ce -> parameters.containsKey("ck_" + ws.getCursoEspejoList().indexOf(ce))) // Verificar si el parámetro existe
                .forEach(ce -> {
                    // Realizar la eliminación del curso espejo
                    ContextUtil.getDAO().getCursoEspejoRepository(ActionUtil.getDBUser()).remove(
                            ce.getCesAsignTr(),
                            ce.getCesElectTr(),
                            ce.getCesCoordTr(),
                            ce.getCesSeccTr(),
                            ce.getCesAgnoTr(),
                            ce.getCesSemTr(),
                            ce.getId().getCesAsign(),
                            ce.getId().getCesElect(),
                            ce.getId().getCesCoord(),
                            ce.getId().getCesSecc(),
                            ce.getId().getCesAgno(),
                            ce.getId().getCesSem()
                    );
                });

        commitTransaction();

        // Actualizar los cursos: espejos y transversales
        CommonCursoUtil.getCursos(genericSession, "E", key); // Espejos
        CommonCursoUtil.getCursos(genericSession, "T*", key); // Transversales

        return SUCCESS;
    }
}
