/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import domain.model.Area;
import java.util.List;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Usach
 */
public class CommonJefeCarreraUtil {
    
    public static List<Area> getAreas(Integer rut, String type)
    {
        return ContextUtil.getDAO().getAreaRepository(ActionUtil.getDBUser()).getAreas(rut, type);
    } 
    
    public static List<Integer> getMinors(Integer rut, String type)
    {
        return ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).getMinors(rut, type);
    }  
}