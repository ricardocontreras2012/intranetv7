/*
 * @(#)ProfesorReporteSaveModifiedReporteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import service.reporteclase.profesor.ProfesorSaveModifiedReporteService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL
 * ProfesorReporteSaveModifiedReporte
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ProfesorReporteSaveModifiedReporteAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private String contenido;
    private String metodo;
    private String objetivos;
    private String observaciones;
    private String recuperacion;
    private Integer sesionReporte;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorSaveModifiedReporteService().service(getGenericSession(), sesionReporte, objetivos,
                contenido, observaciones, metodo, recuperacion, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(sesionReporte, getGenericSession().getWorkSession(getKey()).getReportes());
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setRecuperacion(String recuperacion) {
        this.recuperacion = recuperacion;
    }

    public void setSesionReporte(Integer sesionReporte) {
        this.sesionReporte = sesionReporte;
    }
}
