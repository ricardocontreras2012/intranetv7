/*
 * @(#)MallaNodoSupport.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.support;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MallaNodoSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer asignatura;
    private Integer correl;
    private String electiva;
    private String estado;    // S:Sin Cursar, I:Inscrito, A:Aprobado, R:Reprobado
    private Integer linea;
    private String nombre;
    private Integer reprobaciones;
    private String requisitos;
    private String telSct;
    private String tipo;      // P:Plan, C:Coprogramatico, A:Adicional

    /**
     *
     * @param tipo
     */
    public MallaNodoSupport(String tipo) {
        this.estado = "S";    // S:Sin Cursar
        this.reprobaciones = 0;
        this.tipo = tipo;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAsignatura() {
        return asignatura;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Method description
     *
     * @param asignatura
     */
    public void setAsignatura(Integer asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Method description
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Method description
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method description
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getReprobaciones() {
        return reprobaciones;
    }

    /**
     * Method description
     *
     * @param reprobaciones
     */
    public void setReprobaciones(Integer reprobaciones) {
        this.reprobaciones = reprobaciones;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getElectiva() {
        return electiva;
    }

    /**
     * Method description
     *
     * @param electiva
     */
    public void setElectiva(String electiva) {
        this.electiva = electiva;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getLinea() {
        return linea;
    }

    /**
     * Method description
     *
     *
     * @param linea
     */
    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getCorrel() {
        return correl;
    }

    /**
     * Method description
     *
     *
     * @param correl
     */
    public void setCorrel(Integer correl) {
        this.correl = correl;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getRequisitos() {
        return (requisitos == null)
                ? ""
                : requisitos;
    }

    /**
     * Method description
     *
     *
     * @param requisitos
     */
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getTelSct() {
        return telSct;
    }

    /**
     * Method description
     *
     *
     * @param telSct
     */
    public void setTelSct(String telSct) {
        this.telSct = telSct;
    }
}
