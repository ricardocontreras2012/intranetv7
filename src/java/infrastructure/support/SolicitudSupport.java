/*
 * @(#)SolicitudSupport.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.support;

import domain.model.EstadoSolicitud;
import domain.model.Solicitud;
import domain.model.SolicitudAttach;
import java.io.File;
import java.util.List;
import domain.repository.SolicitudAttachRepository;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getSolicitudAttachFileName;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SolicitudSupport {

    private final Solicitud solicitud;

    /**
     *
     * @param solicitud
     */
    public SolicitudSupport(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<SolicitudAttach> getDocumentos() {

        return ContextUtil.getDAO().getSolicitudAttachRepository(ActionUtil.getDBUser()).find(solicitud);
    }

    /**
     * Method description
     *
     * @param action
     * @param tipo
     * @param upload
     * @param uploadFileName
     * @param caption
     * @throws Exception
     */
    public void doNewFile(ActionCommonSupport action, Integer tipo, File upload, String uploadFileName, String caption)
            throws Exception {

        SolicitudAttachRepository attachRepository
                = ContextUtil.getDAO().getSolicitudAttachRepository(ActionUtil.getDBUser());
        Integer correlDoc = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getSecuenciaDocumentoSolicitud();
        String nombreArchivo = getSolicitudAttachFileName(uploadFileName,
                this.solicitud.getSolFolio(), correlDoc);

        doUpload(action, upload, nombreArchivo,"sol");              
        attachRepository. addAttach(this.solicitud.getSolFolio(), correlDoc, nombreArchivo, tipo);
    }
    
    public void setGenerada()
    {
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
        estadoSolicitud.setEsolCod(10);
        solicitud.setEstadoSolicitud(estadoSolicitud);
    }
    
    public void setAprobada()
    {
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
        estadoSolicitud.setEsolCod(40);
        solicitud.setEstadoSolicitud(estadoSolicitud); 
        solicitud.setSolResolucion("A");
    }
}
