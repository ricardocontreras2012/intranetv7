/*
 * @(#)CommonMaterialDownLoadMaterialService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.MaterialApoyo;
import domain.model.Tmaterial;
import java.io.InputStream;
import java.util.List;
import session.GenericSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonArchivoUtil;
import static infrastructure.util.common.CommonMaterialUtil.getMaterial;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMaterialDownLoadMaterialService {

    public static ActionInputStreamUtil service(GenericSession genericSession, List<Tmaterial> lista, Integer tipo,
            Integer material, String key)
            throws Exception {
        String name;
        InputStream input;
        String description;
        ActionInputStreamUtil ais = null;
           
        MaterialApoyo materialApoyo = getMaterial(lista, tipo, material);
        
        if (!"AL".equals(genericSession.getUserType()) || !"AL".equals(materialApoyo.getMatPerfil())
                || genericSession.getRut().equals(materialApoyo.getMatRutAutor())) { 
            name = materialApoyo.getMatArchivo();
            input = CommonArchivoUtil.getFile(name, "mat");
            description = FormatUtil.getMimeType(name);
            ais = new ActionInputStreamUtil(name, description, input);
        }
        
        LogUtil.setLog(genericSession.getRut(),genericSession.getCurso(key).getNombreCorto() + " > "
                + materialApoyo.getMatArchivo());

        
        return ais;

    }
}
