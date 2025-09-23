/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.model.CursoEspejo;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.CursoEspejoRepository;
import java.sql.CallableStatement;
import java.sql.Clob;
import org.hibernate.Session;

/**
 *
 * @author rcontreras
 */
public class CursoEspejoPersistenceImpl extends CrudAbstractDAO<CursoEspejo, Long> implements CursoEspejoRepository {

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
    public String espejosJson(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String tipo) {

        try {
            Session session = getSession();

            // Usar doReturningWork para trabajar con la conexión real (Hikari lo maneja por detrás)
            String result = null;
            result = session.doReturningWork(connection -> {
                String json = null;

                try (CallableStatement stmt = connection.prepareCall("{ ? = call curso_pkg.get_espejos_json(?,?,?,?,?,?,?) }")) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);

                    stmt.setInt(2, tcarrera);
                    stmt.setInt(3, especialidad);
                    stmt.setString(4, jornada);
                    stmt.setInt(5, agno);
                    stmt.setInt(6, sem);
                    stmt.setInt(7, rut);
                    stmt.setString(8, tipo);
                    
                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        json = clob.getSubString(1, (int) clob.length());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    json = "{\"error\": \"" + ex.getMessage() + "\"}";
                }

                return json;
            });
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Retorna una lista vacía en caso de excepción
        }
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
