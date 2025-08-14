/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.certificacion.alumno;

import action.alumno.AlumnoCertificacionGenerarLogroEnTramiteAction;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import domain.model.CertificacionView;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionGenerarLogroEnTramiteService {

    public String service(AlumnoCertificacionGenerarLogroEnTramiteAction action, GenericSession genericSession, AlumnoSession as, Integer pos, Integer codTramite, String obs,
            String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        CertificacionView cv = as.getCertList().get(pos);

        Integer tCert = as.getTipoCert().getCertTipo();
        String obsAux = (obs == null) ? "" : obs;

        Integer monto = CommonCertificacionUtil.getValor(as.getCertList(), tCert);
        Integer folio = CommonCertificacionUtil.getFolio();
        String name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, tCert);

        Gson gson = new Gson();
        Type typeObject = CommonCertificacionUtil.getTypeToken();

        Map<String, String> pMap = new HashMap<>();
        pMap.put("tcert", tCert.toString());
        pMap.put("desPrint", cv.getCertDesPrint());
        pMap.put("logro", cv.getCertTlogro());
        pMap.put("tramite", codTramite.toString());
        pMap.put("obs", obsAux);
        pMap.put("glosa", "");
        pMap.put("folio", folio.toString());
        pMap.put("archivo", name);
        pMap.put("genera", genericSession.getRut().toString());
        pMap.put("type", genericSession.getUserType());
        pMap.put("session", key);
        pMap.put("monto", monto.toString());
        pMap.put("certCorrel", cv.getCertCorrel().toString());
        String params = "[" + gson.toJson(pMap, typeObject) + "]";

        Map<String, String> cMap = new HashMap<>();
        cMap.put("tcert", tCert.toString());
        cMap.put("monto", monto.toString());
        cMap.put("params", params);
        String json = "[" + gson.toJson(cMap, typeObject) + "]";

        as.setMontoCert(monto);
        as.setCorrelCert(ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).createSolicitudCertificado(ws.getAluCar().getId(), monto, json));
        action.setCorrel(as.getCorrelCert());

        return CommonCertificacionUtil.getRetCertificado(monto, obsAux);
    }
}
