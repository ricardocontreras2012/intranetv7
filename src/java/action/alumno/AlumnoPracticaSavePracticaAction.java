/*
 * @(#)AlumnoPracticaSavePracticaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import static service.alumno.AlumnoPracticaSavePracticaService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoPracticaSavePractica
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoPracticaSavePracticaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private Integer rutEmp;
    private String fonoEmp;
    private String labor;
    private Integer comuna;
    private String direccion;

    private Integer rutAut;
    private String fonoAut;
    private String calidad;
    private String email;
    private String inicio;
    private String termino;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), rutEmp, fonoEmp, labor, comuna,
                direccion,rutAut, fonoAut, email, calidad, inicio, termino, getKey());
    }

    public void setRutEmp(Integer rutEmp) {
        this.rutEmp = rutEmp;
    }

    public void setFonoEmp(String fonoEmp) {
        this.fonoEmp = fonoEmp;
    }

    public void setLabor(String labor) {
        this.labor = labor;
    }

    public void setComuna(Integer comuna) {
        this.comuna = comuna;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setRutAut(Integer rutAut) {
        this.rutAut = rutAut;
    }

    public void setFonoAut(String fonoAut) {
        this.fonoAut = fonoAut;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }
}
