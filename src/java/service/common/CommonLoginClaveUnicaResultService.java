/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonProfesorUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ricardo
 */
public class CommonLoginClaveUnicaResultService {
    public static String service(HttpServletRequest request, Map<String, Object> sesion, String key) {

        String token;
        String userType;
        boolean retValue = false;

        try {
            String data = request.getParameter("data");
            JsonObject jobj = new Gson().fromJson(data, JsonObject.class);

            if (jobj.get("success").getAsBoolean()) {

                String data2 = jobj.get("data").toString();
                JsonObject jobj2 = new Gson().fromJson(data2, JsonObject.class);
                token = jobj2.get("token").toString().replace("\"", "");
                String meta = jobj2.get("metadata").toString();
                JsonObject jobj6 = new Gson().fromJson(meta, JsonObject.class);
                userType = jobj6.get("userType").toString().replace("\"", "");

                URL url = new URL("https://accounts.claveunica.gob.cl/openid/userinfo/");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                // Configurar método POST
                con.setRequestMethod("POST");
                con.setRequestProperty("Authorization", "Bearer " + token);
                con.setDoOutput(true);  // Para enviar body, aunque esté vacío

                // Obtener código de respuesta
                int status = con.getResponseCode();

                BufferedReader reader;
                if (status >= 200 && status < 300) {
                    reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

                } else {
                    reader = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
                }

                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                reader.close();

                // Parsear JSON con Gson
                JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
                JsonObject idPer = jsonObject.getAsJsonObject("RolUnico");
                Integer rut = idPer.get("numero").getAsInt();
                
                switch (userType) {
                    case "AL":
                        retValue = CommonAlumnoUtil.login(rut, "", key, sesion, SystemParametersUtil.INGRESO_CLAVE_UNICA);
                        break;
                    case "PR":
                        retValue = CommonProfesorUtil.login(rut, "", key, sesion, SystemParametersUtil.INGRESO_CLAVE_UNICA);
                        break;
                    default:
                    // code block
                }

                LogUtil.setLog(rut);
            }

        } catch (Exception e) {
            e.printStackTrace();
           //NO borrar capta errores en ClaveUnica
        }

        return retValue ? "success" : "error";
    }
}
