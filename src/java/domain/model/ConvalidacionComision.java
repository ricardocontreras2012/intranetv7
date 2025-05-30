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
public class ConvalidacionComision implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ccoCod ;
    private Integer ccoDepto;

    public ConvalidacionComision() {
    }

    public Integer getCcoCod() {
        return ccoCod;
    }

    public void setCcoCod(Integer ccoCod) {
        this.ccoCod = ccoCod;
    }

    public Integer getCcoDepto() {
        return ccoDepto;
    }

    public void setCcoDepto(Integer ccoDepto) {
        this.ccoDepto = ccoDepto;
    }
}
