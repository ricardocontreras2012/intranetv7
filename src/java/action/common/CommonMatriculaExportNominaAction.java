/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import static service.common.CommonMatriculaExportNominaService.service;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.AppStaticsUtil;
import infrastructure.util.common.CommonMaterialUtil;

/**
 *
 * @author Administrador
 */
public class CommonMatriculaExportNominaAction extends ActionCommonSupport {
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
        contentDisposition = CommonMaterialUtil.getContentDispositionSemAgno(getGenericSession(), getKey(),"NOMINA_MATRICULADOS");
        excelFile = service(getGenericSession(), contentDisposition,
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
