/*
 * @(#)SecretariaDocenteSession.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package session;

import domain.model.Administrativo;
import domain.model.ConvalidacionSolicitud;
import domain.model.ConvalidacionSolicitudAsign;
import domain.model.Profesor;
import java.io.Serializable;
import java.util.List;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SecretariaSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private Administrativo administrativo;
    private List<ConvalidacionSolicitudAsign> porAprobar;    
    private List<ConvalidacionSolicitud> convalidaciones;
    private ConvalidacionSolicitud convalidacion;
    private List<Profesor> comision;

    /**
     *
     */
    public SecretariaSession() {
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

    public List<ConvalidacionSolicitudAsign> getPorAprobar() {
        return porAprobar;
    }

    public void setPorAprobar(List<ConvalidacionSolicitudAsign> porAprobar) {
        this.porAprobar = porAprobar;
    }    

    public List<ConvalidacionSolicitud> getConvalidaciones() {
        return convalidaciones;
    }

    public void setConvalidaciones(List<ConvalidacionSolicitud> convalidaciones) {
        this.convalidaciones = convalidaciones;
    }

    public ConvalidacionSolicitud getConvalidacion() {
        return convalidacion;
    }

    public void setConvalidacion(ConvalidacionSolicitud convalidacion) {
        this.convalidacion = convalidacion;
    }

    public List<Profesor> getComision() {
        return comision;
    }

    public void setComision(List<Profesor> comision) {
        this.comision = comision;
    }    
}
