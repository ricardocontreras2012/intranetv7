/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ConvenioPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Convenio;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo
 */
public class ConvenioPersistenceImpl extends CrudAbstractDAO<Convenio, Long> implements ConvenioPersistence {

    @Override
    public Convenio find(Integer id) {
        Criteria criteria = getSession().createCriteria(Convenio.class);

        criteria.setFetchMode("unidad", JOIN);
        criteria.setFetchMode("proyecto", JOIN);
        criteria.setFetchMode("funcionario", JOIN);
        criteria.setFetchMode("firma", JOIN);
        criteria.createCriteria("curso", LEFT_OUTER_JOIN);
        criteria.add(eq("convNro", id));

        return (Convenio) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override    
    public List<Convenio> getMisConvenios(Integer rut) {            
        // Usamos HQL para mejorar la seguridad y la eficiencia
        String hql = "FROM Convenio c " +
                 "JOIN FETCH c.funcionario f " +  // Traemos la relación Funcionario
                 "JOIN FETCH c.proyecto p " +     // Traemos la relación Proyecto
                 "JOIN FETCH p.unidad u " +       // Traemos la relación Unidad a través de Proyecto
                 "WHERE convenio_pkg.proyecto_propio(p.proyCod, :rut) > 0 " +  // Condición usando proyCod
                 "ORDER BY c.convNro DESC";  // Ordenamos por convNro

        // Creamos la consulta utilizando parámetros para evitar inyecciones SQL
        Query query = getSession().createQuery(hql);
        query.setParameter("rut", rut);  // Parámetro seguro

        // Ejecutamos la consulta y devolvemos los resultados
        return query.list();      
    }

    @Override
    public void delConvenio(Integer convenio) {
        String hql = "delete from Convenio where conv_nro = :convenio";

        Query query = getSession().createQuery(hql);

        query.setParameter("convenio", convenio, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    @Override
    public void insert(Integer folio, Integer rut, String proyecto, Integer rutFirma, String fecha,
            String fechaInicio, String fechaTermino, String tipo,
            String funcion, Integer monto, String tipoMonto, String obsPago) {
        String hql = "insert into convenio(conv_nro, conv_tipo,conv_rut,conv_fecha,"
                + "conv_fecha_ini, conv_fecha_ter, conv_funcion, conv_proy,"
                + "conv_monto,conv_tipo_monto, conv_obs_pago, conv_rut_firma) values("
                + ":folio,:tipo,:rut,to_date(:fecha,'dd-mm-yyyy'),"
                + "to_date(:inicio,'dd-mm-yyyy'),to_date(:termino,'dd-mm-yyyy'),:funcion,"
                + ":proyecto,:monto,:tipoMonto,:obsPago,:rutFirma)";

        Query query = getSession().createSQLQuery(hql);

        query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
        query.setParameter("tipo", tipo, StandardBasicTypes.STRING);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("rutFirma", rutFirma, StandardBasicTypes.INTEGER);
        query.setParameter("fecha", fecha, StandardBasicTypes.STRING);
        query.setParameter("inicio", fechaInicio, StandardBasicTypes.STRING);
        query.setParameter("termino", fechaTermino, StandardBasicTypes.STRING);
        query.setParameter("funcion", funcion, StandardBasicTypes.STRING);
        query.setParameter("proyecto", proyecto, StandardBasicTypes.STRING);
        query.setParameter("monto", monto, StandardBasicTypes.INTEGER);
        query.setParameter("tipoMonto", tipoMonto, StandardBasicTypes.STRING);
        query.setParameter("obsPago", obsPago, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    @Override
    public void putHorario(Integer folio, String dia, String inicio, String termino) {
        String hql = "insert into horario_convenio(hc_nro_conv,hc_dia,hc_hora_inicio,hc_hora_termino) "
                + "select :folio,dia_correl,:inicio,:termino from dia where dia_cod= :dia";

        Query query = getSession().createSQLQuery(hql);

        query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
        query.setParameter("dia", dia, StandardBasicTypes.STRING);
        query.setParameter("inicio", inicio, StandardBasicTypes.STRING);
        query.setParameter("termino", termino, StandardBasicTypes.STRING);

        query.executeUpdate();
    }
}
