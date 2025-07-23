/*
 * @(#)MensajeDestinatarioPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MensajeDestinatarioRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.MensajeDestinatario;
import static java.lang.String.valueOf;
import static java.util.Collections.singleton;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
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
public final class MensajeDestinatarioPersistenceImpl extends CrudAbstractDAO<MensajeDestinatario, Long>
        implements MensajeDestinatarioRepository {

    @Override
    public void saveDest(Integer correl, Integer rut) {
        String sqlText = "insert into mensaje_destinatario("
                + "msgd_correl,msgd_rut_dest,msgd_estado) values("
                + correl + "," + rut + ",'S')";

        Query query = getSession().createSQLQuery(sqlText);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Long countMsgs(Integer rut) {
        return (Long) getSession().createCriteria(MensajeDestinatario.class).add(eq("msgdEstado",
                "S")).add(eq("id.msgdRutDest", rut)).setProjection(rowCount()).uniqueResult();
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
    public List<MensajeDestinatario> findReceivedWithLimits(Integer rut, Integer start, Integer length, String searchValue, String tipoOrder, String nombreDataColumnaActual) {
        Criteria criteria = getSession().createCriteria(MensajeDestinatario.class);

        //Limite para solo retornar lo que se muestra por pantalla
        criteria.setFirstResult(start);
        criteria.setMaxResults(length);

        //Se buscan los mensajes no borrados del Rut.
        criteria.setFetchMode("mensaje", JOIN);
        criteria.createAlias("mensaje", "mensaje");
        criteria.add(eq("id.msgdRutDest", rut));
        criteria.add(ne("msgdEstado", "D"));

        if (!"".equals(searchValue)) {
            criteria.add(disjunction()
                    .add(ilike("mensaje.msgSubject", "%" + searchValue + "%"))
                    .add(ilike("mensaje.msgNombreEnv", "%" + searchValue + "%"))
                    .add(ilike("mensaje.msgMsg", "%" + searchValue + "%"))
            );
        }

        String variableAOrdenar;
        if (null == nombreDataColumnaActual) {
            variableAOrdenar = nombreDataColumnaActual;
        } else {
            switch (nombreDataColumnaActual) {
                case "mensaje.nombreEnvShort":
                    variableAOrdenar = "mensaje.msgNombreEnv";
                    break;
                case "mensaje.subjectShort":
                    variableAOrdenar = "mensaje.msgSubject";
                    break;
                case "mensaje.formattedFullDate":
                    variableAOrdenar = "mensaje.msgFecha";
                    break;
                case "mensaje.paratShort":
                case "mensaje.para":
                    variableAOrdenar = "mensaje.msgPara";
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
    public int countMsgsReceived(Integer rut) {
        Criteria criteria = getSession().createCriteria(MensajeDestinatario.class);

        criteria.add(ne("msgdEstado", "D"));
        criteria.add(eq("id.msgdRutDest", rut));
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
    public int countMsgsReceivedFiltered(Integer rut, String searchValue) {
        Integer totalResult;

        if ("".equals(searchValue)) {
            totalResult = countMsgsReceived(rut);
        } else {
            Criteria criteria = getSession().createCriteria(MensajeDestinatario.class);

            criteria.setFetchMode("mensaje", JOIN);
            criteria.createAlias("mensaje", "mensaje");
            criteria.add(eq("id.msgdRutDest", rut));
            criteria.add(ne("msgdEstado", "D"));
            criteria.add(disjunction()
                    .add(ilike("mensaje.msgSubject", "%" + searchValue + "%"))
                    .add(ilike("mensaje.msgNombreEnv", "%" + searchValue + "%"))
                    .add(ilike("mensaje.msgMsg", "%" + searchValue + "%"))
            );
            totalResult = ((Number) criteria.setProjection(rowCount()).uniqueResult()).intValue();
        }

        return totalResult;
    }

    /**
     * Method description
     *
     * @param mensajeDestinatario
     */
    @Override
    public void setReadMessage(MensajeDestinatario mensajeDestinatario) {
        String hql = "update MensajeDestinatario set msgd_estado='M' where msgd_correl = :correl AND msgd_rut_dest= "
                + ":rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("correl", valueOf(mensajeDestinatario.getId().getMsgdCorrel()), StandardBasicTypes.STRING);
        query.setParameter("rut", valueOf(mensajeDestinatario.getId().getMsgdRutDest()), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param mensajeDestinatario
     */
    @Override
    public void setDeleteReceivedMessage(MensajeDestinatario mensajeDestinatario) {
        String hql = "update MensajeDestinatario set msgd_estado='D' where msgd_correl = :correl AND msgd_rut_dest= "
                + ":rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("correl", valueOf(mensajeDestinatario.getId().getMsgdCorrel()), StandardBasicTypes.STRING);
        query.setParameter("rut", valueOf(mensajeDestinatario.getId().getMsgdRutDest()), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param correl
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> findSent(Integer correl) {
        Query query = getSession().getNamedQuery("EmailDestinatariosFunction");

        query.setParameter(0, correl, StandardBasicTypes.INTEGER);
        List<String> nomina = query.list();

        nomina.removeAll(singleton(null));
        return nomina;
    }
}
