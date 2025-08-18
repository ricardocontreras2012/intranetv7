
package action.mensaje;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import service.mensaje.DownloadAttachFileService;
import infrastructure.util.ActionInputStreamUtil;

/**
 * Procesa el action mapeado del request a la URL
 * CommonMensajeDownloadAttachFile
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class DownloadAttachFileAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private Integer correl;
    private String name;
    private String key;

    ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return
     */
    @Override
    public String execute() {
        String retValue = SUCCESS;
        try {  
            ais = new DownloadAttachFileService().service(correl, name, key);
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
    }

    public String getContentType() {
        return ais.getContentType();
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return ais.getInputStream();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCorrel() {
        return correl;
    }

    /**
     * Method description
     *
     * @param correl
     */
    public void setCorrel(Integer correl) {
        this.correl = correl;
    }  

    public void setKey(String key) {
        this.key = key;
    }
}
