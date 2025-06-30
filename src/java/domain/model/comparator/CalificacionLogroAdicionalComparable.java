/*
 * @(#)CalificacionLogroAdicionalComparable.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.comparator;

import domain.model.CalificacionLogroAdicionalId;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CalificacionLogroAdicionalComparable
        implements Comparator<CalificacionLogroAdicionalId>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @param c1
     * @param c2
     * @return
     */
    @Override
    public int compare(CalificacionLogroAdicionalId c1, CalificacionLogroAdicionalId c2) {
        return Comparator.comparingInt(CalificacionLogroAdicionalId::getClaReq)
                .thenComparingInt(CalificacionLogroAdicionalId::getClaAgno)
                .thenComparingInt(CalificacionLogroAdicionalId::getClaSem)
                .compare(c1, c2);
    }
}
