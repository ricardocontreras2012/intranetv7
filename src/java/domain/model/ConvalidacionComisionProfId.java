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
public class ConvalidacionComisionProfId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ccopCod;
    private Integer ccopRut;

    public ConvalidacionComisionProfId() {
    }

    public Integer getCcopCod() {
        return ccopCod;
    }

    public void setCcopCod(Integer ccopCod) {
        this.ccopCod = ccopCod;
    }

    public Integer getCcopRut() {
        return ccopRut;
    }

    public void setCcopRut(Integer ccopRut) {
        this.ccopRut = ccopRut;
    }
}

