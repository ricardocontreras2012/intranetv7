/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import infrastructure.support.action.common.ActionCommonSupport;
import service.common.CommonSalaReservaGetReservaService;

/**
 *
 * @author rcontreras
 */
public class CommonSalaReservaGetReservaAction extends ActionCommonSupport {

    private Integer reserva;

    @Override
    public String action() throws Exception {
        return new CommonSalaReservaGetReservaService().service(getGenericSession(), reserva, getKey());
    }

    public Integer getReserva() {
        return reserva;
    }

    public void setReserva(Integer reserva) {
        this.reserva = reserva;
    }
}
