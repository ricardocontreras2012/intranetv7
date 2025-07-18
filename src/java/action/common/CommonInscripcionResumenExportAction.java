/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.AppStaticsUtil;
import static infrastructure.util.common.CommonMaterialUtil.getContentDispositionSemAgno;
import java.io.InputStream;
import service.common.CommonInscripcionResumenExportService;


public final class CommonInscripcionResumenExportAction extends ActionCommonSupport {
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
        contentDisposition = getContentDispositionSemAgno(getGenericSession(), getKey(), "RESUMEN_INSCRIPCION");
        excelFile = new CommonInscripcionResumenExportService().service(getGenericSession(), contentDisposition,
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
