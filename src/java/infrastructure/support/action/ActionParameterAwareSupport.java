/*
 * @(#)ActionParameterAwareSupport.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.support.action;

import infrastructure.support.action.common.ActionCommonSupport;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.action.ParametersAware;

/**
 * Clase de la cual se extienden los actions se extiendende la aplicacion que
 * leen los parámetros del request en forma directa desde el formulario.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class ActionParameterAwareSupport extends ActionCommonSupport implements ParametersAware {

    private static final long serialVersionUID = 1L;
    private HttpParameters parameters;

    @Override
    public void withParameters(HttpParameters hp) {
        this.parameters = hp;
    }

    /**
     *
     * @return
     */
    public Map<String, String[]> getMapParameters() {
        return parameters.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Parameter.Request) // Filtrar solo parámetros de tipo Request
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new String[]{entry.getValue().getValue()} // Convertir el valor en un array de String
                ));
    }
}
