/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.common.CommonPracticaActaRectificatoriaEnableService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaActaRectificatoriaEnableAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer agno;
    private Integer sem;
    private Integer practica;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonPracticaActaRectificatoriaEnableService().service(getGenericSession(), agno, sem, practica,  getKey());
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

    public Integer getPractica() {
        return practica;
    }

    public void setPractica(Integer practica) {
        this.practica = practica;
    }
}
