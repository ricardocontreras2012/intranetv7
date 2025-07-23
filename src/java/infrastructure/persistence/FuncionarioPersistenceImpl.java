/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.FuncionarioRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Funcionario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import infrastructure.util.FormatUtil;

/**
 *
 * @author Ricardo
 */
public class FuncionarioPersistenceImpl extends CrudAbstractDAO<Funcionario, Long> implements FuncionarioRepository {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Funcionario> find(String apellido) {                       
        // Normalizamos el apellido en el servidor antes de usarlo en la consulta
        String normalizedApellido = FormatUtil.toUpper(apellido);

        // Usamos HQL para realizar la consulta de forma más segura y sencilla
        String hql = "FROM Funcionario f WHERE normaliza_string(upper(f.funPaterno)) LIKE normaliza_string('%'||:apellido||'%') "
                + "ORDER BY normaliza_string(upper(f.funPaterno)), normaliza_string(upper(f.funMaterno)), f.funNombre";

        // Creamos la consulta y pasamos los parámetros
        Query query = getSession().createQuery(hql);
        query.setParameter("apellido", normalizedApellido, StandardBasicTypes.STRING);

        // Ejecutamos y retornamos la lista de resultados
        return query.list();
    }

    @Override
    public Funcionario find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Funcionario.class);
        criteria.add(eq("funRut", rut));

        return (Funcionario) criteria.uniqueResult();
    }

    @Override
    public void creaFuncionario(Integer rut) {
        Query query = getSession().getNamedQuery("CreaFuncionarioFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public void modify(Integer rut, String direccion) {  
        String sql = "update funcionario set fun_direccion=:direccion where fun_rut=:rut";
        Query query = getSession().createSQLQuery(sql);

        query.setParameter("direccion", direccion, StandardBasicTypes.STRING);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
}
