/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class SolicitudCertificadoCarritoId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer sccSolicitud;
    private Integer sccOrd;

    public SolicitudCertificadoCarritoId() {
    }
        
    public Integer getSccSolicitud() {
        return sccSolicitud;
    }

    public void setSccSolicitud(Integer sccSolicitud) {
        this.sccSolicitud = sccSolicitud;
    }

    public Integer getSccOrd() {
        return sccOrd;
    }

    public void setSccOrd(Integer sccOrd) {
        this.sccOrd = sccOrd;
    }
}
