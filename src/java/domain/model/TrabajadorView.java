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
public class TrabajadorView implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer traRut;
    private String traDv;
    private String traPaterno;
    private String traMaterno;
    private String traNombre;
    private String traNombreSimple;
    private String traEmail;
    private String traSexo;

    public TrabajadorView() {
    }

    public Integer getTraRut() {
        return traRut;
    }

    public void setTraRut(Integer traRut) {
        this.traRut = traRut;
    }

    public String getTraDv() {
        return traDv;
    }

    public void setTraDv(String traDv) {
        this.traDv = traDv;
    }

    public String getTraPaterno() {
        return traPaterno;
    }

    public void setTraPaterno(String traPaterno) {
        this.traPaterno = traPaterno;
    }

    public String getTraMaterno() {
        return traMaterno;
    }

    public void setTraMaterno(String traMaterno) {
        this.traMaterno = traMaterno;
    }

    public String getTraNombre() {
        return traNombre;
    }

    public void setTraNombre(String traNombre) {
        this.traNombre = traNombre;
    }

    public String getTraNombreSimple() {
        return traNombreSimple;
    }

    public void setTraNombreSimple(String traNombreSimple) {
        this.traNombreSimple = traNombreSimple;
    }

    public String getTraEmail() {
        return traEmail;
    }

    public void setTraEmail(String traEmail) {
        this.traEmail = traEmail;
    }

    public String getTraSexo() {
        return traSexo;
    }

    public void setTraSexo(String traSexo) {
        this.traSexo = traSexo;
    }
}
