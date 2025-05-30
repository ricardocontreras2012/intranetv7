/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SolicitudAttach  implements Serializable {

    private static final long serialVersionUID = 1L;
    private SolicitudAttachId id;
    private String solaAttachFile;
    private Solicitud solicitud;
    private TdocumentoSolicitud tdocumentoSolicitud;

    /**
     *
     */
    public SolicitudAttach() {
    }

    /**
     *
     * @return
     */
    public SolicitudAttachId getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(SolicitudAttachId id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getSolaAttachFile() {
        return solaAttachFile;
    }

    /**
     *
     * @param solaAttachFile
     */
    public void setSolaAttachFile(String solaAttachFile) {
        this.solaAttachFile = solaAttachFile;
    }

    /**
     *
     * @return
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     *
     * @param solicitud
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public TdocumentoSolicitud getTdocumentoSolicitud() {
        return tdocumentoSolicitud;
    }

    public void setTdocumentoSolicitud(TdocumentoSolicitud tdocumentoSolicitud) {
        this.tdocumentoSolicitud = tdocumentoSolicitud;
    }
}
