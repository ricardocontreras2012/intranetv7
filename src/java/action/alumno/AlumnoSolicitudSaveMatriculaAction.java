/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import java.io.File;
import service.solicitud.matricula.alumno.AlumnoSolicitudSaveMatriculaService;
import infrastructure.support.action.post.ActionPostCommonSupport;

/**
 *
 * @author Usach
 */
public class AlumnoSolicitudSaveMatriculaAction extends ActionPostCommonSupport{
    
    private static final long serialVersionUID = 1L;
    
    private File[] upload;
    private String[] uploadFileName;
    private String causa;
   
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {            
        return new AlumnoSolicitudSaveMatriculaService().service(this, getGenericSession(), upload,  uploadFileName, causa, getKey());
    } 

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }        
}
