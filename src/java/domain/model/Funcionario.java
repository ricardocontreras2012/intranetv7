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
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    Integer funRut;
    String funDv;
    String funPaterno;
    String funMaterno;
    String funNombre;
    Integer funSexo;
    String funDireccion;

    public Funcionario() {
    }

    public Integer getFunRut() {
        return funRut;
    }
    
    public void setFunRut(Integer funRut) {
        this.funRut = funRut;
    }

    public String getFunDv() {
        return funDv;
    }

    public void setFunDv(String funDv) {
        this.funDv = funDv;
    }

    public String getFunPaterno() {
        return funPaterno;
    }

    public void setFunPaterno(String funPaterno) {
        this.funPaterno = funPaterno;
    }

    public String getFunMaterno() {
        return funMaterno;
    }

    public void setFunMaterno(String funMaterno) {
        this.funMaterno = funMaterno;
    }

    public String getFunNombre() {
        return funNombre;
    }

    public void setFunNombre(String funNombre) {
        this.funNombre = funNombre;
    }

    public Integer getFunSexo() {
        return funSexo;
    }

    public void setFunSexo(Integer funSexo) {
        this.funSexo = funSexo;
    }

    public String getFunDireccion() {
        return funDireccion;
    }

    public void setFunDireccion(String funDireccion) {
        this.funDireccion = funDireccion;
    }

    public String getNombre()
    {
        return (funNombre+" "+(funPaterno==null?"":funPaterno)+" "+(funMaterno==null?"":funMaterno)).trim();
    }
    
    public String getRut()
    {
        return (funRut.toString() + "-" + funDv).trim();
    }
}
