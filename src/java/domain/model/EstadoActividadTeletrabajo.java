/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class EstadoActividadTeletrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String eatCod;
    private String eatDes;
    private String eatFullDes;
    
    public EstadoActividadTeletrabajo() {
    }

    public String getEatCod() {
        return eatCod;
    }

    public void setEatCod(String eatCod) {
        this.eatCod = eatCod;
    }

    public String getEatDes() {
        return eatDes;
    }

    public void setEatDes(String eatDes) {
        this.eatDes = eatDes;
    }

    public String getEatFullDes() {
        return eatFullDes;
    }

    public void setEatFullDes(String eatFullDes) {
        this.eatFullDes = eatFullDes;
    }
    
}
