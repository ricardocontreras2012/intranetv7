/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.jefearea;

import static service.jefearea.JefeAreaReportesGetResumenService.service;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class JefeAreaReportesGetResumenAction extends ActionCommonAgnoSemSupport {
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey(), getAgno(), getSem());
    }
}

