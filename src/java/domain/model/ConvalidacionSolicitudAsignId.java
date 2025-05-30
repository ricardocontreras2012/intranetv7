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
public class ConvalidacionSolicitudAsignId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer csaCorrel;
    private Integer csaAsign;

    public ConvalidacionSolicitudAsignId() {
    }

    public Integer getCsaCorrel() {
        return csaCorrel;
    }

    public void setCsaCorrel(Integer csaCorrel) {
        this.csaCorrel = csaCorrel;
    }

    public Integer getCsaAsign() {
        return csaAsign;
    }

    public void setCsaAsign(Integer csaAsign) {
        this.csaAsign = csaAsign;
    }
}
