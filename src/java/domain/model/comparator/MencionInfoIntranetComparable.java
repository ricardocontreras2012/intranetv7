/*
 * @(#)MencionInfoIntranetComparable.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.comparator;

import domain.model.MencionInfoIntranet;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MencionInfoIntranetComparable implements Comparator<MencionInfoIntranet>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @param m1
     * @param m2
     * @return
     */
    @Override
    public int compare(MencionInfoIntranet m1, MencionInfoIntranet m2) {
        return Integer.compare(m1.getId().getMiniCodCar() * 10 + m1.getId().getMiniCodMen(),
                m2.getId().getMiniCodCar() * 10 + m2.getId().getMiniCodMen());
    }
}
