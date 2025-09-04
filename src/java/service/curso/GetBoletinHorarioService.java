/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonHorarioUtil;
import static infrastructure.util.common.CommonHorarioUtil.getHorario;

/**
 *
 * @author Javier Frez V.
 */
public class GetBoletinHorarioService {
    public String service(GenericSession genericSession, String key, Integer pos) {
        WorkSession ws = genericSession.getWorkSession(key);
        CommonAlumnoUtil.loadAluCar(genericSession, ws, ws.getAluCarList().get(pos));
        ws.setNombre(ws.getAluCar().getAlumno().getAluNombreSocial());
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioList(ws.getAluCar().getCarga()));
        ws.setHorario(getHorario("AL",ws.getModuloHorarioList(), ws.getAluCar().getCarga(), genericSession.getUserType()));
        ws.setIdHorario("AL");
        
        return SUCCESS;
    }
}
