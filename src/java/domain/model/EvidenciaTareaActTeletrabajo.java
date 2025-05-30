/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

public class EvidenciaTareaActTeletrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    private EvidenciaTareaActTeletrabajoId id;
    private TareaActividadTeletrabajo tareaActividadTeletrabajo;
    private String etatelFile;
    private String etatelDes;

    public EvidenciaTareaActTeletrabajoId getId() {
        return id;
    }

    public void setId(EvidenciaTareaActTeletrabajoId id) {
        this.id = id;
    }

    public String getEtatelFile() {
        return etatelFile;
    }

    public void setEtatelFile(String etatelFile) {
        this.etatelFile = etatelFile;
    }

    public TareaActividadTeletrabajo getTareaActividadTeletrabajo() {
        return tareaActividadTeletrabajo;
    }

    public void setTareaActividadTeletrabajo(TareaActividadTeletrabajo tareaActividadTeletrabajo) {
        this.tareaActividadTeletrabajo = tareaActividadTeletrabajo;
    }

    public String getEtatelDes() {
        return etatelDes;
    }

    public void setEtatelDes(String etatelDes) {
        this.etatelDes = etatelDes;
    }
}