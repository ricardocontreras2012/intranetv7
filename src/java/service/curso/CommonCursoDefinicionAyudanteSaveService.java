/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.curso;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.DocenteHorario;
import domain.model.Curso;
import domain.model.CursoId;
import static java.lang.Integer.parseInt;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionAyudanteSaveService {   
    /**
     * Este método principal realiza las siguientes operaciones:
     * 1. Elimina los docentes que ya no están asignados según los parámetros.
     * 2. Añade los nuevos docentes según los parámetros.
     * 3. Actualiza el curso en la sesión y registra la actividad.
     *
     * @param genericSession La sesión genérica que contiene la información del usuario.
     * @param parameters El mapa de parámetros enviados, que contiene los datos de los docentes y horarios.
     * @param key La clave que identifica la sesión de trabajo.
     * @return El estado de la acción (SUCCESS si se completó correctamente).
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        // Obtener la sesión de trabajo del usuario a partir de la clave proporcionada.
        WorkSession ws = genericSession.getWorkSession(key);
        
        // Obtener el usuario actual que está ejecutando la acción.
        String user = ActionUtil.getDBUser();
        
        // Obtener la posición del curso en la lista de cursos en la sesión.
        Integer pos = parseInt(parameters.get("pos")[0]);
        
        // Obtener el identificador del curso desde la lista en la sesión de trabajo.
        CursoId id = ws.getCursoList().get(pos).getId();

        // 1. Eliminar docentes que no están en los parámetros
        for (DocenteHorario dh : ws.getDocenteHorarioList()) {
            boolean found = false;
            String hor = "";
            
            // Iterar sobre los parámetros para encontrar los docentes activos.
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                String field = entry.getKey();

                // Filtrar las entradas que corresponden a docentes (comienzan con "ayu_").
                if (field.startsWith("ayu_")) {
                    int posAux = field.lastIndexOf('_');
                    int idAux = parseInt(field.substring(posAux + 1));  // Extraer el id del parámetro.

                    // Obtener el RUT del docente y el horario asociado.
                    String rutAux = parameters.get("ayu_" + idAux)[0];
                    int rut = parseInt(rutAux.substring(0, rutAux.length() - 2));  // Eliminar el dígito verificador.
                    String horario = parameters.get("horAyu_" + idAux)[0];

                    if (!"".equals(horario)) {
                        // Separar el día y el módulo del horario.
                        String dia = horario.substring(0, 1);  // El primer carácter es el día.
                        int modulo = parseInt(horario.substring(1));  // El resto es el módulo.

                        // Verificar si el docente ya está asignado con el mismo RUT y horario.
                        if (dh.getAyudante().getAyuRut() == rut
                                && dh.getDhorDia() != null && dh.getDhorDia().equals(dia)
                                && dh.getDhorModulo() == modulo) {
                            hor = dh.getDhorDia() + dh.getDhorModulo();
                            found = true;
                            break;
                        }
                    } else {
                        // Si no hay horario, verificar solo por el RUT.
                        if (dh.getAyudante().getAyuRut() == rut) {
                            found = true;
                            break;
                        }
                    }
                }
            }

            // Si el docente no se encuentra en los parámetros, se elimina.
            if (!found) {
                ContextUtil.getDAO().getDocenteHorarioRepository(user).removeDocente(
                        id, dh.getAyudante().getAyuRut(),
                        hor, "E"
                );
            }
        }

        // 2. Añadir nuevos docentes desde los parámetros.
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String field = entry.getKey();

            // Filtrar solo las entradas que corresponden a docentes.
            if (field.startsWith("ayu_")) {
                int posAux = field.lastIndexOf('_');
                int idAux = parseInt(field.substring(posAux + 1));

                // Obtener el RUT y horario del docente.
                String rutAux = parameters.get("ayu_" + idAux)[0];
                int rut = parseInt(rutAux.substring(0, rutAux.length() - 2));  // Eliminar el dígito verificador.
                String horario = parameters.get("horAyu_" + idAux)[0];
                boolean docenteExistente;
                String hor = "";

                if (horario != null && !"".equals(horario)) {
                    hor = horario;                    // Asignar el horario.

                    // Separar el día y el módulo.
                    String dia = horario.substring(0, 1);  // El primer carácter es el día.
                    int modulo = parseInt(horario.substring(1));  // El resto es el módulo.

                    // Verificar si ya existe un docente con el mismo RUT y horario.
                    docenteExistente = ws.getDocenteHorarioList().stream()
                            .anyMatch(dh -> dh.getAyudante().getAyuRut() == rut
                            && dh.getDhorDia() != null && dh.getDhorDia().equals(dia)
                            && dh.getDhorModulo() == modulo);
                } else {
                    // Si no hay horario, solo verificar por el RUT.
                    docenteExistente = ws.getDocenteHorarioList().stream()
                            .anyMatch(dh -> dh.getAyudante().getAyuRut() == rut
                            );
                }

                // Si no existe el docente, se añade a la lista.
                if (!docenteExistente) {
                    ContextUtil.getDAO().getDocenteHorarioRepository(user).addDocente(id, rut, hor, "E");
                }
            }
        }

        // Obtener el curso actualizado desde la base de datos.
        Curso cursoAux = ContextUtil.getDAO().getCursoRepository(user).find(id);

        // Actualizar la lista de cursos en la sesión de trabajo.
        ws.getCursoList().set(pos, cursoAux);

        // Establecer la posición y el curso en la sesión de trabajo.
        ws.setPos(pos);
        ws.setCurso(cursoAux);

        // Registrar la acción en los logs.
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        // Devolver el estado de la acción (éxito).
        return SUCCESS;
    }
}
