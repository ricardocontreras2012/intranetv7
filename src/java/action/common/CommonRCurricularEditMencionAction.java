/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.common.CommonRCurricularEditMencionService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Javier Frez V.
 */
public class CommonRCurricularEditMencionAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    
    private Integer menCodCar;
    private Integer menCodMen;
    private String menPrefijo;
    private String menPlanComun;
    private String menNom;
    private String retValue;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        retValue = new CommonRCurricularEditMencionService().service(getGenericSession(), getKey(), menCodCar, menCodMen, menPrefijo, menPlanComun, menNom);
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

    public String getMenPrefijo() {
        return menPrefijo;
    }

    public void setMenPrefijo(String menPrefijo) {
        this.menPrefijo = menPrefijo;
    }

    public String getMenPlanComun() {
        return menPlanComun;
    }

    public void setMenPlanComun(String menPlanComun) {
        this.menPlanComun = menPlanComun;
    }

    public String getMenNom() {
        return menNom;
    }

    public void setMenNom(String menNom) {
        this.menNom = menNom;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }
    
}
