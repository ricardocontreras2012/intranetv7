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
import infrastructure.util.LogUtil;

/**
 *
 * @author Administrador
 */
public class CommonMatriculaGetNominaService {

    public String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem) {

        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());

        Integer tipoCarrera = miCarreraSupport.getTcrCtip();
        Integer especialidad = miCarreraSupport.getEspCod();
        String regimen = miCarreraSupport.getRegimen();

        ws.setMiCarreraSupport(miCarreraSupport);
        ws.setMatriculaList(ContextUtil.getDAO().getMatriculaHistoricoPersistence(ActionUtil.getDBUser()).find(
                agno, sem, tipoCarrera, especialidad, regimen));
        
        LogUtil.setLog(genericSession.getRut(), miCarreraSupport.getNombreCarrera());

        return SUCCESS;
    }
}
