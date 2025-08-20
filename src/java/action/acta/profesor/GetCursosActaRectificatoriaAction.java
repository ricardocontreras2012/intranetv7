/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.acta.profesor;

import service.acta.profesor.ProfesorGetCursosActaRectificatoriaService;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 *
 * @author Ricardo
 */
public class GetCursosActaRectificatoriaAction  extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    String actionCall="ProfesorActaRectificatoriaGetNomina";

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String keyParent = getKey();

        setKey(getKeySession());

        return new ProfesorGetCursosActaRectificatoriaService().service(getGenericSession(), getKey(), keyParent);
    }

    public String getActionCall() {
        return actionCall;
    }

    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }
}

