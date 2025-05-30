/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.List;

public class ActividadTeletrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    private ActividadTeletrabajoId id;
    private FuncionarioTeletrabajo funcionarioTeletrabajo;
    private List<TareaActividadTeletrabajo> tareaList;
    private EstadoActividadTeletrabajo estado;
    private String atelDes;
    private String atelEstado;

    public ActividadTeletrabajoId getId() {
        return id;
    }

    public void setId(ActividadTeletrabajoId id) {
        this.id = id;
    }

    public EstadoActividadTeletrabajo getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividadTeletrabajo estado) {
        this.estado = estado;
    }
    
    public FuncionarioTeletrabajo getFuncionarioTeletrabajo() {
        return funcionarioTeletrabajo;
    }

    public void setFuncionarioTeletrabajo(FuncionarioTeletrabajo funcionarioTeletrabajo) {
        this.funcionarioTeletrabajo = funcionarioTeletrabajo;
    }

    public String getAtelDes() {
        return atelDes;
    }

    public void setAtelDes(String atelDes) {
        this.atelDes = atelDes;
    }

    public List<TareaActividadTeletrabajo> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<TareaActividadTeletrabajo> tareaList) {
        this.tareaList = tareaList;
    }

    public String getAtelEstado() {
        return atelEstado;
    }

    public void setAtelEstado(String atelEstado) {
        this.atelEstado = atelEstado;
    }
}