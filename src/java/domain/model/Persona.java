/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import infrastructure.util.common.CommonUsersUtil;

/**
 *
 * @author rcontreras
 */
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer perRut;
    private String perPaterno;
    private String perMaterno;
    private String perNombre;
    private String perFull;

    public Persona() {
    }

    public Integer getPerRut() {
        return perRut;
    }

    public void setPerRut(Integer perRut) {
        this.perRut = perRut;
    }

    public String getPerPaterno() {
        return perPaterno;
    }

    public void setPerPaterno(String perPaterno) {
        this.perPaterno = perPaterno;
    }

    public String getPerMaterno() {
        return perMaterno;
    }

    public void setPerMaterno(String perMaterno) {
        this.perMaterno = perMaterno;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getNombre() {
        return CommonUsersUtil.getNombre(this.perPaterno, this.perMaterno, this.perNombre);
    }

    public String getPerFull() {
        return perFull;
    }

    public void setPerFull(String perFull) {
        this.perFull = perFull;
    }
}
