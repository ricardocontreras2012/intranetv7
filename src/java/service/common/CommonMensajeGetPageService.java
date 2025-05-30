/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import session.GenericSession;

/**
 *
 * @author ricardo
 */
public class CommonMensajeGetPageService {

    /**
     *
     * @param genericSession
     * @param url
     * @param key
     * @return
     */
    public static InputStream service(GenericSession genericSession, String url, String key) {
        try {
            URL page = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(page.openStream(), "UTF-8"));

            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            byte[] bytes = stringBuilder.toString().getBytes(Charset.forName("UTF-8"));
            in.close();
            return new ByteArrayInputStream(bytes);

        } catch (Exception e) {
            e.printStackTrace(); return null;
        }
    }
}
