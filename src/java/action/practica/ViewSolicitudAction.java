/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.practica;
import service.practica.ViewSolicitudService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author rcontreras
 */
public class ViewSolicitudAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ViewSolicitudService().service(getGenericSession(), getKey(), getPos());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getSolicitudList());
    }
}

