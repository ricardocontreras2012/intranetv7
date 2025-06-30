/*
 * @(#)RequisitoAdicionalTitPLanComparable.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.comparator;

import domain.model.RequisitoLogroAdicional;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RequisitoAdicionalTitPLanComparable implements Comparator<RequisitoLogroAdicional>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @param cc1
     * @param cc2
     * @return
     */
    @Override
    public int compare(RequisitoLogroAdicional cc1, RequisitoLogroAdicional cc2) {
        return Comparator
                .comparing((RequisitoLogroAdicional r) -> r.getId().getRlaCodCar(), Comparator.nullsFirst(Integer::compareTo))
                .thenComparing((RequisitoLogroAdicional r) -> r.getId().getRlaCodMen(), Comparator.nullsFirst(Integer::compareTo))
                .thenComparing((RequisitoLogroAdicional r) -> r.getId().getRlaCodPlan(), Comparator.nullsFirst(Integer::compareTo))
                .thenComparing((RequisitoLogroAdicional r) -> r.getRlaOrder(), Comparator.nullsFirst(Integer::compareTo)).compare(cc1, cc2);
    }
}
