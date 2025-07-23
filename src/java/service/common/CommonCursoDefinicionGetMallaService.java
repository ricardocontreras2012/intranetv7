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
import infrastructure.util.common.CommonMallaUtil;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionGetMallaService {

    public String service(GenericSession genericSession, String key) {

        WorkSession ws = genericSession.getWorkSession(key);

        MiCarreraSupport carrera = ws.getMiCarreraSupport();
       
        ws.setAsignaturaList(CommonMallaUtil.getAsignaturasMalla(ContextUtil.getDAO().getMallaRepository(ActionUtil.getDBUser()).getMalla(carrera.getTcrCtip(), carrera.getEspCod(), carrera.getRegimen(), genericSession.getRut(), genericSession.getUserType())));

        return SUCCESS;
    }
}
