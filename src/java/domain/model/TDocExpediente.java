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
 * @author Alvaro Romero C.
 * @version 7, 06/05/2025
 */
public class TDocExpediente implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tdeCod;
    private String tdeDes;
    private String tdeUser;

    /**
     *
     */
    public TDocExpediente() {
    }

    public Integer getTdeCod() {
        return tdeCod;
    }

    public void setTdeCod(Integer tdeCod) {
        this.tdeCod = tdeCod;
    }

    public String getTdeDes() {
        return tdeDes;
    }

    public void setTdeDes(String tdeDes) {
        this.tdeDes = tdeDes;
    }

    public String getTdeUser() {
        return tdeUser;
    }

    public void setTdeUser(String tdeUser) {
        this.tdeUser = tdeUser;
    }
    
}
