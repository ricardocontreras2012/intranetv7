
package service.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.RegistradorSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularCertificadosGetGlosaService {

    public String service(GenericSession genericSession, RegistradorSession rs) {
        rs.setCertificadoList(ContextUtil.getDAO().getSolicitudCertificadoCarritoPersistence(ActionUtil.getDBUser()).find(genericSession.getUserType(), genericSession.getRut()));    
        return SUCCESS;
    }
}
