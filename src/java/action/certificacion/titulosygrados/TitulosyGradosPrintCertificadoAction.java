
package action.certificacion.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.ActionValidationPosSupport;
import infrastructure.util.ActionInputStreamUtil;
import java.io.InputStream;
import service.certificacion.titulosygrados.TitulosyGradosPrintCertificadoService;

/**
 *
 * @author Ricardo
 */
public class TitulosyGradosPrintCertificadoAction extends  ActionValidationPosSupport { 

    private static final long serialVersionUID = 1L;
    TitulosyGradosPrintCertificadoService cert = new TitulosyGradosPrintCertificadoService();
    ActionInputStreamUtil ais;
    String rol;
    Integer resolucion;
    String fecha;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = cert.service(getGenericSession(), getKey(), getPos(), rol, resolucion, fecha);
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {                
        return isValidPos(getPos(),  
                getGenericSession().getWorkSession(getKey()).getExpedienteLogroList());
    }

    public String getDescription() {
        return ais.getContentType();
    }

    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setResolucion(Integer resolucion) {
        this.resolucion = resolucion;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
