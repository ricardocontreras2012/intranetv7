/*
 * @(#)MensajePersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MensajeRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Mensaje;
import static java.lang.String.valueOf;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Projections.rowCount;
import static org.hibernate.criterion.Restrictions.disjunction;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ilike;
import static org.hibernate.criterion.Restrictions.ne;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MensajePersistenceImpl extends CrudAbstractDAO<Mensaje, Long> implements MensajeRepository {

    @Override
    public void saveMsg(Mensaje msg) {        
        String sqlText = "insert into mensaje( "
                + "msg_correl,msg_rut_env,msg_rol_env,msg_fecha,msg_subject,msg_msg,msg_para,"
                + "msg_nombre_env,msg_estado,msg_id_session,msg_attach,msg_imagen,msg_url,msg_tipo) values("
                + msg.getMsgCorrel() + ","
                + msg.getMsgRutEnv() + ",'"
                + msg.getMsgRolEnv() + "',"
                + "SYSDATE,'"
                + msg.getMsgSubject() + "','"
                + msg.getMsgMsg() + "','"
                + msg.getMsgPara().replace("'", "''") + "','"
                + msg.getMsgNombreEnv() + "','"
                + msg.getMsgEstado() + "','"
                + msg.getMsgIdSession() + "','"
                + msg.getMsgAttach() + "','"
                + msg.getMsgImagen() + "','"
                + msg.getMsgUrl() + "','"
                + msg.getMsgTipo() + "')"; 
  
        Query query = getSession().createSQLQuery(sqlText);

        query.executeUpdate();                
    }

    /**
     * Method description
     *
     * @param key
     * @param correl
     * @return
     */
    @Override
    public Mensaje find(String key, Integer correl) {
        Criteria criteria = getSession().createCriteria(Mensaje.class);

        criteria.add(eq("msgIdSession", key));
        criteria.add(eq("msgCorrel", correl));

        return (Mensaje) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @param start
     * @param length
     * @param searchValue
     * @param tipoOrder
     * @param nombreDataColumnaActual
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Mensaje> find(Integer rut, Integer start, Integer length, String searchValue, String tipoOrder, String nombreDataColumnaActual) {
        Criteria criteria = getSession().createCriteria(Mensaje.class);

        //Limite para solo retornar lo que se muestra por pantalla
        criteria.setFirstResult(start);
        criteria.setMaxResults(length);
        //Se buscan los mensajes no borrados del Rut.
        criteria.add(eq("msgRutEnv", rut));
        criteria.add(ne("msgEstado", "D"));

        //Dependiendo de si hay filtro o no...
        if (!"".equals(searchValue)) {
            criteria.add(disjunction()
                    .add(ilike("msgSubject", "%" + searchValue + "%"))
                    .add(ilike("msgPara", "%" + searchValue + "%"))
                    .add(ilike("msgMsg", "%" + searchValue + "%"))
            );
        }

        String variableAOrdenar;
        if (null == nombreDataColumnaActual) {
            variableAOrdenar = nombreDataColumnaActual;
        } else {
            switch (nombreDataColumnaActual) {
                case "nombreEnvShort":
                    variableAOrdenar = "msgNombreEnv";
                    break;
                case "subjectShort":
                    variableAOrdenar = "msgSubject";
                    break;
                case "formattedFullDate":
                    variableAOrdenar = "msgFecha";
                    break;
                case "paratShort":
                case "para":
                    variableAOrdenar = "msgPara";
                    break;
                default:
                    variableAOrdenar = nombreDataColumnaActual;
                    break;
            }
        }

        if ("asc".equals(tipoOrder)) {
            criteria.addOrder(asc(variableAOrdenar));
        } else {
            criteria.addOrder(desc(variableAOrdenar));
        }

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public int countMsgs(Integer rut) {
        Criteria criteria = getSession().createCriteria(Mensaje.class);

        criteria.add(eq("msgRutEnv", rut));
        criteria.add(ne("msgEstado", "D"));
        return ((Number) criteria.setProjection(rowCount()).uniqueResult()).intValue();
    }

    /**
     * Method description
     *
     * @param rut
     * @param searchValue
     * @return
     */
    @Override
    public int countMsgsFiltered(Integer rut, String searchValue) {
        Integer totalResult;

        if ("".equals(searchValue)) {
            totalResult = countMsgs(rut);
        } else {
            Criteria criteria = getSession().createCriteria(Mensaje.class);

            criteria.add(eq("msgRutEnv", rut));
            criteria.add(ne("msgEstado", "D"));
            criteria.add(disjunction()
                    .add(ilike("msgSubject", "%" + searchValue + "%"))
                    .add(ilike("msgPara", "%" + searchValue + "%"))
                    .add(ilike("msgMsg", "%" + searchValue + "%"))
            );
            totalResult = ((Number) criteria.setProjection(rowCount()).uniqueResult()).intValue();
        }

        return totalResult;
    }

    /**
     * Method description
     *
     * @param mensaje
     */
    @Override
    public void setDeleteSentMessage(Mensaje mensaje) {
        String hql = "update Mensaje set msg_estado='D' where msg_correl = :correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("correl", valueOf(mensaje.getMsgCorrel()), StandardBasicTypes.STRING);
        query.executeUpdate();
    }
}
