/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import service.certificacion.registradorcurricular.RegistradorCurricularCertificadosEmitirGlosaService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularCertificadosEmitirGlosaAction extends ActionCommonSupport {
    
    private Integer pos;
    private String glosa;
    private Integer correl;

    @Override
    public String action() throws Exception {
        return new RegistradorCurricularCertificadosEmitirGlosaService().service(this, getGenericSession(), Manager.getRegistradorSession(sesion), pos, glosa);
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
   
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public Integer getCorrel() {
        return correl;
    }

    public void setCorrel(Integer correl) {
        this.correl = correl;
    }

}