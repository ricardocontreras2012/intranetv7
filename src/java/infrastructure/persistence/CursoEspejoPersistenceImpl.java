/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.CursoEspejoPersistence;
import domain.model.CursoEspejo;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 *
 * @author rcontreras
 */
public class CursoEspejoPersistenceImpl extends CrudAbstractDAO<CursoEspejo, Long> implements CursoEspejoPersistence {

    @Override
    public CursoEspejo getEspejo(CursoId id) {
        Criteria criteria = getSession().createCriteria(CursoEspejo.class);

        criteria.add(eq("id.cesAsign", id.getCurAsign()));
        criteria.add(eq("id.cesElect", id.getCurElect()));
        criteria.add(eq("id.cesCoord", id.getCurCoord()));
        criteria.add(eq("id.cesSecc", id.getCurSecc()));
        criteria.add(eq("id.cesAgno", id.getCurAgno()));
        criteria.add(eq("id.cesSem", id.getCurSem()));

        return (CursoEspejo) criteria.uniqueResult();
    }

    /**
     *
     * @param tcarrera
     * @param especialidad
     * @param jornada
     * @param agno
     * @param sem
     * @param rut
     * @param tipo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoEspejo> find(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String tipo) {
        // Creamos el Criteria para la clase CursoEspejo
        Criteria criteria = getSession().createCriteria(CursoEspejo.class);

        // Establecemos las relaciones y las estrategias de carga para las entidades relacionadas
        criteria.setFetchMode("espejo", FetchMode.JOIN);
        criteria.setFetchMode("transversal", FetchMode.JOIN);

        // Agregamos las restricciones para los valores de agno y sem
        criteria.add(Restrictions.eq("id.cesAgno", agno));
        criteria.add(Restrictions.eq("id.cesSem", sem));

        // Construcción de la subconsulta SQL de manera segura utilizando parámetros
        String sql = "perfil_intranet_pkg.curso_propio_x_tcarrera(ces_asign, ces_elect, ces_coord, ces_secc, ces_agno, ces_sem, ces_comp, ?, ?, ?, ?, ?) > 0";

        // Agregamos el sqlRestriction con los parámetros adecuados
        criteria.add(Restrictions.sqlRestriction(sql,
                new Object[]{rut, tipo, tcarrera, especialidad, jornada}, // Pasamos los parámetros correctamente
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.STRING, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER, StandardBasicTypes.STRING})); // Especificamos los tipos correctos

        // Ejecutamos la consulta y devolvemos los resultados
        return criteria.list();
    }

    @Override
    public void add(Integer asignTr, String electTr, String coordTr, Integer seccTr, Integer agnoTr, Integer semTr,
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {
        Query query = getSession().getNamedQuery("AddCursoEspejoFunction");

        query.setParameter(0, asignTr, StandardBasicTypes.INTEGER);
        query.setParameter(1, electTr, StandardBasicTypes.STRING);
        query.setParameter(2, coordTr, StandardBasicTypes.STRING);
        query.setParameter(3, seccTr, StandardBasicTypes.INTEGER);
        query.setParameter(4, agnoTr, StandardBasicTypes.INTEGER);
        query.setParameter(5, semTr, StandardBasicTypes.INTEGER);
        query.setParameter(6, asign, StandardBasicTypes.INTEGER);
        query.setParameter(7, elect, StandardBasicTypes.STRING);
        query.setParameter(8, coord, StandardBasicTypes.STRING);
        query.setParameter(9, secc, StandardBasicTypes.INTEGER);
        query.setParameter(10, agno, StandardBasicTypes.INTEGER);
        query.setParameter(11, sem, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public void remove(Integer asignTr, String electTr, String coordTr, Integer seccTr, Integer agnoTr, Integer semTr,
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {

        Query query = getSession().getNamedQuery("RemoveCursoEspejoFunction");

        query.setParameter(0, asignTr, StandardBasicTypes.INTEGER);
        query.setParameter(1, electTr, StandardBasicTypes.STRING);
        query.setParameter(2, coordTr, StandardBasicTypes.STRING);
        query.setParameter(3, seccTr, StandardBasicTypes.INTEGER);
        query.setParameter(4, agnoTr, StandardBasicTypes.INTEGER);
        query.setParameter(5, semTr, StandardBasicTypes.INTEGER);
        query.setParameter(6, asign, StandardBasicTypes.INTEGER);
        query.setParameter(7, elect, StandardBasicTypes.STRING);
        query.setParameter(8, coord, StandardBasicTypes.STRING);
        query.setParameter(9, secc, StandardBasicTypes.INTEGER);
        query.setParameter(10, agno, StandardBasicTypes.INTEGER);
        query.setParameter(11, sem, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
}
