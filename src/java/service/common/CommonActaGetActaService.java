/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ActaCalificacionId;
import domain.model.CursoId;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ActaConsultaSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Servicio para obtener la información de un acta de calificación y su curso asociado
 * para un semestre y año específicos.
 *
 * <p>Este servicio recupera los detalles de un acta de calificación, así como
 * los datos del curso relacionado a dicha acta. A través de la clave de sesión
 * proporcionada, se obtiene la información del acta y el curso asociado, 
 * permitiendo su posterior procesamiento o visualización.</p>
 * 
 * <p>Se espera que el servicio devuelva una respuesta de tipo {@link com.opensymphony.xwork2.Action.SUCCESS}
 * si la operación es exitosa. En caso de que ocurra algún error durante la
 * ejecución, se generará una excepción.</p>
 * 
 * @author Administrador
 */
public class CommonActaGetActaService {

    /**
     * Obtiene los datos de un acta de calificación y su curso asociado
     * basándose en la posición del acta en la lista de consultas.
     *
     * <p>Este método obtiene un acta específica de la lista de actas en la
     * sesión de trabajo, y utiliza el identificador del acta para consultar
     * la información del curso asociado a dicho acta. Además, se establece
     * la información de la nómina correspondiente al acta.</p>
     * 
     * @param genericSession La sesión general que contiene los datos del usuario.
     * @param pos La posición en la lista de actas para recuperar el acta correspondiente.
     * @param key La llave para acceder a los datos de la sesión.
     * @return El estado de la acción. Retorna {@link com.opensymphony.xwork2.Action.SUCCESS}
     * si la operación fue exitosa.
     * @throws Exception Si ocurre un error durante la ejecución del servicio, como problemas
     * de acceso a los datos o de ejecución de consultas.
     */
    public String service(GenericSession genericSession, Integer pos, String key)
            throws Exception {

        // Recupera la sesión de trabajo correspondiente utilizando la clave proporcionada
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtiene el acta de calificación en la posición indicada en la lista de actas
        ActaConsultaSupport ac = ws.getActaConsultaSupportList().get(pos);

        // Obtiene el identificador del acta de calificación
        ActaCalificacionId id = ac.getId();
        
        // Crea un nuevo identificador de curso basado en la información del acta
        CursoId cursoId = new CursoId();
        cursoId.setCurAsign(ac.getAcalAsign());
        cursoId.setCurElect(ac.getAcalElect());
        cursoId.setCurCoord(ac.getAcalCoord());
        cursoId.setCurSecc(ac.getAcalSecc());
        cursoId.setCurAgno(ws.getAgnoAct());
        cursoId.setCurSem(ws.getSemAct());
        cursoId.setCurComp("T");

        // Recupera los datos del curso asociado a la acta de calificación utilizando el identificador del curso
        ws.setCurso(ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).find(cursoId));

        // Recupera la nómina asociada al acta de calificación utilizando el identificador del acta
        ws.setNominaActa(ContextUtil.getDAO().getActaNominaViewRepository(ActionUtil.getDBUser())
                .find(id.getAcalFolio(), id.getAcalAgno(), id.getAcalSem()));

        // Retorna el estado de éxito de la operación
        return SUCCESS;
    }    
}
