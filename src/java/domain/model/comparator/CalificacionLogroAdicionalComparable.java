/*
 * @(#)CalificacionLogroAdicionalComparable.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.comparator;

import domain.model.CalificacionLogroAdicional;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CalificacionLogroAdicionalComparable
        implements Comparator<CalificacionLogroAdicional>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @param c1
     * @param c2
     * @return
     */
    @Override
    public int compare(CalificacionLogroAdicional c1, CalificacionLogroAdicional c2) {
        // Comparar por ClaReq
        int cmpClaReq = Integer.compare(c1.getId().getClaReq(), c2.getId().getClaReq());
        if (cmpClaReq != 0) {
            return cmpClaReq;
        }

        // Comparar por ClaAgno y ClaSem
        return Integer.compare(
                10 * c1.getId().getClaAgno() + c1.getId().getClaSem(),
                10 * c2.getId().getClaAgno() + c2.getId().getClaSem()
        );
    }
}
