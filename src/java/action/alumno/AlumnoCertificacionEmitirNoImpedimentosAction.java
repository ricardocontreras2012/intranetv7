/*
 * @(#)AlumnoCertificacionEmitirAlumnoRegularAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.alumno.AlumnoCertificacionEmitirNoImpedimentosService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class AlumnoCertificacionEmitirNoImpedimentosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    AlumnoCertificacionEmitirNoImpedimentosService serviceCert = new AlumnoCertificacionEmitirNoImpedimentosService();
    ActionInputStreamUtil ais;
    private Integer correl;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = serviceCert.service(correl);
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;
    }    

    public String getDescription() {
        return ais.getContentType();
    }

    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    } 

    public void setCorrel(Integer correl) {
        this.correl = correl;
    }
}
