
package infrastructure.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utilidad para gestionar el pool de conexiones para el log.
 * <p>
 * Esta clase es responsable de crear, mantener y cerrar el pool de conexiones a la base de datos
 * utilizando HikariCP, un proveedor de pool de conexiones eficiente y rápido.
 * </p>
 * 
 * @author Administrador
 */
public class PoolLoggingUtil {

    private static final Logger LOGGER = LogManager.getLogger(PoolLoggingUtil.class);

    private static JasyptDecrypterUtil jd = new JasyptDecrypterUtil();
    private static HikariDataSource dataSource;

    // Constructor privado para evitar la creación de instancias.
    private PoolLoggingUtil() {}

    /**
     * Obtiene una conexión del pool de conexiones.
     * <p>
     * Si el pool de conexiones no está creado, lo inicializa antes de obtener la conexión.
     * </p>
     * 
     * @return Una conexión a la base de datos, o null si ocurre un error.
     */
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (Exception e) {
            LOGGER.error("Error obteniendo conexión del pool de conexiones", e);
            return null;
        }
    }

    /**
     * Obtiene el DataSource para acceder al pool de conexiones.
     * <p>
     * Si el DataSource no ha sido inicializado, este método crea el pool de conexiones bajo demanda.
     * </p>
     * 
     * @return El DataSource del pool de conexiones.
     */
    private static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            createDataSource();
        }
        return dataSource;
    }

    /**
     * Crea el pool de conexiones utilizando la configuración adecuada.
     * <p>
     * Este método configura y crea el pool de conexiones HikariCP utilizando parámetros
     * sensibles descifrados a través de Jasypt.
     * </p>
     */
    private static void createDataSource() {
        HikariConfig hikariConfig = getHikariConfig();
        LOGGER.info("Creando LogPool de conexiones");
        dataSource = new HikariDataSource(hikariConfig);
    }

    /**
     * Configura los parámetros de HikariCP para la conexión a la base de datos.
     * <p>
     * Los parámetros sensibles, como la URL de la base de datos, el usuario y la contraseña, se
     * descifran utilizando Jasypt para proteger los datos.
     * </p>
     * 
     * @return La configuración de HikariCP.
     */
    private static HikariConfig getHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(jd.decrypt("CfYSPZOp9qPcT5R10c0ylI69cewaoJ3acieau7mTj38rT/QyV7ButC2uZZuuK3JT94a+sYWd5ng="));
        hikariConfig.setUsername(jd.decrypt("7UrATw0noglCEQ3ZA4ItdJNBMLyYnA4m"));
        hikariConfig.setPassword(jd.decrypt("s0FYyrVYjTNYlYeqjtFueAYd/TI20lYPGSyD7W/l/IvDuJAZ2UjwfA=="));
        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setPoolName("LogPool");
        hikariConfig.setMaximumPoolSize(50);  // Número máximo de conexiones
        hikariConfig.setMinimumIdle(5);      // Número mínimo de conexiones inactivas
        return hikariConfig;
    }

    /**
     * Cierra el pool de conexiones.
     * <p>
     * Este método se asegura de cerrar correctamente las conexiones y liberar los recursos.
     * </p>
     */
    public static void closePool() {
        if (dataSource != null) {
            LOGGER.info("Cerrando LogPool de conexiones");
            dataSource.close();
        } else {
            LOGGER.warn("No hay pool de conexiones para cerrar");
        }
    }
}

