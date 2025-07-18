/*
 * @(#)CommonExportControlAsistenciaAction.java
 *
 * Copyright (c) 2024 FAE-USACH
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.common.CommonExportControlAsistenciaService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.AppStaticsUtil;
import static infrastructure.util.common.CommonMaterialUtil.getContentDispositionSemAgno;

/**
 *
 * @author Felipe and Javier
 */
public final class CommonExportControlAsistenciaAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private String contentDisposition;
    private InputStream excelFile;
    private String description;
    
    private Integer agno;
    private Integer sem;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        this.agno = getGenericSession().getWorkSession(getKey()).getAgnoAct();
        this.sem = getGenericSession().getWorkSession(getKey()).getSemAct();
        
        description = AppStaticsUtil.XLS_MIME;
        contentDisposition = getContentDispositionSemAgno(getGenericSession(), getKey(), "CONTROL_ASISTENCIA");
        excelFile = new CommonExportControlAsistenciaService().service(getGenericSession(), getKey(), contentDisposition, agno, sem);

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

    public Integer getAgno() {
        return agno;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }
    
}
