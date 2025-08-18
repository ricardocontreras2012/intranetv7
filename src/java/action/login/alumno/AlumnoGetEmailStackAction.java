/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.login.alumno;

import service.login.alumno.AlumnoGetEmailStackService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoGetEmailStackAction extends ActionCommonSupport {

    @Override
    public String action() {
        return new AlumnoGetEmailStackService().service(getGenericSession(), getKey());
    }
}
