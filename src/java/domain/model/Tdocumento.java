/*
 * @(#)Dia.java
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
public class Tdocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tdocCod;
    private String tdocDes;

    /**
     *
     */
    public Tdocumento() {
    }

    public Integer getTdocCod() {
        return tdocCod;
    }

    public void setTdocCod(Integer tdocCod) {
        this.tdocCod = tdocCod;
    }

    public String getTdocDes() {
        return tdocDes;
    }

    public void setTdocDes(String tdocDes) {
        this.tdocDes = tdocDes;
    }
}
