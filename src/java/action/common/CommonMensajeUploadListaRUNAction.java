package action.common;

import java.io.File;
import service.mensaje.CommonMensajeUploadListaRUNService;
import infrastructure.support.action.post.ActionPostCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonMensajeUploadListaRUNAction extends ActionPostCommonSupport {

    private File upload;
    private String uploadContentType;
    private String uploadFileName;

    @Override
    public String action() throws Exception {        
        return new CommonMensajeUploadListaRUNService().service(this, getGenericSession(), upload, uploadFileName, getKey());
    }

    /**
     *
     * @return
     */
    public File getUpload() {
        return upload;
    }

    /**
     *
     * @param upload
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     *
     * @return
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     *
     * @param uploadContentType
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     *
     * @return
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     *
     * @param uploadFileName
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
