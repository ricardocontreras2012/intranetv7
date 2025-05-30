/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import static service.common.CommonPrintPDFDownloadService.service;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.AppStaticsUtil;

/**
 *
 * @author Ricardo
 */
public class CommonPrintPDFDownloadAction extends ActionCommonSupport {

    private String description;
    private InputStream inputStream;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String retValue = SUCCESS;
        
        description = AppStaticsUtil.PDF_MIME;

        try {
            inputStream = service(getGenericSession(), getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
    }


    /**
     * Method description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return inputStream;
    }
}

