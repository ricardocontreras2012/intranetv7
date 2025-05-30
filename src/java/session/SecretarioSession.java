/*
 * @(#)SecretarioSession.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package session;

import domain.model.Profesor;
import java.io.Serializable;

/**
 * @author Ricardo Contreras S.
 */
public class SecretarioSession implements Serializable {

    private static final long serialVersionUID = 1L;
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
