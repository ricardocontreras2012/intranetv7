/*
 * @(#)RegistradorSession.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package session;

import domain.model.ActaCalificacion;
import domain.model.Administrativo;
import domain.model.Externo;
import domain.model.Mencion;
import domain.model.ParametroMencion;
import domain.model.SolicitudCertificadoCarrito;
import java.io.Serializable;
import java.util.List;
import domain.model.NominaActaView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RegistradorSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<ActaCalificacion> actaList;
    private Administrativo administrativo;
    private List<Externo> externoList;
    private List<NominaActaView> nominaActaViewList;
    private List<SolicitudCertificadoCarrito> certificadoList;
    private ParametroMencion parametroMencion;
    private Mencion mencion;

    /**
     *
     */
    public RegistradorSession() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Administrativo getAdministrativo() {
        return administrativo;
    }

    /**
     * Method description
     *
     * @param administrativo
     */
    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<NominaActaView> getNominaActaViewList() {
        return nominaActaViewList;
    }

    /**
     * Method description
     *
     * @param nominaActaViewList
     */
    public void setNominaActaViewList(List<NominaActaView> nominaActaViewList) {
        this.nominaActaViewList = nominaActaViewList;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<ActaCalificacion> getActaList() {
        return actaList;
    }

    /**
     * Method description
     *
     * @param actaList
     */
    public void setActaList(List<ActaCalificacion> actaList) {
        this.actaList = actaList;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<Externo> getExternoList() {
        return externoList;
    }

    /**
     * Method description
     *
     * @param externoList
     */
    public void setExternoList(List<Externo> externoList) {
        this.externoList = externoList;
    }

    public List<SolicitudCertificadoCarrito> getCertificadoList() {
        return certificadoList;
    }

    public void setCertificadoList(List<SolicitudCertificadoCarrito> certificadoList) {
        this.certificadoList = certificadoList;
    }

    public ParametroMencion getParametroMencion() {
        return parametroMencion;
    }

    public void setParametroMencion(ParametroMencion parametroMencion) {
        this.parametroMencion = parametroMencion;
    }

    public Mencion getMencion() {
        return mencion;
    }

    public void setMencion(Mencion mencion) {
        this.mencion = mencion;
    }
}
