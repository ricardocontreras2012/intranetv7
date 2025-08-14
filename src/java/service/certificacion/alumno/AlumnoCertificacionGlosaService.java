/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.certificacion.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class AlumnoCertificacionGlosaService {
  /**
     * Method Servicio
     *
     * @param correl
     * @return Action status
     */
    public String service(Integer correl) { 
        // Ojo por ahora 1 pero despues puede ser el que correponda al carrito
        ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).setEstadoCarrito(correl, 1, "GL");

        return SUCCESS;     
    }
}
