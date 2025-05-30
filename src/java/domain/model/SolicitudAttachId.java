/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SolicitudAttachId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer solaCorrelAttach;
    private Integer solaCorrelSol;

    /**
     *
     */
    public SolicitudAttachId() {
    }

    /**
     *
     * @return
     */
    public Integer getSolaCorrelAttach() {
        return solaCorrelAttach;
    }

    /**
     *
     * @param solaCorrelAttach
     */
    public void setSolaCorrelAttach(Integer solaCorrelAttach) {
        this.solaCorrelAttach = solaCorrelAttach;
    }

    /**
     *
     * @return
     */
    public Integer getSolaCorrelSol() {
        return solaCorrelSol;
    }

    /**
     *
     * @param solaCorrelSol
     */
    public void setSolaCorrelSol(Integer solaCorrelSol) {
        this.solaCorrelSol = solaCorrelSol;
    }
}
