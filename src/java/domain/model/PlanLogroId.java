/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author rcontreras
 */
public class PlanLogroId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer plalCodCar;
    private Integer plalCodMen;
    private Integer plalCodPlan;
    private Integer plalCorrel;

    public PlanLogroId() {
    }

    public Integer getPlalCodCar() {
        return plalCodCar;
    }

    public void setPlalCodCar(Integer plalCodCar) {
        this.plalCodCar = plalCodCar;
    }

    public Integer getPlalCodMen() {
        return plalCodMen;
    }

    public void setPlalCodMen(Integer plalCodMen) {
        this.plalCodMen = plalCodMen;
    }

    public Integer getPlalCodPlan() {
        return plalCodPlan;
    }

    public void setPlalCodPlan(Integer plalCodPlan) {
        this.plalCodPlan = plalCodPlan;
    }

    public Integer getPlalCorrel() {
        return plalCorrel;
    }

    public void setPlalCorrel(Integer plalCorrel) {
        this.plalCorrel = plalCorrel;
    }

}
