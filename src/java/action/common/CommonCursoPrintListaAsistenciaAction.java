

/*
 * @(#)CommonListaCursoExportListaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import domain.model.AluCar;
import java.util.List;
import static service.common.CommonCursoPrintListaAsistenciaService.service;
import infrastructure.support.action.ActionReportSupport;

/**
 * Procesa el action mapeado del request a la URL CommonListaCursoExportLista
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonCursoPrintListaAsistenciaAction extends ActionReportSupport {

    private static final long serialVersionUID = 1L;
    private List<AluCar> nomina;
    private String contentDisposition;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        nomina = service(getGenericSession(), getReport(),
                getKey());

        return SUCCESS;
    }

    public List<AluCar> getNomina() {
        return nomina;
    }

    public void setNomina(List<AluCar> nomina) {
        this.nomina = nomina;
    }
    

    /**
     * Method description
     *
     * @return
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * Method description
     *
     * @param contentDisposition
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }
}