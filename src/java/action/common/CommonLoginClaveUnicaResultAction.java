/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.action.SessionAware;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonProfesorUtil;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Administrador
 */
public class CommonLoginClaveUnicaResultAction implements ServletRequestAware, SessionAware {

    private HttpServletRequest request;
    protected Map<String, Object> sesion;
    private String key;
    private boolean retValue = false;

    public String getValidacion() {
        try {
            String data = request.getParameter("data");
            JsonObject jobj = new Gson().fromJson(data, JsonObject.class);

            if (jobj.get("success").getAsBoolean()) {

                String data2 = jobj.get("data").toString();
                JsonObject jobj2 = new Gson().fromJson(data2, JsonObject.class);

                //// ANTIGUA FORMA
                /*String user = jobj2.get("user").toString();

                JsonObject jobj4 = new Gson().fromJson(user, JsonObject.class);
                String RolUnico = jobj4.get("RolUnico").toString();

                JsonObject jobj5 = new Gson().fromJson(RolUnico, JsonObject.class);
                Integer rut = jobj5.get("numero").getAsInt();
                 */
                
                //// NUEVA FORMA
                
                String token = jobj2.get("token").toString();
                System.out.println("token: " + token);
                
                URL url = new URL("https://accounts.claveunica.gob.cl/openid/userinfo/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // Configurar método POST y cabecera
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "Bearer "+token);
                conn.setDoOutput(true);

                // Leer la respuesta
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder jsonResponse = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    jsonResponse.append(inputLine);
                }
                in.close();

                // Parsear JSON con Gson
                JsonObject jsonObject = JsonParser.parseString(jsonResponse.toString()).getAsJsonObject();
                JsonObject idPer = jsonObject.getAsJsonObject("RolUnico");
                Integer rut = idPer.get("numero").getAsInt();

                System.out.println("Numero: " + rut);
                ///////////// FIN NUEVA FORMA
                                
                String meta = jobj2.get("metadata").toString();
                JsonObject jobj6 = new Gson().fromJson(meta, JsonObject.class);
                String userType = jobj6.get("userType").toString();

                userType = userType.replace("\"", "");

                key = getKeySession();

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
            }
        } catch (Exception e) { //NO borrar capta errores en ClaveUnica
        }

        return retValue ? "success" : "error";
    }

    @Override
    public void withServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Map<String, Object> getSesion() {
        return this.sesion;
    }

    @Override
    public void withSession(Map<String, Object> map) {
        sesion = map;
    }

    public String getKey() {
        return key;
    }
}
