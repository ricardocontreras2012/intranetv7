/*
 * @(#)MallaJsonSupport.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.support;

import java.io.Serializable;

/**
 *
 * @author Alvaro
 */
public class MallaJsonSupport implements Serializable {
    private Integer nivel;
    private Integer asig;
    private String nombre;
    private Integer reprobaciones;
    private String situacion;
    private Integer linea;
    private String requisitos;
    private Integer correl;
    private String tel_sct;
    private String origen;
    private String electivo;
    private String tipo;

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getAsig() {
        return asig;
    }

    public void setAsig(Integer asig) {
        this.asig = asig;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getReprobaciones() {
        return reprobaciones;
    }

    public void setReprobaciones(Integer reprobaciones) {
        this.reprobaciones = reprobaciones;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public Integer getCorrel() {
        return correl;
    }

    public void setCorrel(Integer correl) {
        this.correl = correl;
    }

    public String getTel_sct() {
        return tel_sct;
    }

    public void setTel_sct(String tel_sct) {
        this.tel_sct = tel_sct;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getElectivo() {
        return electivo;
    }

    public void setElectivo(String electivo) {
        this.electivo = electivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
