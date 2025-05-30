/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.DummyPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import domain.model.Dummy;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo
 */
public class DummyPersistenceImpl extends CrudAbstractDAO<Dummy, Long> implements DummyPersistence {

    @Override
    public void setPasswordUser(Integer rut, String user, String password) {

        Query query = getSession().getNamedQuery("SetPasswordFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);
        query.setParameter(1, user, StandardBasicTypes.STRING);
        query.setParameter(2, password, StandardBasicTypes.STRING);

        query.executeUpdate();

    }

    @Override
    public String getRandomPassword(String user) {
        Query query = getSession().getNamedQuery("GetRandPasswordFunction");

        query.setParameter(0, user, StandardBasicTypes.STRING);

        return (String) query.uniqueResult();
    }

    @Override
    public String getEmail(Integer rut) {
        Query query = getSession().getNamedQuery("EmailFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);

        return (String) query.uniqueResult();
    }

    @Override
    public Integer createSolicitudCertificado(AluCarId id, Integer monto, String jsonList) {
        Query query = getSession().getNamedQuery("CreateSolicitudCertificadoFunction");

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, monto, StandardBasicTypes.INTEGER);
        query.setParameter(5, jsonList, StandardBasicTypes.STRING);

        return (Integer) query.uniqueResult();
    }

    @Override
    public void createPago(Integer correl, Integer idTrans) {
        Query query = getSession().getNamedQuery("CreatePagoFunction");

        query.setParameter(0, correl, StandardBasicTypes.INTEGER);
        query.setParameter(1, idTrans, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public Object[] getCertificado(Integer solicitud) {
        String sql = "SELECT scc_solicitud, scc_tcert, scc_params FROM solicitud_certificado_carrito WHERE scc_solicitud = :solicitud";

        // Crear la consulta con parámetros para evitar inyecciones SQL
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("solicitud", solicitud);

        // Ejecutar la consulta y devolver el resultado
        Object result = query.uniqueResult();

        return (Object[]) result; // Aseguramos que el resultado sea un Object[]       
    }

    @Override
    public Object[] getSolicitudCertificado(Integer idTrans) {
        String sql = "SELECT scp_solicitud, scc_ord, tce_des_corta, scc_params "
                + "FROM solicitud_certificado_pago, solicitud_certificado_carrito, tcertificado "
                + "WHERE scp_solicitud = scc_solicitud "
                + "AND tce_cod = scc_tcert "
                + "AND scp_id_tran = :idTrans "
                + "AND scc_estado = 'PP'";

        // Crear la consulta con parámetros para evitar inyecciones SQL
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("idTrans", idTrans);

        // Ejecutar la consulta y devolver el resultado
        Object result = query.uniqueResult();

        return (Object[]) result; // Aseguramos que el resultado sea un Object[]    
    }

    @Override
    public void setEstadoCarrito(Integer correl, Integer ord, String estado) {
        Query query = getSession().getNamedQuery("SetEstadoCarritoFunction");

        query.setParameter(0, correl, StandardBasicTypes.INTEGER);
        query.setParameter(1, ord, StandardBasicTypes.INTEGER);
        query.setParameter(2, estado, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @Override
    public void setEstadoPago(Integer correl, String estado) {
        Query query = getSession().getNamedQuery("SetEstadoPagoFunction");

        query.setParameter(0, correl, StandardBasicTypes.INTEGER);
        query.setParameter(1, estado, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @Override
    public String getRankingEgresado(Integer rut, Integer carrera, Integer agnoIng, Integer semIng) {
        Query query = getSession().getNamedQuery("GetRankingEgresadoFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);
        query.setParameter(1, carrera, StandardBasicTypes.INTEGER);
        query.setParameter(2, agnoIng, StandardBasicTypes.INTEGER);
        query.setParameter(3, semIng, StandardBasicTypes.INTEGER);

        return (String) query.uniqueResult();
    }

    @Override
    public String getRankingEgresadoMencion(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion) {
        Query query = getSession().getNamedQuery("GetRankingEgresadoMencionFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);
        query.setParameter(1, carrera, StandardBasicTypes.INTEGER);
        query.setParameter(2, agnoIng, StandardBasicTypes.INTEGER);
        query.setParameter(3, semIng, StandardBasicTypes.INTEGER);
        query.setParameter(4, mencion, StandardBasicTypes.INTEGER);

        return (String) query.uniqueResult();
    }

    @Override
    public String getRankingRegular(Integer rut, Integer carrera, Integer agnoIng, Integer semIng) {
        Query query = getSession().getNamedQuery("GetRankingRegularFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);
        query.setParameter(1, carrera, StandardBasicTypes.INTEGER);
        query.setParameter(2, agnoIng, StandardBasicTypes.INTEGER);
        query.setParameter(3, semIng, StandardBasicTypes.INTEGER);

        return (String) query.uniqueResult();
    }

    @Override
    public String getRankingRegularMencion(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion) {
        Query query = getSession().getNamedQuery("GetRankingRegularMencionFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);
        query.setParameter(1, carrera, StandardBasicTypes.INTEGER);
        query.setParameter(2, agnoIng, StandardBasicTypes.INTEGER);
        query.setParameter(3, semIng, StandardBasicTypes.INTEGER);
        query.setParameter(4, mencion, StandardBasicTypes.INTEGER);

        return (String) query.uniqueResult();
    }

    @Override
    public void setGlosa(Integer correl, Integer ord, String glosa) {
        Query query = getSession().getNamedQuery("SetGlosaFunction");

        query.setParameter(0, correl, StandardBasicTypes.INTEGER);
        query.setParameter(1, ord, StandardBasicTypes.INTEGER);
        query.setParameter(2, glosa, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getMinors(Integer rut, String type) {
        String sql = "SELECT DISTINCT plal_resol_logro FROM plan_logro WHERE plal_cod_logro=80 AND perfil_intranet_pkg.mencion_propia(plal_cod_car, plal_cod_men, :type,:rut)=1";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("type", type, StandardBasicTypes.STRING);

        return query.list();
    }
}
