/*
 * @(#)TitulosyGradosNominaGetNominaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.titulosygrados;

import org.apache.commons.lang3.StringUtils;
import static service.titulosygrados.TitulosyGradosNominaGetNominaService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaGetNominaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer agno;
    private String nomina;
    private Integer tipo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), tipo, StringUtils.upperCase(nomina), agno, getKey());
    }

    public String getNomina() {
        return nomina;
    }

    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getAgno() {
        return agno;
    }

    /**
     * Method description
     *
     *
     * @param agno
     */
    public void setAgno(Integer agno) {
        this.agno = agno;
    }
}
