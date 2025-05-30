/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.util.Date;
import infrastructure.util.common.CommonSolicitudUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class LogSolicitud {

    private Integer logCorrel;
    private EstadoSolicitud estadoSolicitud;
    private Tsolicitud tsolicitud;
    private String logUser;
    private Date logFecha;
    private Integer logFolio;
    private String logResolucion;

    /**
     *
     */
    public LogSolicitud() {
    }

    /**
     *
     * @return
     */
    public Integer getLogCorrel() {
        return logCorrel;
    }

    /**
     *
     * @param logCorrel
     */
    public void setLogCorrel(Integer logCorrel) {
        this.logCorrel = logCorrel;
    }

    /**
     *
     * @return
     */
    public String getLogUser() {
        return logUser;
    }

    /**
     *
     * @param logUser
     */
    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    /**
     *
     * @return
     */
    public Date getLogFecha() {
        return logFecha;
    }

    /**
     *
     * @param logFecha
     */
    public void setLogFecha(Date logFecha) {
        this.logFecha = logFecha;
    }

    /**
     *
     * @return
     */
    public Integer getLogFolio() {
        return logFolio;
    }

    /**
     *
     * @param logFolio
     */
    public void setLogFolio(Integer logFolio) {
        this.logFolio = logFolio;
    }

    /**
     *
     * @return
     */
    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    /**
     *
     * @param estadoSolicitud
     */
    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    /**
     *
     * @return
     */
    public Tsolicitud getTsolicitud() {
        return tsolicitud;
    }

    /**
     *
     * @param tsolicitud
     */
    public void setTsolicitud(Tsolicitud tsolicitud) {
        this.tsolicitud = tsolicitud;
    }

    /**
     *
     * @return
     */
    public String getLogResolucion() {
        return logResolucion;
    }

    /**
     *
     * @param logResolucion
     */
    public void setLogResolucion(String logResolucion) {
        this.logResolucion = logResolucion;
    }

    /**
     *
     * @return
     */
    public String getResolucion()
    {
        return CommonSolicitudUtil.getResolucion(logResolucion);
    }
}
