/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.titulosygrados;

import infrastructure.support.action.common.ActionCommonSupport;
import static service.titulosygrados.TitulosyGradosNominaNewService.service;

/**
 *
 * @author rcontreras
 */
public class TitulosyGradosNominaNewAction extends ActionCommonSupport {

    @Override
    public String action() throws Exception {
        return service(getGenericSession(),  getKey());
    }
}
