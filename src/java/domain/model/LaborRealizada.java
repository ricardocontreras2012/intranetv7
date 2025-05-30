/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class LaborRealizada implements Serializable {

    private static final long serialVersionUID = 1L;
    private LaborRealizadaId id;
    private String lreaIniciales;
    private TrabajadorView funcionario;
    private Unidad unidad;
    private Labor labor;
    private String lreaSubrogante;

    public LaborRealizada() {
    }

    public LaborRealizadaId getId() {
        return id;
    }

    public void setId(LaborRealizadaId id) {
        this.id = id;
    }

    public String getLreaIniciales() {
        return lreaIniciales;
    }

    public void setLreaIniciales(String lreaIniciales) {
        this.lreaIniciales = lreaIniciales;
    }

    public TrabajadorView getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(TrabajadorView funcionario) {
        this.funcionario = funcionario;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Labor getLabor() {
        return labor;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
    }


    public String getLreaSubrogante() {
        return lreaSubrogante;
    }

    public void setLreaSubrogante(String lreaSubrogante) {
        this.lreaSubrogante = lreaSubrogante;
    }
    
   
    //////////////
    public String getLabel()
    {        
        return ("1".equals(funcionario.getTraSexo())?labor.getLabDesFem():labor.getLabDesMasc()).toUpperCase(ContextUtil.getLocale())+ (lreaSubrogante == null?"":" (S)");
    }

    public String getLabelFull()
    {
        return getLabel()+" ("+getUnidad().getUniNom().toUpperCase(ContextUtil.getLocale())+")";
    }

}
