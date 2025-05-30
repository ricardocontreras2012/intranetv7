/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ParametroMencionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ParametroMencion;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author Usach
 */
public class ParametroMencionPersistenceImpl  extends CrudAbstractDAO<ParametroMencion, Long> implements ParametroMencionPersistence {
    
    @Override
    public ParametroMencion getParameter(Integer carrera, Integer mencion)
    {
        Criteria criteria = getSession().createCriteria(ParametroMencion.class);

        criteria.add(eq("id.pmenCodCar", carrera));
        criteria.add(eq("id.pmenCodMen", mencion));

        return (ParametroMencion) criteria.uniqueResult();
    }
    
    @Override
    public void updateParametroMencion(Integer pmenCodCar, Integer pmenCodMen, 
                                             Integer pmenAgnoAct, Integer pmenSemAct, 
                                             Integer pmenAgnoCal, Integer pmenSemCal, 
                                             Integer pmenAgnoEnc, Integer pmenSemEnc, 
                                             Integer pmenAgnoIns, Integer pmenSemIns, 
                                             Date pmenInsPostInicio, Date pmenInsPostTermino, 
                                             Date pmenInsAdmTermino, Date pmenInsModTermino, 
                                             Date pmenInsEliminTermino, String pmenInsLock) {
        String hql = "UPDATE ParametroMencion SET " +
                         "pmenAgnoAct = :pmenAgnoAct, pmenSemAct = :pmenSemAct, " +
                         "pmenAgnoCal = :pmenAgnoCal, pmenSemCal = :pmenSemCal, " +
                         "pmenAgnoEnc = :pmenAgnoEnc, pmenSemEnc = :pmenSemEnc, " +
                         "pmenAgnoIns = :pmenAgnoIns, pmenSemIns = :pmenSemIns, " +
                         "pmenInsPostInicio = :pmenInsPostInicio, pmenInsPostTermino = :pmenInsPostTermino, " +
                         "pmenInsAdmTermino = :pmenInsAdmTermino, pmenInsModTermino = :pmenInsModTermino, " +
                         "pmenInsEliminTermino = :pmenInsEliminTermino, pmenInsLock = decode(:pmenInsLock,'', NULL,:pmenInsLock)  " +
                         "WHERE id.pmenCodCar = :pmenCodCar AND id.pmenCodMen = :pmenCodMen";

            // Crea la consulta y asigna los parámetros
            Query query = getSession().createQuery(hql);
            query.setParameter("pmenAgnoAct", pmenAgnoAct);
            query.setParameter("pmenSemAct", pmenSemAct);
            query.setParameter("pmenAgnoCal", pmenAgnoCal);
            query.setParameter("pmenSemCal", pmenSemCal);
            query.setParameter("pmenAgnoEnc", pmenAgnoEnc);
            query.setParameter("pmenSemEnc", pmenSemEnc);
            query.setParameter("pmenAgnoIns", pmenAgnoIns);
            query.setParameter("pmenSemIns", pmenSemIns);
            query.setParameter("pmenInsPostInicio", pmenInsPostInicio);
            query.setParameter("pmenInsPostTermino", pmenInsPostTermino);
            query.setParameter("pmenInsAdmTermino", pmenInsAdmTermino);
            query.setParameter("pmenInsModTermino", pmenInsModTermino);
            query.setParameter("pmenInsEliminTermino", pmenInsEliminTermino);
            query.setParameter("pmenInsLock", pmenInsLock);
            query.setParameter("pmenCodCar", pmenCodCar);
            query.setParameter("pmenCodMen", pmenCodMen);

            // Ejecuta la consulta
            query.executeUpdate();
    }    
}
