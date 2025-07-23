/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ExpedienteNominaRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ExpedienteNomina;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author rcontreras
 */
public class ExpedienteNominaPersistenceImpl extends CrudAbstractDAO<ExpedienteNomina, Long>
        implements ExpedienteNominaRepository {

    @Override
    public void saveNomina(ExpedienteNomina nomina)
    {
        String hql = "insert into Expediente_Nomina (expn_correl,expn_logro,expn_numero,expn_agno,expn_fecha) VALUES "
                + "(:correl,:logro,:numero,:agno,:fecha)";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("correl", nomina.getExpnCorrel(), StandardBasicTypes.INTEGER);
        query.setParameter("logro", nomina.getExpnLogro(), StandardBasicTypes.INTEGER);
        query.setParameter("numero", nomina.getExpnNumero(), StandardBasicTypes.STRING);
        query.setParameter("agno", nomina.getExpnAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("fecha", nomina.getExpnFecha(), StandardBasicTypes.DATE);
        query.executeUpdate();
    }
}
