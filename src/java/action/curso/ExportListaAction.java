/*
 * @(#)ExportListaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.curso;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.curso.ExportListaService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.AppStaticsUtil;
import static infrastructure.util.common.CommonMaterialUtil.getContentDispositionCurso;

/**
 * Procesa el action mapeado del request a la URL CommonCursoExportLista
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ExportListaAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private String contentDisposition;
    private InputStream excelFile;
    private String description;


    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {        
        description = AppStaticsUtil.XLS_MIME;
        contentDisposition = getContentDispositionCurso(getGenericSession(), getKey(),"NOMINA_CURSO");
        excelFile = new ExportListaService().service(getGenericSession(), contentDisposition,
                getKey());

        return SUCCESS;
    }

    public InputStream getExcelFile() {
        return excelFile;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public String getDescription() {
        return description;
    }
}
