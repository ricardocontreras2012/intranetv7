/*
 * @(#)CommonProfesorCargaHistoricaPrintAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import domain.model.Curso;
import java.util.List;

import service.common.CommonProfesorCargaHistoricaPrintService;
import infrastructure.support.action.ActionReportSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonProfesorCargaHistoricaExport
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonProfesorCargaHistoricaPrintAction extends ActionReportSupport {

    private static final long serialVersionUID = 1L;
    private List<Curso> nomina;
    private String contentDisposition;
    private String format;
    private Integer rut;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {              
        nomina = new CommonProfesorCargaHistoricaPrintService().service(getGenericSession(), getReport(), getKey());
        return SUCCESS;
    }
   

    /**
     * Method description
     *
     * @return
     */
    public List<Curso> getNomina() {
        return nomina;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * Method description
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * Method description
     *
     * @param contentDisposition
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * Method description
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }
}
