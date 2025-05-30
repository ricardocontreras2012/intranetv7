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
public class ConvalidacionComisionProf implements Serializable {
    private static final long serialVersionUID = 1L;
    private ConvalidacionComisionProfId id;
    private Profesor profesor;
    ConvalidacionComision comision;

    public ConvalidacionComisionProf() {
    }

    public ConvalidacionComisionProfId getId() {
        return id;
    }

    public void setId(ConvalidacionComisionProfId id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ConvalidacionComision getComision() {
        return comision;
    }

    public void setComision(ConvalidacionComision comision) {
        this.comision = comision;
    }
}
