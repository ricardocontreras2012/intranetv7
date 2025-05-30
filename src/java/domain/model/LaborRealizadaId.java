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
public class LaborRealizadaId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer lreaRut;
    private Integer lreaLabor;
    private Integer lreaUnidad;

    public LaborRealizadaId() {
    }

    public Integer getLreaRut() {
        return lreaRut;
    }

    public void setLreaRut(Integer lreaRut) {
        this.lreaRut = lreaRut;
    }

    public Integer getLreaLabor() {
        return lreaLabor;
    }

    public void setLreaLabor(Integer lreaLabor) {
        this.lreaLabor = lreaLabor;
    }

    public Integer getLreaUnidad() {
        return lreaUnidad;
    }

    public void setLreaUnidad(Integer lreaUnidad) {
        this.lreaUnidad = lreaUnidad;
    }
}
