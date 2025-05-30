/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Convenio implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer convNro;
    String convTipo;
    Funcionario funcionario;
    Date convFecha;
    Date convFechaIni;
    Date convFechaTer;
    String convFuncion;
    Proyecto proyecto;
    Curso curso;
    Integer convHor;
    Integer convMonto;
    String convTipoMonto;
    String convLugarDesempeno;
    String convObs;
    String convObsPago;
    String convNombreAlumno;
    Profesor firma;

    /*public Convenio(Integer convNro, String convTipo, Integer convRut, Date convFecha, Date convFechaIni, Date convFechaTer, String convFuncion, Proyecto proyecto,  Curso curso, Integer convHor, Integer convMonto, String convTipoMonto, String convLugarDesempeno, String convObs, String convObsPago, String convNombreAlumno, Integer convRutFirma) {
        this.convNro = convNro;
        this.convTipo = convTipo;
        this.funcionario = funcionario;
        this.convFecha = convFecha;
        this.convFechaIni = convFechaIni;
        this.convFechaTer = convFechaTer;
        this.convFuncion = convFuncion;
        this.proyecto = proyecto;
        this.curso = curso;
        this.convHor = convHor;
        this.convMonto = convMonto;
        this.convTipoMonto = convTipoMonto;
        this.convLugarDesempeno = convLugarDesempeno;
        this.convObs = convObs;
        this.convObsPago = convObsPago;
        this.convNombreAlumno = convNombreAlumno;
        this.convRutFirma = convRutFirma;
    }*/
    public Integer getConvNro() {
        return convNro;
    }

    public void setConvNro(Integer convNro) {
        this.convNro = convNro;
    }

    public String getConvTipo() {
        return convTipo;
    }

    public void setConvTipo(String convTipo) {
        this.convTipo = convTipo;
    }

    public Date getConvFecha() {
        return convFecha;
    }

    public void setConvFecha(Date convFecha) {
        this.convFecha = convFecha;
    }

    public Date getConvFechaIni() {
        return convFechaIni;
    }

    public void setConvFechaIni(Date convFechaIni) {
        this.convFechaIni = convFechaIni;
    }

    public Date getConvFechaTer() {
        return convFechaTer;
    }

    public void setConvFechaTer(Date convFechaTer) {
        this.convFechaTer = convFechaTer;
    }

    public String getConvFuncion() {
        return convFuncion;
    }

    public void setConvFuncion(String convFuncion) {
        this.convFuncion = convFuncion;
    }

    public Integer getConvHor() {
        return convHor;
    }

    public void setConvHor(Integer convHor) {
        this.convHor = convHor;
    }

    public Integer getConvMonto() {
        return convMonto;
    }

    public void setConvMonto(Integer convMonto) {
        this.convMonto = convMonto;
    }

    public String getConvTipoMonto() {
        return convTipoMonto;
    }

    public void setConvTipoMonto(String convTipoMonto) {
        this.convTipoMonto = convTipoMonto;
    }

    public String getConvLugarDesempeno() {
        return convLugarDesempeno;
    }

    public void setConvLugarDesempeno(String convLugarDesempeno) {
        this.convLugarDesempeno = convLugarDesempeno;
    }

    public String getConvObs() {
        return convObs;
    }

    public void setConvObs(String convObs) {
        this.convObs = convObs;
    }

    public String getConvObsPago() {
        return convObsPago;
    }

    public void setConvObsPago(String convObsPago) {
        this.convObsPago = convObsPago;
    }

    public String getConvNombreAlumno() {
        return convNombreAlumno;
    }

    public void setConvNombreAlumno(String convNombreAlumno) {
        this.convNombreAlumno = convNombreAlumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Profesor getFirma() {
        return firma;
    }

    public void setFirma(Profesor firma) {
        this.firma = firma;
    }

    public String getTipo() {
        String ret = "";
        switch (this.convTipo) {
            case "AYU":
                ret = "Ayudantía";
                break;
            case "DPG":
                ret = "Dirección Programa";
                break;
            case "EXT":
                ret = "Externo";
                break;
            case "DOC":
                ret = "Docencia";
                break;
            case "SEC":
                ret = "Secretaria";
                break;
            case "SER":
                ret = "Servicio";
                break;
        }
        return ret;
    }
}
