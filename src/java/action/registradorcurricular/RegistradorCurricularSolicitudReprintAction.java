/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import service.reincorporacion.registradorcurricular.RegistradorCurricularReprintSolicitudService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularSolicitudReprintAction  extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer folio;
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new RegistradorCurricularReprintSolicitudService().service(getGenericSession(), getKey(), folio);
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

}
