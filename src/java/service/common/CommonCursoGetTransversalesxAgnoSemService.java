/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.common.CommonCursoUtil;
import infrastructure.util.common.CommonHorarioUtil;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo
 */
public class CommonCursoGetTransversalesxAgnoSemService {
    public String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem, String actionCall) {

        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(agno, sem));
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());
        ws.setMiCarreraSupport(miCarreraSupport);
        ws.setActionCall(actionCall);
        CommonCursoUtil.getCursos(genericSession, "T", key); //Cerrados, Transversales y Espejos

        return SUCCESS;
    }
    
}
