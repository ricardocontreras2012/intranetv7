/*
 * @(#)AlumnoPracticaAddAttachFileActionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import java.io.File;
import service.practica.alumno.AlumnoPracticaAddAttachFileService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoPracticaAddAttachFileInscripcion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoPracticaAddAttachFileAction extends ActionCommonSupport {
    private File upload;
    private String uploadFileName;
    private Integer tipo;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoPracticaAddAttachFileService().service(this, getGenericSession(), getKey(), upload, uploadFileName, tipo);
    }    

    public void setUpload(File upload) {
        this.upload = upload;
    }    

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
