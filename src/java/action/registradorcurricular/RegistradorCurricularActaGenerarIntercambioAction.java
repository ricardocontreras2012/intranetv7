/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import service.acta.registradorcurricular.RegistradorCurricularActaGenerarIntercambioService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularActaGenerarIntercambioAction extends ActionCommonSupport {

    private Integer agno;
    private Integer sem;

    @Override
    public String action() throws Exception {
        return new RegistradorCurricularActaGenerarIntercambioService().service(this, getGenericSession(), sem, agno, getKey());
    }

    public Integer getAgno() {
        return agno;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }


}
