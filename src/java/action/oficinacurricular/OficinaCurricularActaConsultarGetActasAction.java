/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.oficinacurricular;

import service.acta.oficinacurricular.OficinaCurricularActaConsultarGetActasService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class OficinaCurricularActaConsultarGetActasAction extends ActionCommonSupport {
    private String flag;


    @Override
    public String action() throws Exception {
        return new OficinaCurricularActaConsultarGetActasService().service(getGenericSession(), flag, getKey());
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
