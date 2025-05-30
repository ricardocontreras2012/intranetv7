/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import static service.registradorcurricular.RegistradorCurricularCertificadosGetGlosaService.service;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularCertificadosGetGlosaAction extends ActionCommonSupport {

    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getRegistradorSession(sesion));
    }
}
