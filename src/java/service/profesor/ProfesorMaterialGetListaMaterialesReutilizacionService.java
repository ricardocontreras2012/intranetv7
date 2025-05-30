package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.CursoId;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonCursoUtil;
import static infrastructure.util.common.CommonMaterialUtil.getListaOtrosMateriales;

/**
 * Servicio para obtener la lista de materiales de reutilización de un curso.
 * 
 * Este servicio recupera la lista de materiales reutilizables asociados a un curso y lo guarda en la sesión de trabajo.
 * 
 * @version 7, 20/11/2013
 * @autor Ricardo Contreras S.
 */
public final class ProfesorMaterialGetListaMaterialesReutilizacionService {

    /**
     * Servicio que obtiene la lista de materiales reutilizables para el curso en sesión.
     * 
     * @param genericSession La sesión genérica que contiene la sesión de trabajo del profesor.
     * @param key La clave para acceder a los datos de la sesión.
     * @return El estado de la acción (SUCCESS si el proceso fue exitoso).
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    public static String service(GenericSession genericSession, String key) throws Exception {
        // Recupera la sesión de trabajo
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtiene el ID del curso asociado al profesor
        CursoId id = CommonCursoUtil.getParent(ws.getCurso(), ws.getCargaEspejo());

        // Recupera la lista de otros materiales de reutilización y la almacena en la sesión
        ws.setOtrosTmaterial(getListaOtrosMateriales(id));

        // Registra un log para el curso
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }
}
