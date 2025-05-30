/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    String proyCod;
    String proyCodOfi;
    String proyNom;
    String proyAdministrado;
    Profesor jefe;
    Unidad unidad;

    public Proyecto() {
    }

    public String getProyCod() {
        return proyCod;
    }

    public void setProyCod(String proyCod) {
        this.proyCod = proyCod;
    }

    public String getProyCodOfi() {
        return proyCodOfi;
    }

    public void setProyCodOfi(String proyCodOfi) {
        this.proyCodOfi = proyCodOfi;
    }

    public String getProyNom() {
        return proyNom;
    }

    public void setProyNom(String proyNom) {
        this.proyNom = proyNom;
    }

    public String getProyAdministrado() {
        return proyAdministrado;
    }

    public void setProyAdministrado(String proyAdministrado) {
        this.proyAdministrado = proyAdministrado;
    }

    public Profesor getJefe() {
        return jefe;
    }

    public void setJefe(Profesor jefe) {
        this.jefe = jefe;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
}
