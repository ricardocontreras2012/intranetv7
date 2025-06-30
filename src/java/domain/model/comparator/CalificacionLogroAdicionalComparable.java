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

    @Override
    public int compare(CalificacionLogroAdicional c1, CalificacionLogroAdicional c2) {
        return Comparator.comparingInt((CalificacionLogroAdicional c) -> c.getId().getClaReq())
                .thenComparingInt(c -> c.getId().getClaAgno())
                .thenComparingInt(c -> c.getId().getClaSem())
                .compare(c1, c2);
    }
}
