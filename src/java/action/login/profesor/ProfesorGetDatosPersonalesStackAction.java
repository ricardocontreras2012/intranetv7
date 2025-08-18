
package action.login.profesor;

import service.login.profesor.ProfesorGetDatosPersonalesStackService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class ProfesorGetDatosPersonalesStackAction  extends ActionCommonSupport {
    
     private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorGetDatosPersonalesStackService().service(getGenericSession(), Manager.getProfesorSession(sesion), getKey());
    }    
}
