/*
 * @(#)RemoveMaterialesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.material;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.MaterialApoyo;
import java.util.Map;
import domain.repository.MaterialApoyoRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonMaterialUtil.getListaMateriales;
import java.util.stream.IntStream;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RemoveMaterialesService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        MaterialApoyoRepository materialApoyoRepository = ContextUtil.getDAO().getMaterialApoyoRepository(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());
    
        // Iterar sobre la lista de Tmaterial y MaterialApoyo 
        IntStream.range(0, ws.getTmaterial().size()) // Iterar sobre el índice de Tmaterial
                .forEach(i -> IntStream.range(0, ws.getTmaterial().get(i).getMateriales().size()) // Iterar sobre el índice de Materiales
                .filter(j -> parameters.get("ck_" + i + '_' + j) != null) // Filtrar donde hay parámetros correspondientes
                .forEach(j -> {
                    MaterialApoyo materialApoyo = ws.getTmaterial().get(i).getMateriales().get(j);
                    if (materialApoyo.getMatRutAutor().equals(genericSession.getRut())) {
                        LogUtil.setLog(genericSession.getRut(), genericSession.getCurso(key).getNombreCorto()
                                + " > " + materialApoyo.getMatArchivo());
                        materialApoyoRepository.makeTransient(materialApoyo);
                    }
                }));

        commitTransaction();

        ws.setTmaterial(getListaMateriales(genericSession.getCurso(key).getId(),
                genericSession.getRut(), genericSession.getUserType(), genericSession.getUserType(),
                ws.getTipoMaterial()));

        return SUCCESS;
    }
}
