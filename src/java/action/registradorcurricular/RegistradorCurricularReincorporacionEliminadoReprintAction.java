/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import service.reincorporacion.registradorcurricular.RegistradorCurricularReprintEliminadoService;
import infrastructure.support.action.ActionReportSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularReincorporacionEliminadoReprintAction extends ActionReportSupport {

    private static final long serialVersionUID = 1L;
    
    private Integer solicitud;

    @Override
    public String action() throws Exception {
        return new RegistradorCurricularReprintEliminadoService().service(getGenericSession(), getKey(), solicitud);
    }

    public Integer getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Integer solicitud) {
        this.solicitud = solicitud;
    }
}
