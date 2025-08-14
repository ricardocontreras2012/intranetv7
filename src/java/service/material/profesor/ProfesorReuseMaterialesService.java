package service.material.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.MaterialApoyo;
import java.util.Map;
import domain.repository.MaterialApoyoRepository;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.util.AppStaticsUtil.APP_DB_USERS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonSequenceUtil;

import java.util.stream.IntStream;

/**
 * Servicio para reutilizar materiales de apoyo en un curso.
 * Este servicio maneja la reutilización de materiales de apoyo seleccionados por el profesor
 * y los asocia al curso correspondiente.
 * 
 * @version 1.0, 23/12/2024
 * @author Ricardo Contreras S.
 */
public final class ProfesorReuseMaterialesService {

    /**
     * Servicio que permite reutilizar materiales seleccionados por el profesor.
     * 
     * @param genericSession La sesión genérica que contiene la sesión de trabajo del profesor.
     * @param parameters Mapa de parámetros provenientes del formulario.
     * @param key La clave para acceder a los datos de la sesión.
     * @param keyParent La clave para acceder a la sesión del curso.
     * @return El estado de la acción (SUCCESS si el proceso fue exitoso).
     * @throws Exception Si ocurre algún error durante el servicio.
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key, String keyParent)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = genericSession.getWorkSession(keyParent).getCurso();

        // Inicia la transacción de la base de datos
        beginTransaction(ActionUtil.getDBUser());

        // Recorre la lista de materiales y reutiliza los seleccionados
        IntStream.range(0, ws.getOtrosTmaterial().size())
                .forEach(i -> {
                    IntStream.range(0, ws.getOtrosTmaterial().get(i).getMateriales().size())
                            .filter(j -> parameters.get("ck_" + i + '_' + j) != null)  // Filtra los materiales seleccionados
                            .forEach(j -> {
                                MaterialApoyo material = ws.getOtrosTmaterial().get(i).getMateriales().get(j);
                                doReuse(material, genericSession.getUserType(), curso,
                                        ContextUtil.getDAO().getMaterialApoyoRepository(ActionUtil.getDBUser()));
                            });
                });

        // Confirma la transacción
        commitTransaction();

        // Registra un log para el curso
        LogUtil.setLogCurso(genericSession.getRut(), curso);

        return SUCCESS;
    }

    /**
     * Reutiliza un material de apoyo y lo asocia a un curso.
     * 
     * @param materialBase El material base que se va a reutilizar.
     * @param userTypeShort El tipo de usuario (profesor, alumno, etc.).
     * @param curso El curso al que se va a asociar el material.
     * @param materialRepository La persistencia para almacenar el material.
     */
    private void doReuse(MaterialApoyo materialBase, String userTypeShort, Curso curso,
            MaterialApoyoRepository materialRepository) {
        Integer folio = CommonSequenceUtil.getDocumentSeq();
        MaterialApoyo material = new MaterialApoyo();

        // Copia las propiedades del material base al nuevo material
        material.setMatCorrel(folio);
        material.setCurso(curso);
        material.setMatArchivo(materialBase.getMatArchivo());
        material.setMatDescripcion(materialBase.getMatDescripcion());
        material.setMatRutAutor(materialBase.getMatRutAutor());
        material.setMatTipo(materialBase.getMatTipo());
        material.setMatPerfil(APP_DB_USERS.get(userTypeShort));
        material.setMatFechaArchivado(materialBase.getMatFechaArchivado());
        material.setMatFechaHabilitacion(getSysdate());

        // Guarda el nuevo material reutilizado
        materialRepository.makePersistent(material);
    }
}
