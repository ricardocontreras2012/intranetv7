/*
 * @(#)TitulosyGradosNominaRemoveAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.titulosygrados;

import domain.model.ExpedienteLogro;
import service.titulosygrados.TitulosyGradosNominaRemoveService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaRemoveAction extends ActionParameterAwareSupport {

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

        ExpedienteLogro expediente = getGenericSession().getWorkSession( getKey()).getExpedienteLogroList().get(0);
        agno = expediente.getNomina().getExpnAgno();
        nomina = expediente.getNomina().getExpnNumero();
        tipo = expediente.getPlanLogro().getLogro().getLogrCod();

        return new TitulosyGradosNominaRemoveService().service(getGenericSession(), getMapParameters(), agno, nomina, tipo, getKey());
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNomina() {
        return nomina;
    }

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
