/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.inscripcion.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.LogUtil;
import session.AlumnoSession;

/**
 *
 * @author Administrador
 */
public class AlumnoSwapInscripcionService {

    /**
     *
     * @param genericSession
     * @param as
     * @param pos
     * @param key
     * @return
     */
    public String service(GenericSession genericSession, AlumnoSession as, Integer pos, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();

        aluCar.swapInscripcion(genericSession, ws.getCurso().getId(), ws.getCursoList().get(pos).getId());
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }

}
