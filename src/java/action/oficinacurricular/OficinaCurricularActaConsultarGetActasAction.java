/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.oficinacurricular;

import static service.oficinacurricular.OficinaCurricularActaConsultarGetActasService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class OficinaCurricularActaConsultarGetActasAction extends ActionCommonSupport {


    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey());
    }
}
