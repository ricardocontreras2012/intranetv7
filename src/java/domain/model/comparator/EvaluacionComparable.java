/*
 * @(#)EvaluacionComparable.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.comparator;

import domain.model.Evaluacion;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EvaluacionComparable implements Comparator<Evaluacion>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @param e1
     * @param e2
     * @return
     */
    @Override
    public int compare(Evaluacion e1, Evaluacion e2) {
        return Integer.compare(
                e1.getId().getEvalTeva() * 10 + e1.getId().getEvalEval(),
                e2.getId().getEvalTeva() * 10 + e2.getId().getEvalEval()
        );
    }
}
