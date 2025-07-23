/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support;

import domain.model.Mencion;
import java.io.Serializable;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class MencionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static Mencion getMencion(Integer menCodCar, Integer menCodMen)
    {
        return ContextUtil.getDAO().getMencionRepository(ActionUtil.getDBUser()).find(menCodCar, menCodMen);
    }
    
}
