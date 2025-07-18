/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import java.io.File;
import service.alumno.AlumnoSolicitudSolicitudJustificativoPEPAddAttachService;
import infrastructure.support.action.post.ActionPostCommonSupport;

/**
 *
 * @author Usach
 */
public class AlumnoSolicitudSolicitudJustificativoPEPAddAttachAction extends ActionPostCommonSupport{
    
    private static final long serialVersionUID = 1L;
    
    private File[] upload;
    private String[] uploadFileName;
   
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {                  
        return new AlumnoSolicitudSolicitudJustificativoPEPAddAttachService().service(this, getGenericSession(), upload,  uploadFileName, getKey());
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
}
