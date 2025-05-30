package infrastructure.util;

import domain.model.WebUser;
import java.sql.*;
import java.util.*;

/**
 * Clase que gestiona la obtención y procesamiento de usuarios desde una base de datos.
 * Incluye métodos para cargar usuarios, y categorizar a los usuarios por diferentes atributos
 * como tipo, autoridad y secretaría.
 */
public class StartUtil {

    private final Connection connection;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     *
     * @param connection La conexión a la base de datos que se utilizará para consultar la información.
     */
    public StartUtil(Connection connection) {
        this.connection = connection;
    }

    /**
     * Obtiene una lista de usuarios desde la base de datos, excluyendo el usuario con ID 'LO'.
     * Los usuarios se ordenan por su atributo 'wu_jsp' de forma ascendente.
     *
     * @return Una lista de objetos {@link WebUser} representando a los usuarios obtenidos de la base de datos.
     */
    public List<WebUser> find() {
        List<WebUser> usuarios = new ArrayList<>();
        String query = "SELECT wu_user, wu_des, wu_jsp, wu_normal_plus, wu_bd, wu_login, wu_autoridad, wu_secretaria " +
                "FROM web_user WHERE wu_user NOT IN ('LO') ORDER BY wu_jsp ASC";

        // Usamos try-with-resources para garantizar el cierre automático de los recursos.
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                // Creamos un nuevo objeto WebUser con los valores obtenidos de la base de datos
                WebUser usuario = new WebUser();
                usuario.setWuUser(rs.getString("WU_USER"));
                usuario.setWuJsp(rs.getString("WU_JSP"));
                usuario.setWuNormalPlus(rs.getString("WU_NORMAL_PLUS"));
                usuario.setWuBD(rs.getString("WU_BD"));
                usuario.setWuAutoridad(rs.getString("WU_AUTORIDAD"));

                // Agregamos el usuario a la lista
                usuarios.add(usuario);
                // Imprimimos el ID del usuario (esto puede ser útil para debugging)
                System.out.println("userid -> " + usuario.getWuUser());
            }
        } catch (SQLException e) {
            // Si ocurre una excepción, se imprime la traza del error (se puede mejorar usando un logger)
            e.printStackTrace();
        }

        return usuarios;
    }

    /**
     * Llena un mapa con los usuarios que tienen el atributo 'wu_normal_plus' igual a "N".
     * Este método se utiliza para clasificar los usuarios normales (N).
     *
     * @param lista Mapa que almacenará los usuarios con su respectivo valor de 'wu_jsp'.
     * @param usuarios Lista de objetos {@link WebUser} con los usuarios cargados desde la base de datos.
     */
    public void setNormales(Map<String, String> lista, List<WebUser> usuarios) {
        setUsuariosPorTipo(lista, usuarios, "N");
    }

    /**
     * Llena un mapa con los usuarios que tienen el atributo 'wu_normal_plus' igual a "P".
     * Este método se utiliza para clasificar los usuarios de tipo Plus (P).
     *
     * @param lista Mapa que almacenará los usuarios con su respectivo valor de 'wu_jsp'.
     * @param usuarios Lista de objetos {@link WebUser} con los usuarios cargados desde la base de datos.
     */
    public void setPlus(Map<String, String> lista, List<WebUser> usuarios) {
        setUsuariosPorTipo(lista, usuarios, "P");
    }

    /**
     * Llena un mapa con los usuarios y sus respectivas bases de datos.
     * Este método categoriza a los usuarios por su base de datos asociada ('wu_bd').
     *
     * @param lista Mapa que almacenará los usuarios con su respectiva base de datos.
     * @param usuarios Lista de objetos {@link WebUser} con los usuarios cargados desde la base de datos.
     */
    public void setBD(Map<String, String> lista, List<WebUser> usuarios) {
        // Se utiliza un bucle para recorrer la lista de usuarios y agregar al mapa los valores correspondientes.
        usuarios.forEach(usuario -> lista.put(usuario.getWuUser(), usuario.getWuBD()));
    }

    /**
     * Llena un mapa con los usuarios que tienen el atributo 'wu_autoridad' igual a "S".
     * Este método se utiliza para clasificar a los usuarios con autoridad.
     *
     * @param lista Mapa que almacenará los usuarios con su respectivo valor de 'wu_user' (usuario).
     * @param usuarios Lista de objetos {@link WebUser} con los usuarios cargados desde la base de datos.
     */
    public void setAutoridades(Map<String, String> lista, List<WebUser> usuarios) {
        usuarios.stream()
                // Filtra solo los usuarios con autoridad ('S')
                .filter(usuario -> "S".equals(usuario.getWuAutoridad()))
                // Agrega al mapa el ID del usuario con su nombre de usuario
                .forEach(usuario -> lista.put(usuario.getWuUser(), usuario.getWuUser()));
    }

    /**
     * Llena un mapa con los usuarios que tienen el atributo 'wu_secretaria' igual a "S".
     * Este método se utiliza para clasificar a los usuarios secretarios.
     *
     * @param lista Mapa que almacenará los usuarios con su respectivo valor de 'wu_user' (usuario).
     * @param usuarios Lista de objetos {@link WebUser} con los usuarios cargados desde la base de datos.
     */
    public void setSecretarias(Map<String, String> lista, List<WebUser> usuarios) {
        usuarios.stream()
                // Filtra solo los usuarios que son secretarias ('S')
                .filter(usuario -> "S".equals(usuario.getWuSecretaria()))
                // Agrega al mapa el ID del usuario con su nombre de usuario
                .forEach(usuario -> lista.put(usuario.getWuUser(), usuario.getWuUser()));
    }

    /**
     * Método común utilizado para agregar usuarios al mapa según el tipo de usuario (Normal o Plus).
     * Este método centraliza la lógica para evitar duplicación de código entre {@link #setNormales} y {@link #setPlus}.
     *
     * @param lista Mapa que almacenará los usuarios con su respectivo valor de 'wu_jsp'.
     * @param usuarios Lista de objetos {@link WebUser} con los usuarios cargados desde la base de datos.
     * @param tipo El tipo de usuario que se está buscando ("N" para normales o "P" para plus).
     */
    private void setUsuariosPorTipo(Map<String, String> lista, List<WebUser> usuarios, String tipo) {
        usuarios.stream()
                // Filtra los usuarios según el valor de 'wu_normal_plus' (N o P)
                .filter(usuario -> tipo.equals(usuario.getWuNormalPlus()))
                // Agrega al mapa el ID del usuario con su respectivo valor de 'wu_jsp'
                .forEach(usuario -> lista.put(usuario.getWuUser(), usuario.getWuJsp()));
    }
}
