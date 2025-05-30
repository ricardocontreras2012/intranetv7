/*
 * @(#)CommonEstadisticasAlumnosxCalidadAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import domain.model.Ccalidad;
import java.util.List;
import static service.common.CommonEstadisticasAlumnosxCalidadService.service;
import infrastructure.support.action.ActionReportSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonEstadisticasAlumnosxCalidad
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonEstadisticasAlumnosxCalidadAction extends ActionReportSupport {

    private static final long serialVersionUID = 1L;
    private Integer agno;
    private Integer calidad;
    private String contentDisposition;
    private String format;
    private List<Ccalidad> nomina;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        nomina = service(getReport(), agno, calidad);

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgno() {
        return agno;
    }

    /**
     * Method description
     *
     * @param agno
     */
    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCalidad() {
        return calidad;
    }

    /**
     * Method description
     *
     * @param calidad
     */
    public void setCalidad(Integer calidad) {
        this.calidad = calidad;
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
    public List<Ccalidad> getNomina() {
        return nomina;
    }
}
