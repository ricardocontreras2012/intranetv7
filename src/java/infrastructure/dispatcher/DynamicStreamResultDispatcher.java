/*
 * @(#)DynamicStreamResultDispatcher.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.dispatcher;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.result.StreamResult;

/**
 * Esta clase extiende `StreamResult` de Struts2 y permite personalizar la forma en 
 * que se manejan los resultados de tipo 'stream' (como la descarga de archivos), 
 * estableciendo dinámicamente el nombre del archivo y el tipo de contenido basados 
 * en los valores proporcionados durante la ejecución del action.
 *
 * @autor Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class DynamicStreamResultDispatcher extends StreamResult {

    private static final long serialVersionUID = 1L;

    /**
     * Este método sobrescribe la ejecución predeterminada del resultado `StreamResult` 
     * para personalizar el nombre del archivo y el tipo de contenido antes de enviarlo 
     * al cliente. Se obtiene el nombre del archivo y el tipo de contenido desde el 
     * contexto de la acción y luego se configura la cabecera de respuesta HTTP para 
     * que el archivo se descargue con los parámetros correctos.
     *
     * @param finalLocation La ubicación final del archivo a ser transmitido.
     * @param invocation El objeto `ActionInvocation` que contiene la información 
     *                  sobre el contexto de la acción.
     * @throws Exception Si ocurre un error durante la ejecución del resultado.
     */
    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        // Obtiene el nombre del archivo a descargar desde el contexto de la acción
        String downloadedFileName = (String) invocation.getStack().findValue(conditionalParse("name", invocation));

        // Establece el valor de la cabecera "Content-Disposition" para forzar la descarga del archivo
        contentDisposition = "attachment; filename=\"" + downloadedFileName + '"';

        // Obtiene el tipo de contenido (MIME type) desde el contexto de la acción
        contentType = invocation.getStack().findValue(conditionalParse("description", invocation)).toString();

        // Llama al método `doExecute` de la clase base para completar la ejecución del resultado
        super.doExecute(finalLocation, invocation);
    }
}
