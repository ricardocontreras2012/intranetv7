/*
 * @(#)Tprograma.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class Tprograma implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tprCod;
    private String tprDes;
    private String tprFlagCarrera;

    public Tprograma() {
    }

    
    public Integer getTprCod() {
        return tprCod;
    }

    public void setTprCod(Integer tprCod) {
        this.tprCod = tprCod;
    }

    public String getTprDes() {
        return tprDes;
    }

    public void setTprDes(String tprDes) {
        this.tprDes = tprDes;
    }

    public String getTprFlagCarrera() {
        return tprFlagCarrera;
    }

    public void setTprFlagCarrera(String tprFlagCarrera) {
        this.tprFlagCarrera = tprFlagCarrera;
    }
}
