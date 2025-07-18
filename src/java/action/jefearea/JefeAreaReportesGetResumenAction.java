/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.jefearea;

import service.jefearea.JefeAreaReportesGetResumenService;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class JefeAreaReportesGetResumenAction extends ActionCommonAgnoSemSupport {
    @Override
    public String action() throws Exception {
        return new JefeAreaReportesGetResumenService().service(getGenericSession(), getKey(), getAgno(), getSem());
    }
}

