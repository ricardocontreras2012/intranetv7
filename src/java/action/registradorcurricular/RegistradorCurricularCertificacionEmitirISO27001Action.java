/*
 * @(#)AlumnoCertificacionEmitirISO27001Action.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.registradorcurricular;

import java.io.InputStream;
import service.certificacion.registradorcurricular.RegistradorCurricularCertificacionEmitirISO27001Service;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.AppStaticsUtil;
import infrastructure.util.common.CommonCertificacionUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularCertificacionEmitirISO27001Action extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String description;
    private String name;
    private InputStream inputStream;
    private Integer tramite;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        RegistradorCurricularCertificacionEmitirISO27001Service serviceCert = new RegistradorCurricularCertificacionEmitirISO27001Service();
        
        Integer folio = CommonCertificacionUtil.getFolio();
        description = AppStaticsUtil.PDF_MIME;
        name = CommonCertificacionUtil.getNameFile(getGenericSession().getWorkSession(getKey()).getAluCar(), folio, 60);
        inputStream = serviceCert.service(getGenericSession(), tramite, name, folio,
                getKey());

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getTramite() {
        return tramite;
    }

    /**
     * Method description
     *
     *
     * @param tramite
     */
    public void setTramite(Integer tramite) {
        this.tramite = tramite;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
