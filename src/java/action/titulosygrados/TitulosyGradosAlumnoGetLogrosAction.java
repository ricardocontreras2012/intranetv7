/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.titulosygrados;

import infrastructure.support.action.common.ActionCommonSupport;
import static service.titulosygrados.TitulosyGradosAlumnoGetLogrosService.service;

/**
 *
 * @author Ricardo
 */
public class TitulosyGradosAlumnoGetLogrosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey());
    }
}
