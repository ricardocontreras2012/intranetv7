/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.oficinacurricular;

import static service.oficinacurricular.OficinaCurricularActaConsultarService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class OficinaCurricularActaConsultarAction extends ActionCommonSupport {

    private String flag;

    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), flag, getKey());
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
