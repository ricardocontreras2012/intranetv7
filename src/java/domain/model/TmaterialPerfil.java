/*
 * @(#)TmaterialPerfil.java
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
public class TmaterialPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    private TmaterialPerfilId id;
    private Tmaterial tmaterial;

    /**
     *
     */
    public TmaterialPerfil() {
    }

    /**
     * Method description
     *
     * @return
     */
    public TmaterialPerfilId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(TmaterialPerfilId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public Tmaterial getTmaterial() {
        return tmaterial;
    }

    /**
     * Method description
     *
     * @param tmaterial
     */
    public void setTmaterial(Tmaterial tmaterial) {
        this.tmaterial = tmaterial;
    }
}
