/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.EvidenciaTareaActTeletrabajoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.EvidenciaTareaActTeletrabajo;
import domain.model.EvidenciaTareaActTeletrabajoId;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Javier
 */
public class EvidenciaTareaActTeletrabajoPersistenceImpl extends CrudAbstractDAO<EvidenciaTareaActTeletrabajo, Long> implements EvidenciaTareaActTeletrabajoPersistence {
    /**
     * Method description
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @param correl
     * @return
     */
    @Override
    public EvidenciaTareaActTeletrabajo find(Integer rut, Date fecha, Integer tarea, Integer correl) {
        Criteria criteria = getSession().createCriteria(EvidenciaTareaActTeletrabajo.class);
        criteria.add(eq("id.etatelRut", rut));
        criteria.add(eq("id.etatelFecha", fecha));
        criteria.add(eq("id.etatelTarea", tarea));
        criteria.add(eq("id.etatelCorrel", correl));

        return (EvidenciaTareaActTeletrabajo) criteria.uniqueResult();
    }
    
    /**
     * Metodo que encuentra todas las evidencias de un tarea en particular, los
     * cuales son encontrados a partir del las primeras 3 variables del ID 
     * compuesto de la evidencia.
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EvidenciaTareaActTeletrabajo> findByTarea(Integer rut, Date fecha, Integer tarea){
        Criteria criteria = getSession().createCriteria(EvidenciaTareaActTeletrabajo.class);
        criteria.add(eq("id.etatelRut", rut));
        criteria.add(eq("id.etatelFecha", fecha));
        criteria.add(eq("id.etatelTarea", tarea));
        
        criteria.addOrder(asc("id.etatelCorrel"));
        
        return criteria.list();
    }
    
    /**
     * Metodo
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @param des
     * @param file
     */
    @Override
    public void insertEvidencia(Integer rut, Date fecha, Integer tarea, String des, String file) {
        String hql = "insert into evidencia_tar_act_teletrabajo (etatel_rut, etatel_fecha, etatel_tarea, etatel_correl, etatel_des, etatel_file) VALUES "
                + "(:rut, :fecha, :tarea, seq_tarea.nextval, :des, :file)";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("fecha", fecha, StandardBasicTypes.DATE);
        query.setParameter("tarea", tarea, StandardBasicTypes.INTEGER);
        query.setParameter("des", des, StandardBasicTypes.STRING);
        query.setParameter("file", file, StandardBasicTypes.STRING);
        
        query.executeUpdate();
    }
    
    /**
     * Metodo
     *
     * @param id
     */
    @Override
    public void deleteEvidencia(EvidenciaTareaActTeletrabajoId id) {
        Criteria criteria = getSession().createCriteria(EvidenciaTareaActTeletrabajo.class);
        criteria.add(eq("id.etatelRut", id.getEtatelRut()));
        criteria.add(eq("id.etatelFecha", id.getEtatelFecha()));
        criteria.add(eq("id.etatelTarea", id.getEtatelTarea()));
        criteria.add(eq("id.etatelCorrel", id.getEtatelCorrel()));
        
        EvidenciaTareaActTeletrabajo evidenciaPorEliminar = (EvidenciaTareaActTeletrabajo) criteria.uniqueResult();
        getSession().delete(evidenciaPorEliminar);
        
    }
}
