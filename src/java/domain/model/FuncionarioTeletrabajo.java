/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

public class FuncionarioTeletrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer ftelRut;
    private Integer ftelRutJefe;
    private String ftelType;
    private Funcionario funcionario;

    public Integer getFtelRut() {
        return ftelRut;
    }

    public void setFtelRut(Integer ftelRut) {
        this.ftelRut = ftelRut;
    }

    public Integer getFtelRutJefe() {
        return ftelRutJefe;
    }

    public void setFtelRutJefe(Integer ftelRutJefe) {
        this.ftelRutJefe = ftelRutJefe;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getFtelType() {
        return ftelType;
    }

    public void setFtelType(String ftelType) {
        this.ftelType = ftelType;
    }
}
