package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Reincorporacion;
import java.util.List;

/**
 *
 * @author Ricardo Contreras S.
 */
public interface ReincorporacionRepository extends CrudGenericDAO<Reincorporacion, Long> {

    /**
     *
     * @param agno
     * @param sem
     * @param nomina
     * @param tipo
     * @return
     */
    List<Reincorporacion> find(Integer agno, Integer sem, Integer nomina, String tipo);

    /**
     *
     * @param folio
     * @return
     */
    Reincorporacion find(Integer folio);

    /**
     *
     * @param solicitud
     */
    void marcarProcesado(Integer solicitud);

    /**
     *
     * @param agno
     * @param sem
     * @param nomina
     */
    void reincorporaEliminados(Integer agno, Integer sem, Integer nomina);

    /**
     *
     * @param agno
     * @param sem
     * @param nomina
     */
    void reincorporaEliminadosFull(Integer agno, Integer sem, Integer nomina);

    void reincorporaRetiros(Integer agno, Integer sem, Integer nomina);
    void reincorporaProrrogas(Integer agno, Integer sem, Integer nomina);
}
