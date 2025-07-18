/*
 * @(#)ProfesorEvaluacionExportPlanillaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.profesor.ProfesorEvaluacionExportPlanillaService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 * Procesa el action mapeado del request a la URL
 * ProfesorEvaluacionExportPlanilla
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ProfesorEvaluacionExportPlanillaAction extends ActionCommonSupport {

  private static final long serialVersionUID = 1L;
    ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String retValue = SUCCESS;
        try {
            ais = new ProfesorEvaluacionExportPlanillaService().service(getGenericSession(), getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;

    }

    public String getContentType() {
        return ais.getContentType();
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return ais.getInputStream();
    }

    /**
     * Method description
     *
     * @return
     */
    public String getName() {
        return ais.getName();
    }
}
