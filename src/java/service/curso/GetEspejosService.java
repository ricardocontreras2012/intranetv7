/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.common.CommonCursoUtil;

/**
 *
 * @author Ricardo
 */
public class GetEspejosService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param agno
     * @param sem
     * @param actionCall
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem, String actionCall) {
        
        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());
        ws.setMiCarreraSupport(miCarreraSupport);
        ws.setActionCall(actionCall);
        CommonCursoUtil.getCursos(genericSession, "E", key); //Espejos
        CommonCursoUtil.getCursos(genericSession, "T*", key); //Todos los Transversales
        
        return SUCCESS;
    }
}
