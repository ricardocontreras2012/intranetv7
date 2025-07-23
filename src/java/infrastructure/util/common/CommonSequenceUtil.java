/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class CommonSequenceUtil {
    
    static public Integer getDocumentSeq()
    {
        return ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getSecuenciaMaterial();
    }
    
}
