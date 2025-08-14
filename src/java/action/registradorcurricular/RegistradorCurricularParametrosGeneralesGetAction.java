/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import service.parametro.registradorcurricular.RegistradorCurricularParametrosGeneralesGetService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Usach
 */
public class RegistradorCurricularParametrosGeneralesGetAction extends ActionCommonSupport {
    
    @Override
    public String action() throws Exception {
        return new RegistradorCurricularParametrosGeneralesGetService().service(getGenericSession(), Manager.getRegistradorSession(sesion));
    }

}
