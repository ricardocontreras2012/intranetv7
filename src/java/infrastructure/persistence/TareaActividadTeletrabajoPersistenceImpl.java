/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.TareaActividadTeletrabajoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.TareaActividadTeletrabajo;
import domain.model.TareaActividadTeletrabajoId;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Javier
 */
public class TareaActividadTeletrabajoPersistenceImpl extends CrudAbstractDAO<TareaActividadTeletrabajo, Long> implements TareaActividadTeletrabajoPersistence {
    /**
     * Method description
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @return
     */
    @Override
    public TareaActividadTeletrabajo find(Integer rut, Date fecha, Integer tarea) {
        Criteria criteria = getSession().createCriteria(TareaActividadTeletrabajo.class);
        criteria.add(eq("id.tatelRut", rut));
        criteria.add(eq("id.tatelFecha", fecha));
        criteria.add(eq("id.tatelTarea", tarea));

        return (TareaActividadTeletrabajo) criteria.uniqueResult();
    }
    
    /**
     * Metodo que busca todos los tareas que tiene una actividad en especifico 
     * a partir de las dos primeras variables del ID compuesto del mismo Tarea.
     *
     * @param rut
     * @param fecha
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TareaActividadTeletrabajo> findByActividad(Integer rut, Date fecha){
        Criteria criteria = getSession().createCriteria(TareaActividadTeletrabajo.class);
        criteria.add(eq("id.tatelRut", rut));
        criteria.add(eq("id.tatelFecha", fecha));
        
        return criteria.list();
    }    
    
    @SuppressWarnings("unchecked")
    @Override
    public List<TareaActividadTeletrabajo> find(Integer rut, Date fecha){
        Criteria criteria = getSession().createCriteria(TareaActividadTeletrabajo.class);
        criteria.add(eq("id.tatelRut", rut));
        criteria.add(eq("id.tatelFecha", fecha));
        criteria.addOrder(Order.desc("id.tatelTarea"));
        
        return criteria.list();
    }
    
    
    @Override
    public void insertTarea(Integer rut, Date fecha, String des) {
        String hql = "insert into tarea_actividad_teletrabajo (tatel_rut, tatel_fecha, tatel_tarea, tatel_evidencia, tatel_des) VALUES "
                + "(:rut,:fecha, seq_tarea.nextval, :evidencia, :des)";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("fecha", fecha, StandardBasicTypes.DATE);
        query.setParameter("evidencia", "N", StandardBasicTypes.STRING);
        query.setParameter("des", des, StandardBasicTypes.STRING);
        
        query.executeUpdate();
    }
    
    @Override
    public void deleteTarea(TareaActividadTeletrabajoId id) {

        String hqlDelete = "delete from TareaActividadTeletrabajo where tatel_rut=:rut AND tatel_fecha=:fecha AND tatel_tarea= :tarea";

        Query query = getSession().createQuery(hqlDelete);
        query.setParameter("rut", id.getTatelRut(), StandardBasicTypes.INTEGER);
        query.setParameter("fecha", id.getTatelFecha(), StandardBasicTypes.DATE);
        query.setParameter("tarea", id.getTatelTarea(), StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
}
