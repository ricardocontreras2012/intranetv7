/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import java.io.InputStream;
import session.GenericSession;
import infrastructure.util.common.CommonArchivoUtil;

/**
 *
 * @author Ricardo
 */
public class CommonPrintPDFDownloadService {

    public static InputStream service(GenericSession genericSession, String key) {
        try {
            return CommonArchivoUtil.getFile(genericSession.getWorkSession(key).getPdfTempFile(), "tmp");
        } catch (Exception e) {return null;
        }
    }
}
