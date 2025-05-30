/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ConvalidacionSolicitudAsignPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.Asignatura;
import domain.model.ConvalidacionSolicitudAsign;
import domain.model.ConvalidacionSolicitudAsignId;
import java.math.BigDecimal;
import java.util.List;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import java.util.stream.Collectors;

/**
 *
 * @author rcontreras
 */
public class ConvalidacionSolicitudAsignPersistenceImpl extends CrudAbstractDAO<ConvalidacionSolicitudAsign, Long> implements ConvalidacionSolicitudAsignPersistence {

    @Override
    public List<ConvalidacionSolicitudAsign> getPorConvalidar(AluCar aluCar) {

        List<Object[]> lconvalidacion = ContextUtil.getDAO().getMallaPersistence(ActionUtil.getDBUser()).porConvalidar(aluCar);

        return lconvalidacion.stream()
                .map(mallaObj -> {
                    ConvalidacionSolicitudAsign conv = new ConvalidacionSolicitudAsign();
                    Asignatura asign = new Asignatura();

                    asign.setAsiCod(((Number) mallaObj[0]).intValue());
                    asign.setAsiNom((String) mallaObj[1]);
                    asign.setAsiElect((String) mallaObj[2]);
                    conv.setAsignatura(asign);

                    return conv;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ConvalidacionSolicitudAsign> getPorConvalidar(AluCar aluCar, Integer solicitud) {
        List<Object[]> lconvalidacion = ContextUtil.getDAO().getMallaPersistence(ActionUtil.getDBUser()).porConvalidarSolicitud(aluCar, solicitud);

        return lconvalidacion.stream()
                .map(mallaObj -> {
                    ConvalidacionSolicitudAsign conv = new ConvalidacionSolicitudAsign();
                    ConvalidacionSolicitudAsignId idConv = new ConvalidacionSolicitudAsignId();
                    Asignatura asign = new Asignatura();

                    asign.setAsiCod(((Number) mallaObj[0]).intValue());
                    asign.setAsiNom((String) mallaObj[1]);
                    asign.setAsiElect((String) mallaObj[2]);

                    idConv.setCsaAsign(asign.getAsiCod());
                    idConv.setCsaCorrel(solicitud);

                    conv.setAsignatura(asign);
                    conv.setCsaCursada((String) mallaObj[3]);
                    conv.setCsaElectivo((String) mallaObj[4]);
                    conv.setCsaEstado((String) mallaObj[5]);
                    conv.setCsaNota((BigDecimal) mallaObj[6]);
                    conv.setCsaObs((String) mallaObj[7]);
                    conv.setId(idConv);

                    return conv;
                })
                .collect(Collectors.toList());
    }
}
