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
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonHorarioUtil;

/**
 *
 * @author Javier Frez V.
 */
public class CommonCursoGetBoletinService {
    public String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(agno, sem));
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());
        ws.setMiCarreraSupport(miCarreraSupport);
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        
        ws.setAluCarList(ContextUtil.getDAO().getAluCarPersistence(ActionUtil.getDBUser()).find(miCarreraSupport.getTcrCtip(), miCarreraSupport.getEspCod(), miCarreraSupport.getRegimen()));
        
        return SUCCESS;
    }
}
