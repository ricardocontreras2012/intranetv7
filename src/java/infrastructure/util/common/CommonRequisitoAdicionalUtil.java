/*
 * @(#)CommonRequisitoAdicionalUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util.common;

import domain.model.TrequisitoLogroAdicional;
import java.util.Optional;
import session.WorkSession;

/**
 * Clase utilitaria que proporciona métodos comunes relacionados con los "Requisitos Adicionales".
 * Esta clase contiene un método para obtener la descripción de un "Requisito Adicional" basado en su identificador.
 * 
 * @autor Ricardo Contreras S.
 */
public class CommonRequisitoAdicionalUtil {

    // Constructor privado para evitar la instanciación de la clase utilitaria
    private CommonRequisitoAdicionalUtil() {
    }

    /**
     * Obtiene la descripción del "Requisito Adicional" correspondiente al valor proporcionado.
     * Busca el valor en la lista de "TrequisitoLogroAdicional" de la sesión de trabajo proporcionada.
     * 
     * @param val el identificador a buscar en la lista de "TrequisitoLogroAdicional".
     * @param ws la sesión de trabajo que contiene la lista de "TrequisitoLogroAdicional".
     * @return la descripción del "Requisito Adicional" si se encuentra, o null si no se encuentra.
     */
    public static String getDescription(Integer val, WorkSession ws) {
        // Comprobación de valores nulos para evitar errores
        if (val == null || ws == null || ws.getTrequisitoLogroAdicionalList() == null) {
            return null;
        }

        // Usamos Streams para encontrar el "TrequisitoLogroAdicional" que coincida con el código y obtener su descripción
        Optional<String> description = ws.getTrequisitoLogroAdicionalList().stream()
                .filter(requisito -> requisito.getTrlaCod().equals(val))  // Filtramos por el código
                .map(TrequisitoLogroAdicional::getTrlaDes)                  // Extraemos la descripción
                .findFirst();                                             // Tomamos el primer resultado (si hay)

        // Retornamos la descripción si se encuentra, o null si no se encuentra
        return description.orElse(null);
    }
}
