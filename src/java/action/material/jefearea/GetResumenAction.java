/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.material.jefearea;

import service.material.jefearea.JefeAreaMaterialesGetResumenService;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class GetResumenAction extends ActionCommonAgnoSemSupport {
    @Override
    public String action() throws Exception {
        return new JefeAreaMaterialesGetResumenService().service(getGenericSession(), getKey(), getAgno(), getSem());
    }
}

