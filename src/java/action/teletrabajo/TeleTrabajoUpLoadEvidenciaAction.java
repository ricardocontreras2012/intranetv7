/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.teletrabajo;

import java.io.File;
import static service.teletrabajo.TeleTrabajoUpLoadEvidenciaService.service;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Javier
 */
public final class TeleTrabajoUpLoadEvidenciaAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    
    private File[] uploadedFile;
    private String[] uploadedFileFileName;
    private String[] uploadedFileContentType;
    private String descripcionEvidencia;
    private Integer posT;
    private Integer idList;
    
    /**
     * Metodo que se comunica con el servicio que realizara la 
     * subida/actulaizacion de la evidencia.
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        //cambio de variable para mejorar comprension
        Integer correl = idList;
        String retValue = service(this, getGenericSession(), getKey(), Manager.getTeleTrabajoSession(sesion), uploadedFile, uploadedFileFileName, uploadedFileContentType, posT, correl, descripcionEvidencia);
        return retValue;
    }

    public File[] getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(File[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String[] getUploadedFileFileName() {
        return uploadedFileFileName;
    }

    public void setUploadedFileFileName(String[] uploadedFileFileName) {
        this.uploadedFileFileName = uploadedFileFileName;
    }

    public String[] getUploadedFileContentType() {
        return uploadedFileContentType;
    }

    public void setUploadedFileContentType(String[] uploadedFileContentType) {
        this.uploadedFileContentType = uploadedFileContentType;
    }

    public Integer getPosT() {
        return posT;
    }

    public void setPosT(Integer posT) {
        this.posT = posT;
    }

    public String getDescripcionEvidencia() {
        return descripcionEvidencia;
    }

    public void setDescripcionEvidencia(String descripcionEvidencia) {
        this.descripcionEvidencia = descripcionEvidencia;
    }

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }
    
}
