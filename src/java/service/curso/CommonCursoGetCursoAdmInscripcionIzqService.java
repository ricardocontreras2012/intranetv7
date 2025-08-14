/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.JefeCarreraSession;
import session.WorkSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Felipe
 */
public final class CommonCursoGetCursoAdmInscripcionIzqService {

    /**
     * Method Servicio
     *
     * @param genericSession
     * @param jcSession
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, JefeCarreraSession jcSession, Integer pos, String key) {

        WorkSession ws = genericSession.getWorkSession(key);

        jcSession.setPosIzq(pos);
        jcSession.setCursoAdmIzq(ws.getCursoList().get(pos));
        jcSession.setPosDer(-1);
        LogUtil.setLogCurso(genericSession.getRut(), jcSession.getCursoAdmIzq());

        return SUCCESS;
    }
}
