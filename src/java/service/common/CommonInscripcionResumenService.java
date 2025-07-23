/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import session.GenericSession;
import session.JefeCarreraSession;
import session.WorkSession;

/**
 *
 * @author Usach
 */
public class CommonInscripcionResumenService {
    public String service(GenericSession genericSession, JefeCarreraSession js, int pos, int agno, int sem, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        String userType = genericSession.getUserType();
        Integer rut = genericSession.getRut();
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);

        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);
        ws.setMiCarreraSupport(miCarreraSupport);
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());
        
        Integer tipoCarrera = miCarreraSupport.getTcrCtip();
        Integer especialidad = miCarreraSupport.getEspCod();
        String regimen = miCarreraSupport.getRegimen();
        
        String json = ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).getResumen(tipoCarrera, especialidad, regimen, ws.getAgnoAct(), ws.getSemAct(), rut, userType);
        js.setJson(json);     
        
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
