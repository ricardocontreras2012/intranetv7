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
        return Integer.compare(
                calculateComparisonValue(cc1),
                calculateComparisonValue(cc2)
        );
    }

    private int calculateComparisonValue(RequisitoLogroAdicional cc) {
        return cc.getId().getRlaCodCar() * 1000
                + cc.getId().getRlaCodMen() * 100
                + cc.getId().getRlaCodPlan() * 10
                + cc.getRlaOrder();
    }
}
