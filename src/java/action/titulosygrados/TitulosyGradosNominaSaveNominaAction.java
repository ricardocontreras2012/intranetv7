/*
 * @(#)TitulosyGradosNominaSaveNominaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.titulosygrados;

import domain.model.ExpedienteLogro;
import service.nominatitulacion.titulosygrados.TitulosyGradosSaveNominaService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaSaveNominaAction extends ActionParameterAwareSupport {

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
        ExpedienteLogro expediente = getGenericSession().getWorkSession( getKey()).getExpedienteLogro();

        agno = expediente.getNomina().getExpnAgno();
        nomina = expediente.getNomina().getExpnNumero();
        tipo = expediente.getPlanLogro().getLogro().getLogrCod();

        return new TitulosyGradosSaveNominaService().service(getGenericSession(), getMapParameters(), getKey());
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
    public String getNomina() {
        return nomina;
    }

    /**
     * Method description
     *
     *
     * @param nomina
     */
    public void setNomina(String nomina) {
        this.nomina = nomina;
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
