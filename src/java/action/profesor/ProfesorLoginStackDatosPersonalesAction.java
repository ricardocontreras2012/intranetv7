
package action.profesor;

import service.profesor.ProfesorLoginStackDatosPersonalesService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class ProfesorLoginStackDatosPersonalesAction  extends ActionCommonSupport {
    
     private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorLoginStackDatosPersonalesService().service(getGenericSession(), Manager.getProfesorSession(sesion), getKey());
    }    
}
