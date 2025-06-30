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
        return Comparator.comparingInt(CursoId::getCurAgno)
                .thenComparingInt(CursoId::getCurSem)
                .thenComparingInt(CursoId::getCurAsign)
                .thenComparing(CursoId::getCurElect)
                .thenComparing(CursoId::getCurCoord)
                .thenComparingInt(CursoId::getCurSecc)
                .compare(id1, id2);
    }
}
