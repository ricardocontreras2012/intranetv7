/*
 * @(#)CursoComparable.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.comparator;

import domain.model.CursoId;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CursoComparable implements Comparator<CursoId>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @param id1
     * @param id2
     * @return
     */
    @Override
    public int compare(CursoId id1, CursoId id2) {
        int retValue;
        
        retValue = Integer.compare(id1.getCurAgno(), id2.getCurAgno());
        if (retValue != 0) {
            return retValue;
        }
        
        retValue = Integer.compare(id1.getCurSem(), id2.getCurSem());
        if (retValue != 0) {
            return retValue;
        }

        // Comparar por Asignatura si Agno y Semestre son iguales
        retValue = Integer.compare(id1.getCurAsign(), id2.getCurAsign());
        if (retValue != 0) {
            return retValue;
        }
        
        retValue = id1.getCurElect().compareTo(id2.getCurElect());
        if (retValue != 0) {
            return retValue;
        }
        
        retValue = id1.getCurCoord().compareTo(id2.getCurCoord());
        if (retValue != 0) {
            return retValue;
        }
        
         retValue = Integer.compare(id1.getCurSecc(), id2.getCurSecc());
        if (retValue != 0) {
            return retValue;
        }
        
        return 0;
    }
}
