
package service.certificacion.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.RegistradorSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularGetGlosaService {

    public String service(GenericSession genericSession, RegistradorSession rs) {
        rs.setCertificadoList(ContextUtil.getDAO().getSolicitudCertificadoCarritoRepository(ActionUtil.getDBUser()).find(genericSession.getUserType(), genericSession.getRut()));    
        return SUCCESS;
    }
}
