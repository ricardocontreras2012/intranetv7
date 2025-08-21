/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.curso;

import infrastructure.support.action.ActionValidationAgnoSemSupport;
import service.curso.GetTransversalesxAgnoSemService;

/**
 *
 * @author Ricardo
 */
public class GetTransversalesxAgnoSemAction extends ActionValidationAgnoSemSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetTransversalesxAgnoSemService().service(getGenericSession(), getKey(), getPos(), getAgno(), getSem(), actionCall);
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getMiCarreraSupportList());
    }

    /**
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}
