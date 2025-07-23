/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Malla;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonMallaUtil;

/**
 *
 * @author Administrador
 */
public class CommonCursoDefinicionGetElectivosService {
   public String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem) {

        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport carrera = ws.getMiCarreraSupportList().get(pos);
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        
        ws.setNombreCarrera(carrera.getNombreCarrera());
        ws.setMiCarreraSupport(carrera);
        
        List<Malla> mallaList = ContextUtil.getDAO().getMallaRepository(ActionUtil.getDBUser()).getElectivosMalla(
                carrera.getTcrCtip(), carrera.getEspCod(), carrera.getRegimen(), genericSession.getRut(), genericSession.getUserType());
        ws.setAsignaturaList(CommonMallaUtil.getAsignaturasMalla(mallaList));        
        ws.setElectivoList(ContextUtil.getDAO().getElectivoRepository(ActionUtil.getDBUser()).find(carrera.getTcrCtip(), carrera.getEspCod(), agno, sem, genericSession.getRut(), genericSession.getUserType()));
                
        return SUCCESS;
    }
}
