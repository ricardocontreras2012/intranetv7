/*
 * @(#)TmaterialPerfilId.java
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
public class TmaterialPerfilId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tmapCod;
    private String tmapDes;

    /**
     *
     */
    public TmaterialPerfilId() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getTmapCod() {
        return tmapCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getTmapDes() {
        return tmapDes;
    }

    /**
     * Method description
     *
     * @param tmapCod
     */
    public void setTmapCod(Integer tmapCod) {
        this.tmapCod = tmapCod;
    }

    /**
     * Method description
     *
     * @param tmapDes
     */
    public void setTmapDes(String tmapDes) {
        this.tmapDes = tmapDes;
    }
}
