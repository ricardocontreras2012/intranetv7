/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static service.alumno.AlumnoLoginEmailService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoLoginEmailAction  extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String email;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return service(getGenericSession(), email, getKey());
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
