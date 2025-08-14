/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import service.parametromencion.registradorcurricular.RegistradorCurricularParametrosxMencionSaveService;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Usach
 */
public class RegistradorCurricularParametrosxMencionSaveAction extends ActionParameterAwareSupport {

    @Override
    public String action() throws Exception {
        return new RegistradorCurricularParametrosxMencionSaveService().service(getGenericSession(), Manager.getRegistradorSession(sesion), getMapParameters(), getKey());
    }
}
