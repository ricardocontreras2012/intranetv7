/*
 * @(#)VerificacionCertificadoSession.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package session;

import domain.model.LogCertificacion;
import java.util.List;
import infrastructure.support.CalificacionCertificacionSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class VerificacionCertificadoSession {

    private List<CalificacionCertificacionSupport> cartolaAdicional;
    private List<CalificacionCertificacionSupport> cartolaMalla;
    private List<CalificacionCertificacionSupport> cartolaOtras;
    private LogCertificacion logCertificacion;
    private String archivo;
    private Boolean download;

    /**
     * Method description
     *
     * @return
     */
    public LogCertificacion getLogCertificacion() {
        return logCertificacion;
    }

    /**
     * Method description
     *
     * @param logCertificacion
     */
    public void setLogCertificacion(LogCertificacion logCertificacion) {
        this.logCertificacion = logCertificacion;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<CalificacionCertificacionSupport> getCartolaAdicional() {
        return cartolaAdicional;
    }

    /**
     * Method description
     *
     * @param cartolaAdicional
     */
    public void setCartolaAdicional(List<CalificacionCertificacionSupport> cartolaAdicional) {
        this.cartolaAdicional = cartolaAdicional;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<CalificacionCertificacionSupport> getCartolaMalla() {
        return cartolaMalla;
    }

    /**
     * Method description
     *
     * @param cartolaMalla
     */
    public void setCartolaMalla(List<CalificacionCertificacionSupport> cartolaMalla) {
        this.cartolaMalla = cartolaMalla;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<CalificacionCertificacionSupport> getCartolaOtras() {
        return cartolaOtras;
    }

    /**
     * Method description
     *
     * @param cartolaOtras
     */
    public void setCartolaOtras(List<CalificacionCertificacionSupport> cartolaOtras) {
        this.cartolaOtras = cartolaOtras;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Boolean getDownload() {
        return download;
    }

    public void setDownload(Boolean download) {
        this.download = download;
    }
}
