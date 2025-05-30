/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import domain.model.AluCar;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonFacultadUtil {

    /**
     *
     */
    private CommonFacultadUtil() {
    }

    /**
     *
     * @param asign
     * @return
     */
    public static String getNombrexAsign(Integer asign) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getNombreFacultadxAsign(asign);
    }

    public static String getNombrexTcarrera(Integer tcarrera, Integer especialidad) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getNombreFacultadxTcarrera(tcarrera, especialidad);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static Integer getUnidadxProf(Integer rut) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getUnidadFacultadxProf(rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static String getNombrexAyu(Integer rut) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getNombreFacultadxAyu(rut);
    }

    /**
     *
     * @param aluCar
     * @return
     */
    public static String getNombre(AluCar aluCar) {
        aluCar.setAluCarFunction();
        return aluCar.getAluCarFunction().getNombreFacultad();
    }
}
