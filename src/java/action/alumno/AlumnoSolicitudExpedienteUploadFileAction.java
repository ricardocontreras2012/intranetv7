package action.alumno;

import infrastructure.support.action.post.ActionPostCommonSupport;
import java.io.File;
import static service.alumno.AlumnoSolicitudExpedienteUploadFileService.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvaro
 */
public class AlumnoSolicitudExpedienteUploadFileAction extends ActionPostCommonSupport{
    
    private static final long serialVersionUID = 1L;
    
    private File[] upload;
    private String[] uploadFileName;
    private Integer tdoc;
   
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), upload,  uploadFileName, tdoc, getKey());
    }

    public File[] getUpload() {
        return upload;
    }

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public Integer getTdoc() {
        return tdoc;
    }

    public void setTdoc(Integer tdoc) {
        this.tdoc = tdoc;
    }
    
    
}
