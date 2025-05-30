/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.common.CommonCursoUtil;

/**
 *
 * @author Administrador
 */
public class CommonCursoGetCursosxAgnoSemEncuestaService {
    public static String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem) {

        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);        
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());
        ws.setMiCarreraSupport(miCarreraSupport);
        CommonCursoUtil.getCursosProf(genericSession, "*", key); //Cerrados, Transversales y Espejos

        return SUCCESS;
    }
}
