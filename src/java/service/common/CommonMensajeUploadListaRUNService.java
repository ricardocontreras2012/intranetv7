package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import infrastructure.util.common.CommonSequenceUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonMensajeUploadListaRUNService {

    /**
     *
     * @param action
     * @param genericSession
     * @param upload
     * @param uploadFileName
     * @param key
     * @return
     * @throws Exception
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, File upload,
            String uploadFileName, String key)
            throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        Integer folio = CommonSequenceUtil.getDocumentSeq();
        String nombre = getAttachFileName(uploadFileName,"_LISTA", folio);

        doUpload(action, upload, nombre,"tmp");
        ws.getMensajeSupport().getCurrentNode().setValue(nombre);
        
        return SUCCESS;
    }
}
