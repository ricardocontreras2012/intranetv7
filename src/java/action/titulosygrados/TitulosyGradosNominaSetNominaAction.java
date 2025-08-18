/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.titulosygrados;

import service.nominatitulacion.titulosygrados.TitulosyGradosSetNominaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class TitulosyGradosNominaSetNominaAction extends ActionCommonSupport {

    private Integer tipo;
    private Integer agno;
    private String fechaNomina;
    private String nomina;
    private String url = "CommonAlumnoSearchEnable?typeSearch=F&actionCall=CommonAlumnoGetAluCarList&actionNested=TitulosyGradosNominaAddAlumno";

    @Override
    public String action() throws Exception {
        return new TitulosyGradosSetNominaService().service(getGenericSession(), tipo, agno, nomina, fechaNomina, getKey());
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getAgno() {
        return agno;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    public String getFechaNomina() {
        return fechaNomina;
    }

    public void setFechaNomina(String fechaNomina) {
        this.fechaNomina = fechaNomina;
    }

    public String getNomina() {
        return nomina;
    }

    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
