/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.curso;

import service.curso.SearchAyudanteService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class SearchAyudanteAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String materno;
    private String nombre;
    private String paterno;
    private Integer rut;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SearchAyudanteService().service(getGenericSession(), rut, paterno, materno, nombre, getKey());
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }
}
