/*
 * @(#)VerificacionCertificadoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.verificacioncertificado;


import static org.apache.struts2.ServletActionContext.getRequest;
import service.verificacioncertificado.VerificacionCertificadoService;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.LogUtil.logInfo;

/**
 * Procesa el action mapeado del request a la URL VerificacionCertificado
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class VerificacionCertificadoQRAction extends ActionCommonSupport {

    private Integer folio;
    private String verificador;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception { 
        logInfo(":: IP:" + getRequest().getRemoteAddr() + " QR Folio=" + folio);
        return new VerificacionCertificadoService().service(this, sesion, folio, verificador);
    }    

    /**
     * Method description
     *
     * @param folio
     */
    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    /**
     * Method description
     *
     * @param verificador
     */
    public void setVerificador(String verificador) {
        this.verificador = verificador;
    }

    public Integer getFolio() {
        return folio;
    }

    public String getVerificador() {
        return verificador;
    }
}
