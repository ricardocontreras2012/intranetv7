/*
 * @(#)CursoComparable.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.comparator;

import domain.model.Curso;
import java.io.Serializable;
import java.util.Comparator;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CursoComparable implements Comparator<Curso>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @param c1
     * @param c2
     * @return
     */
    @Override
    public int compare(Curso c1, Curso c2) {
        // Comparar por Agno y Semestre
        int retValue = Integer.compare(
                10 * c1.getId().getCurAgno() + c1.getId().getCurSem(),
                10 * c2.getId().getCurAgno() + c2.getId().getCurSem()
        );
        if (retValue != 0) {
            return retValue;
        }

        // Comparar por Asignatura si Agno y Semestre son iguales
        retValue = Integer.compare(c1.getId().getCurAsign(), c2.getId().getCurAsign());
        if (retValue != 0) {
            return retValue;
        }

        // Si Agno, Semestre y Asignatura son iguales, comparar por nombre completo
        return ContextUtil.getCollator().compare(c1.getNombreFull(), c2.getNombreFull());
    }
}
