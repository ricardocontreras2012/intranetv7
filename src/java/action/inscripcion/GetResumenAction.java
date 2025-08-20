/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.inscripcion;

import infrastructure.support.action.common.ActionCommonAgnoSemSupport;
import service.inscripcion.GetResumenResumenService;
import session.Manager;

/**
 *
 * @author Usach
 */
public class GetResumenAction extends ActionCommonAgnoSemSupport {

    private static final long serialVersionUID = 1L;
    private Integer pos;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetResumenResumenService().service(getGenericSession(), Manager.getJefeCarreraSession(sesion), pos, getAgno(), getSem(), getKey());
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }      
}