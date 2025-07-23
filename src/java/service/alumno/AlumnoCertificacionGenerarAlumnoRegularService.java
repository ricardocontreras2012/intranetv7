/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import action.alumno.AlumnoCertificacionGenerarAlumnoRegularAction;
import com.google.gson.Gson;
import domain.model.MatriculaHistoricoId;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.SystemParametersUtil.C1;
import infrastructure.util.common.CommonCertificacionUtil;

/**
 *
 * @author Administrador
 */
public class AlumnoCertificacionGenerarAlumnoRegularService {

    public String service(AlumnoCertificacionGenerarAlumnoRegularAction action, GenericSession genericSession, AlumnoSession as, Integer codTramite, Integer periodo, String obs, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        MatriculaHistoricoId id = ws.getMatriculaList().get(periodo).getId();
        String obsAux = (obs == null) ? "" : obs;

        Integer agnoCert = id.getMathAgno();
        Integer semCert = id.getMathSem();

        Integer monto = CommonCertificacionUtil.getValor(as.getCertList(), C1);
        Integer folio = CommonCertificacionUtil.getFolio();
        String name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, C1);

        Gson gson = new Gson();
        Type typeObject = CommonCertificacionUtil.getTypeToken();
        
        Map<String, String> pMap = new HashMap<>();
        pMap.put("tramite", codTramite.toString());
        pMap.put("agno", agnoCert.toString());
        pMap.put("sem", semCert.toString());
        pMap.put("obs", obsAux);
        pMap.put("glosa", "");
        pMap.put("folio", folio.toString());
        pMap.put("archivo", name);
        pMap.put("genera", genericSession.getRut().toString());
        pMap.put("type", genericSession.getUserType());
        pMap.put("session", key);
        pMap.put("monto", monto.toString());        
        String params = "["+gson.toJson(pMap, typeObject)+"]";
                    
        Map<String, String> cMap = new HashMap<>();
        cMap.put("tcert", C1.toString());
        cMap.put("monto", monto.toString());
        cMap.put("params", params);
        String json = "["+gson.toJson(cMap, typeObject)+"]";
          
        as.setMontoCert(monto);
        as.setCorrelCert(ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).createSolicitudCertificado(ws.getAluCar().getId(), monto, json));
        action.setCorrel(as.getCorrelCert());
                
        return CommonCertificacionUtil.getRetCertificado(monto, obsAux);
    }
}
