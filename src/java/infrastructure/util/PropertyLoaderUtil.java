package infrastructure.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase utilitaria para cargar archivos de propiedades desde el classpath.
 * Esta clase soporta cargar archivos con extensión `.properties` y `.xml`.
 * Proporciona métodos para cargar las propiedades utilizando un cargador de clases
 * especificado o el cargador de clases del hilo actual.
 * 
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class PropertyLoaderUtil {

    // Constantes para los sufijos de los archivos
    private static final String PROPERTIES_SUFFIX = ".properties";
    private static final String XML_SUFFIX = ".xml";
    
    // Bandera para lanzar una excepción si no se encuentra el archivo
    private static final boolean THROW_ON_LOAD_FAILURE = true;

    /**
     * Carga un archivo de propiedades desde el classpath. El archivo puede tener
     * la extensión `.properties` o `.xml`. Si no se proporciona la extensión,
     * se asumirá que es un archivo `.properties`.
     *
     * @param name El nombre del archivo de propiedades (sin la barra inicial ni la extensión).
     * @param loader El cargador de clases a usar (si es null, se usa el cargador de clases del sistema).
     * @return Un objeto Properties cargado desde el archivo.
     * @throws IllegalArgumentException Si el archivo no se encuentra y THROW_ON_LOAD_FAILURE es true.
     * @throws RuntimeException Si ocurre un error de entrada/salida durante la carga del archivo.
     */
    private static Properties loadProperties(String name, ClassLoader loader) {
        // Validación del nombre del recurso
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre del recurso no debe ser nulo ni vacío.");
        }

        // Normalizar el nombre del recurso: eliminar la barra inicial si está presente y añadir el sufijo apropiado
        name = name.startsWith("/") ? name.substring(1) : name;
        String resourceName = name.endsWith(XML_SUFFIX) ? name : name + PROPERTIES_SUFFIX;

        // Cargar las propiedades
        Properties result = new Properties();
        try (InputStream in = (loader != null ? loader : ClassLoader.getSystemClassLoader()).getResourceAsStream(resourceName)) {
            // Verificar si el recurso existe
            if (in == null) {
                if (THROW_ON_LOAD_FAILURE) {
                    throw new IllegalArgumentException("No se pudo encontrar el recurso: " + resourceName);
                }
                return null;  // El recurso no fue encontrado
            }

            // Cargar las propiedades según el tipo de archivo (XML o .properties)
            if (resourceName.endsWith(XML_SUFFIX)) {
                result.loadFromXML(in);
            } else {
                result.load(in);  // Cargar desde un archivo .properties
            }
        } catch (IOException e) {
            // Envolver la IOException en una RuntimeException para una gestión más sencilla
            throw new RuntimeException("Error al cargar las propiedades desde " + resourceName, e);
        }

        return result;
    }

    /**
     * Método conveniente para cargar propiedades utilizando el cargador de clases del hilo actual.
     * Este método usa el cargador de clases del hilo actual para localizar el archivo de propiedades.
     * 
     * @param name El nombre del archivo de propiedades (sin barra inicial ni extensión).
     * @return Un objeto Properties cargado desde el archivo.
     * @throws IllegalArgumentException Si el archivo no se encuentra y THROW_ON_LOAD_FAILURE es true.
     * @throws RuntimeException Si ocurre un error de entrada/salida durante la carga del archivo.
     */
    public static Properties loadProperties(String name) {
        return loadProperties(name, Thread.currentThread().getContextClassLoader());
    }
}
