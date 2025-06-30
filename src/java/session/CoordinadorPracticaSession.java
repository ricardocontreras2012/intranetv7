/*
 * @(#)CoordinadorPracticaSession.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package session;

import domain.model.Profesor;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CoordinadorPracticaSession {

    private Profesor profesor;

    /**
     * Method description
     *
     * @return
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Method description
     *
     * @param profesor
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
