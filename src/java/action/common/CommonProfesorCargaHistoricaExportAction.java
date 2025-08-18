/*
 * @(#)CommonProfesorCargaHistoricaExportAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import java.io.InputStream;

import service.profesor.ExportCargaHistoricaService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.AppStaticsUtil;
import infrastructure.util.common.CommonMaterialUtil;

/**
 * Procesa el action mapeado del request a la URL
 * CommonProfesorCargaHistoricaExport
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonProfesorCargaHistoricaExportAction extends ActionCommonSupport {
    
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
        contentDisposition = CommonMaterialUtil.getContentDispositionProfesor(getGenericSession(), getKey(),"CARGA HISTÓRICA");
        excelFile = new ExportCargaHistoricaService().service(getGenericSession(), contentDisposition, getKey());
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
