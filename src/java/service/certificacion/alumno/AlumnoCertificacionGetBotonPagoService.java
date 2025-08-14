/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.certificacion.alumno;

import com.google.gson.Gson;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.apache.struts2.ServletActionContext;
import session.AlumnoSession;
import infrastructure.support.PagoSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Administrador
 */
public class AlumnoCertificacionGetBotonPagoService {

    public String service(AlumnoSession as) {

        try {

            URL url = new URL("https://services.usach.cl/api/newTransaction?amount=" + as.getMontoCert() + "&store=Certificados%20en%20linea&url_callback=https://" + ServletActionContext.getRequest().getServerName() + "/intranetv7/AlumnoCertificacionGetConfirmacionPago");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            httpConn.setRequestMethod("POST");

            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setRequestProperty("Authorization", "Bearer VEb9bdiAXGnbSMoXsWKHpt50jFQ7vioDMxTlyyM1");

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();

            Scanner s = new Scanner(responseStream, StandardCharsets.UTF_8.name()).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            response = response.replace("\\/", "/");

            Gson gson = new Gson();
            PagoSupport pago = gson.fromJson(response, PagoSupport.class);

            Integer idTran = FormatUtil.Base64toInt(pago.getTransaction_number());

            ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).createPago(as.getCorrelCert(), idTran);

            return pago.getUrl_payment();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
