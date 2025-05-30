/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.List;

public class TareaActividadTeletrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    private TareaActividadTeletrabajoId id;
    private String tatelDes;
    private ActividadTeletrabajo actividadTeletrabajo;
    private String tatelEvidencia;
    private List<EvidenciaTareaActTeletrabajo> evidenciaList;

    public TareaActividadTeletrabajoId getId() {
        return id;
    }

    public void setId(TareaActividadTeletrabajoId id) {
        this.id = id;
    }

    public String getTatelDes() {
        return tatelDes;
    }

    public void setTatelDes(String tatelDes) {
        this.tatelDes = tatelDes;
    }

    public ActividadTeletrabajo getActividadTeletrabajo() {
        return actividadTeletrabajo;
    }

    public void setActividadTeletrabajo(ActividadTeletrabajo actividadTeletrabajo) {
        this.actividadTeletrabajo = actividadTeletrabajo;
    } 

    public String getTatelEvidencia() {
        return tatelEvidencia;
    }

    public void setTatelEvidencia(String tatelEvidencia) {
        this.tatelEvidencia = tatelEvidencia;
    }

    public List<EvidenciaTareaActTeletrabajo> getEvidenciaList() {
        return evidenciaList;
    }

    public void setEvidenciaList(List<EvidenciaTareaActTeletrabajo> evidenciaList) {
        this.evidenciaList = evidenciaList;
    }
}
