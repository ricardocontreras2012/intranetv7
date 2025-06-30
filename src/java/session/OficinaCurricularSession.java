/*
 * @(#)OficinaCurricularSession.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package session;

import domain.model.Administrativo;
import java.io.Serializable;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class OficinaCurricularSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private Administrativo administrativo;

    /**
     *
     */
    public OficinaCurricularSession() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Administrativo getAdministrativo() {
        return administrativo;
    }

    /**
     * Method description
     *
     * @param administrativo
     */
    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    }
}
