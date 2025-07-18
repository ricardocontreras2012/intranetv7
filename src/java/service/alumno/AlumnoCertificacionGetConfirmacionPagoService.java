/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import action.alumno.AlumnoCertificacionGetConfirmacionPagoAction;
import com.google.gson.Gson;
import java.util.Map;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class AlumnoCertificacionGetConfirmacionPagoService {

    public String service(AlumnoCertificacionGetConfirmacionPagoAction action, String transaction, Integer amount, String payment_method, String isApproved) {
        String vRet = "error";
        String obs;
        String estado;
        Integer correl;
        Integer ord;              
        Integer idTran = Integer.valueOf(transaction);

        if ("true".equals(isApproved)) {
            //Por ahora es un certificado x solicitud, luego tiene que ser una lista

            vRet = "retry";
            Object[] obj = ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).getSolicitudCertificado(idTran);

            if (obj != null) {
                estado = "PA";
                correl = ((Number) obj[0]).intValue();
                action.setCorrel(correl);
                ord = ((Number) obj[1]).intValue();
                vRet = (String) obj[2];
                String params = (String) obj[3];
                params = params.substring(1, params.length() - 1);

                Gson gson = new Gson();
                @SuppressWarnings("unchecked")
                Map<String, String> mapParams = gson.fromJson(params, Map.class);
                obs = mapParams.get("obs");
                if (obs != null && !"".equals(obs)) {
                    estado = "GL";
                    vRet = "glosa";
                }

                ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).setEstadoPago(correl, "true");
                ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).setEstadoCarrito(correl, ord, estado);
            }
        }
        
        return vRet;
    }
}
