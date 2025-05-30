/*
 * @(#)ActionReportSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support.action;

import infrastructure.support.action.common.ActionCommonSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase de la cual se extienden los actions se extiendende la aplicacion que
 * generan un reporte.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class ActionReportSupport extends ActionCommonSupport {

    private Map<String, Object> report = new HashMap<>();

    /**
     * Method description
     *
     * @return
     */
    public Map<String, Object> getReport() {
        return report;
    }

    /**
     * Method description
     *
     * @param report
     */
    public void setReport(Map<String, Object> report) {
        this.report = report;
    }
}
