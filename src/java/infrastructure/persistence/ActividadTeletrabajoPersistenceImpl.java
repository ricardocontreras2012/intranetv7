/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ActividadTeletrabajoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import java.util.Date;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.ActividadTeletrabajo;
import domain.model.ActividadTeletrabajoId;
import java.util.List;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.ge;
import static org.hibernate.criterion.Restrictions.le;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Javier
 */
public final class ActividadTeletrabajoPersistenceImpl extends CrudAbstractDAO<ActividadTeletrabajo, Long> implements ActividadTeletrabajoPersistence {

    /**
     * Method description
     *
     * @param rut
     * @param fecha
     * @return
     */
    @Override
    public ActividadTeletrabajo find(Integer rut, Date fecha) {
        Criteria criteria = getSession().createCriteria(ActividadTeletrabajo.class);
        criteria.add(eq("id.atelRut", rut));
        criteria.add(eq("id.atelFecha", fecha));

        return (ActividadTeletrabajo) criteria.uniqueResult();
    }

    

    /**
     * Method description
     *
     * @param rut
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActividadTeletrabajo> findActividadesBetweenDates(Integer rut, Date fechaInicio, Date fechaFinal) {
        Criteria criteria = getSession().createCriteria(ActividadTeletrabajo.class);
        criteria.add(eq("id.atelRut", rut));
        criteria.add(ge("id.atelFecha", fechaInicio));
        criteria.add(le("id.atelFecha", fechaFinal));

        return criteria.list();
    }
    
    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActividadTeletrabajo> findAll()
    {
        Criteria criteria = getSession().createCriteria(ActividadTeletrabajo.class);
        criteria.addOrder(desc("id.atelFecha"));
        
        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rutJefe
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActividadTeletrabajo> find(Integer rutJefe) {
        Criteria criteria = getSession().createCriteria(ActividadTeletrabajo.class);
        criteria.createAlias("funcionarioTeletrabajo", "funcionarioTeletrabajo");
        criteria.createAlias("funcionarioTeletrabajo.funcionario", "funcionario");
        criteria.add(eq("funcionarioTeletrabajo.ftelRutJefe", rutJefe));
        criteria.addOrder(desc("id.atelFecha"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ActividadTeletrabajo> find(Integer rutJefe, Integer rutFun) {
        Criteria criteria = getSession().createCriteria(ActividadTeletrabajo.class);
        criteria.createAlias("funcionarioTeletrabajo", "funcionarioTeletrabajo");
        criteria.createAlias("funcionarioTeletrabajo.funcionario", "funcionario");
        criteria.createAlias("estado", "estado");
        criteria.add(eq("funcionarioTeletrabajo.ftelRutJefe", rutJefe));
        criteria.add(eq("funcionarioTeletrabajo.ftelRut", rutFun));
        criteria.addOrder(desc("id.atelFecha"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ActividadTeletrabajo> findByFun(Integer rutFun) {
        Criteria criteria = getSession().createCriteria(ActividadTeletrabajo.class);
        criteria.createAlias("funcionarioTeletrabajo", "funcionarioTeletrabajo");
        criteria.createAlias("funcionarioTeletrabajo.funcionario", "funcionario");
        criteria.createAlias("estado", "estado");
        criteria.add(eq("funcionarioTeletrabajo.ftelRut", rutFun));
        criteria.addOrder(desc("id.atelFecha"));

        return criteria.list();
    }

    @Override
    public void insertActividad(Integer rut, Date fecha, String des) {
        String hql = "insert into actividad_teletrabajo (atel_rut, atel_fecha, atel_des, atel_estado) VALUES "
                + "(:rut,:fecha,:des,'C')";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("fecha", fecha, StandardBasicTypes.DATE);
        query.setParameter("des", des, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @Override
    public void deleteActividad(ActividadTeletrabajoId id) {

        String hqlDelete = "delete from ActividadTeletrabajo where atel_rut=:rut AND atel_fecha=:fecha ";

        Query query = getSession().createQuery(hqlDelete);
        query.setParameter("rut", id.getAtelRut(), StandardBasicTypes.INTEGER);
        query.setParameter("fecha", id.getAtelFecha(), StandardBasicTypes.DATE);

        query.executeUpdate();
    }

    @Override
    public void updateActividad(ActividadTeletrabajoId id, String estado) {

        String hqlDelete = "update ActividadTeletrabajo set atel_estado=:estado where atel_rut=:rut AND atel_fecha=:fecha ";

        Query query = getSession().createQuery(hqlDelete);
        query.setParameter("rut", id.getAtelRut(), StandardBasicTypes.INTEGER);
        query.setParameter("fecha", id.getAtelFecha(), StandardBasicTypes.DATE);
        query.setParameter("estado", estado, StandardBasicTypes.STRING);
        
        query.executeUpdate();
    }
}
