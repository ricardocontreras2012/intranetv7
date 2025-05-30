/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonRCurricularDeleteUnidadService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Javier Frez V.
 */
public class CommonRCurricularDeleteUnidadAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private Integer menCodCar;
    private Integer menCodMen;
    private String retValue;
    private Integer uniCod;
    private String duty;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        retValue = service(getGenericSession(), getKey(), menCodCar, menCodMen, uniCod, duty);
        return retValue;
    }

    public Integer getMenCodCar() {
        return menCodCar;
    }

    public void setMenCodCar(Integer menCodCar) {
        this.menCodCar = menCodCar;
    }

    public Integer getMenCodMen() {
        return menCodMen;
    }

    public void setMenCodMen(Integer menCodMen) {
        this.menCodMen = menCodMen;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }

    public Integer getUniCod() {
        return uniCod;
    }

    public void setUniCod(Integer uniCod) {
        this.uniCod = uniCod;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
    
}
