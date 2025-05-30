/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariaproyectos;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Convenio;
import java.util.List;
import java.util.Map;
import session.GenericSession;
import session.ProyectoSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import java.util.stream.IntStream;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioRemoveConveniosService {

    public static String service(GenericSession genericSession, ProyectoSession ps,
            Map<String, String[]> parameters, String key) {

        List<Convenio> lista = ps.getConvenioList();
        beginTransaction(ActionUtil.getDBUser());

        IntStream.range(0, lista.size())
                .filter(i -> parameters.get("ck_" + i) != null) // Filtra los índices con parámetros no nulos
                .forEach(i -> {
                    Convenio convenio = lista.get(i);
                    ContextUtil.getDAO().getConvenioPersistence(ActionUtil.getDBUser()).delConvenio(convenio.getConvNro());
                    LogUtil.setLog(genericSession.getRut(), convenio.getConvNro());
                });

        commitTransaction();

        return SUCCESS;
    }
}
