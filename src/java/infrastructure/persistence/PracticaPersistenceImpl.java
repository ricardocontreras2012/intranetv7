/*
 * @(#)PracticaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.PracticaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Asignatura;
import domain.model.Practica;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class PracticaPersistenceImpl extends CrudAbstractDAO<Practica, Long> implements PracticaPersistence {

    @Override
    public String getHoras(Integer asignatura) {
        Query query = getSession().getNamedQuery("HorasPracticaFunction");

        query.setParameter(0, asignatura, StandardBasicTypes.INTEGER);
        return (String) query.uniqueResult();
    }

    @Override
    public Integer getPracticaxInscribir(AluCar aluCar) {
        Query query = getSession().getNamedQuery("GetPracticaxInscribirFunction");

        query.setParameter(0, aluCar.getId().getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCar.getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCar.getId().getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCar.getId().getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        return (Integer) query.uniqueResult();
    }

    @Override
    public void insert(Practica practica) {
        Query query = getSession().getNamedQuery("InsertarPracticaFunction");

        query.setParameter(0, practica.getId().getPraRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, practica.getId().getPraCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, practica.getId().getPraAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, practica.getId().getPraSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, practica.getId().getPraAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(5, practica.getId().getPraAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(6, practica.getId().getPraSem(), StandardBasicTypes.INTEGER);

        query.setParameter(7, practica.getPraFechaInicio(), StandardBasicTypes.DATE);
        query.setParameter(8, practica.getPraFechaTermino(), StandardBasicTypes.DATE);
        query.setParameter(9, practica.getEmpleador().getEmpRut(), StandardBasicTypes.INTEGER);
        query.setParameter(10, practica.getPraTelefonoEmp(), StandardBasicTypes.STRING);
        query.setParameter(11, practica.getPraLabor(), StandardBasicTypes.STRING);
        query.setParameter(12, practica.getComuna().getComCod(), StandardBasicTypes.INTEGER);
        query.setParameter(13, practica.getPraDireccion(), StandardBasicTypes.STRING);
        query.setParameter(14, practica.getAutoriza().getPerRut(), StandardBasicTypes.INTEGER);
        query.setParameter(15, practica.getPraTelefonoAut(), StandardBasicTypes.STRING);
        query.setParameter(16, practica.getPraCalidadAut(), StandardBasicTypes.STRING);
        query.setParameter(17, practica.getPraEstado(), StandardBasicTypes.STRING);
        query.setParameter(18, practica.getSolicitud().getSolFolio(), StandardBasicTypes.INTEGER);
        query.setParameter(19, practica.getPraEmail(), StandardBasicTypes.STRING);

        query.executeUpdate();

    }

    @Override
    public void resolver(Practica practica) {
        Query query = getSession().getNamedQuery("ResolverPracticaFunction");

        query.setParameter(0, practica.getId().getPraRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, practica.getId().getPraCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, practica.getId().getPraAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, practica.getId().getPraSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, practica.getSolicitud().getSolFolio(), StandardBasicTypes.INTEGER);
        query.setParameter(5, practica.getId().getPraAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(6, practica.getId().getPraAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(7, practica.getId().getPraSem(), StandardBasicTypes.INTEGER);
        query.setParameter(8, practica.getPraEstado(), StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @Override
    public Practica find(Integer solicitud) {
        Criteria criteria = getSession().createCriteria(Practica.class);

        criteria.add(eq("solicitud.solFolio", solicitud));
        criteria.setFetchMode("asignatura", JOIN);
        criteria.setFetchMode("comuna.region", JOIN);
        criteria.setFetchMode("comuna", JOIN);
        criteria.setFetchMode("empleador", JOIN);
        criteria.setFetchMode("autoriza", JOIN);
        criteria.setFetchMode("solicitud", JOIN);

        return (Practica) criteria.uniqueResult();

    }

    /**
     * Method description
     *
     * @param asign
     * @param agno
     * @param sem
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Practica> getNominaxCalificar(Integer asign, Integer agno, Integer sem) {

        Criteria criteria = getSession().createCriteria(Practica.class);

        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("asignatura", "asignatura");
        criteria.add(eq("asignatura.asiCod", asign));
        criteria.add(eq("id.praAgno", agno));
        criteria.add(eq("id.praSem", sem));
        criteria.add(sqlRestriction("pra_estado='A'"));
        criteria.add(sqlRestriction("not exists (select * from calificacion where cal_rut=pra_rut and cal_cod_car = pra_cod_car and cal_agno_ing = pra_agno_ing and cal_sem_ing = pra_sem_ing and cal_agno = pra_agno and cal_sem=pra_sem and cal_asign = pra_asign)"));
        criteria.add(sqlRestriction("not exists (select * from acta_practica, acta_practica_nomina where apra_folio = apran_folio and pra_asign = apra_asign and pra_agno = apra_agno and pra_sem=apra_sem and apran_rut=pra_rut and apran_cod_car = apran_cod_car and apran_agno_ing = pra_agno_ing and apran_sem_ing = pra_sem_ing)"));
        criteria.add(sqlRestriction("exists (select * from matricula_historico where math_rut=pra_rut and math_cod_car = pra_cod_car and math_agno_ing = pra_agno_ing and math_sem_ing = pra_sem_ing and math_agno = pra_agno and math_sem=pra_sem)"));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Practica> getNominaxRectificar(Integer asign, Integer agno, Integer sem) {

        Criteria criteria = getSession().createCriteria(Practica.class);

        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("asignatura", "asignatura");
        criteria.add(eq("asignatura.asiCod", asign));
        criteria.add(eq("id.praAgno", agno));
        criteria.add(eq("id.praSem", sem));
        criteria.add(sqlRestriction("pra_estado='A'"));
        criteria.add(sqlRestriction("exists (select * from calificacion where cal_rut=pra_rut and cal_cod_car = pra_cod_car and cal_agno_ing = pra_agno_ing and cal_sem_ing = pra_sem_ing and cal_agno = pra_agno and cal_sem=pra_sem and cal_asign = pra_asign)"));
        criteria.add(sqlRestriction("exists (select * from acta_practica, acta_practica_nomina where apra_folio = apran_folio and pra_asign = apra_asign and pra_agno = apra_agno and pra_sem=apra_sem and apran_rut=pra_rut and apran_cod_car = apran_cod_car and apran_agno_ing = pra_agno_ing and apran_sem_ing = pra_sem_ing)"));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Asignatura> getPracticasCoordinador(Integer rut) {
        // Definimos la consulta SQL con parámetros en lugar de concatenar valores
        String strQuery = "SELECT * from asignatura where exists ( "
                + "select 1 FROM labor_realizada l "
                + "JOIN unidad u1 ON u1.uni_cod = l.lrea_unidad "
                + "JOIN unidad u2 ON u1.uni_acad_mayor = u2.uni_cod "
                + "JOIN mencion m ON m.men_unidad = u2.uni_cod "
                + "JOIN malla ma ON ma.ma_cod_car = m.men_cod_car "
                + "WHERE l.lrea_labor = 67 "
                + "AND asi_cod = ma.ma_asign "
                + "AND asi_flag_practica = 'S' "
                + "AND l.lrea_rut = :rut)";

        // Creamos la consulta SQL utilizando Hibernate y le asignamos el parámetro
        SQLQuery query = getSession().createSQLQuery(strQuery).addEntity(Asignatura.class);
        query.setParameter("rut", rut);  // Asignamos el parámetro de forma segura

        // Ejecutamos la consulta y devolvemos el resultado
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Asignatura> getPracticasSecretaria(Integer rut) {
        String strQuery = "select distinct asignatura.*,  secm_rut from secretaria_mencion,mencion, malla, asignatura where men_cod_car = secm_cod_car and "
                + "men_cod_men = secm_cod_men and "
                + "ma_cod_car = men_cod_car and "
                + "ma_cod_men = men_cod_men and "
                + "ma_asign = asi_cod and "
                + "asi_flag_practica='S' and "
                + "secm_rut =" + rut;

        SQLQuery query = getSession().createSQLQuery(strQuery).addEntity("asignatura", Asignatura.class);

        return query.list();
    }

    @Override
    public void crearActa(Integer folio, Integer practica, Integer agno, Integer sem, Integer porc_emp, Integer porc_coord, String tipo) {
        String sql = "insert into acta_practica (apra_folio,apra_asign,apra_agno,apra_sem,apra_porc_emp,apra_porc_coord,apra_estado, apra_tipo) VALUES("
                + folio + "," + practica + "," + agno + "," + sem + "," + porc_emp + "," + porc_coord + ",'E','" + tipo + "')";

        Query query = getSession().createSQLQuery(sql);

        query.executeUpdate();
    }

    @Override
    public void agregarNomina(AluCarId id, Integer folio, BigDecimal nota_emp, BigDecimal nota_coord) {
        String sql = "insert into acta_practica_nomina (apran_folio,apran_rut,apran_cod_car,apran_agno_ing,apran_sem_ing,apran_nota_emp,apran_nota_coord) VALUES("
                + folio + "," + id.getAcaRut() + "," + id.getAcaCodCar() + "," + id.getAcaAgnoIng() + "," + id.getAcaSemIng() + "," + nota_emp + "," + nota_coord + ")";

        Query query = getSession().createSQLQuery(sql);

        query.executeUpdate();
    }
}
