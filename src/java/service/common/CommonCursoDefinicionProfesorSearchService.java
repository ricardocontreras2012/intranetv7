/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.util.common.CommonProfesorUtil.getProfesorPersona;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionProfesorSearchService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param rut
     * @param paterno
     * @param materno
     * @param nombre
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer rut, String paterno, String materno,
            String nombre, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setProfesorList(getProfesorPersona(rut, paterno, materno, nombre));
        return SUCCESS;
    }
}
