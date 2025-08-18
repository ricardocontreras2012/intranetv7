/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.ayudante.GetCargaHistoricaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class CommonAyudanteCargaHistoricaEncuestaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall = "CommonAyudanteGetEncuesta";

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetCargaHistoricaService().service(getGenericSession(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     * Method description
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}
