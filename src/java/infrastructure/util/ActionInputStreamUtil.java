/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util;

import java.io.InputStream;

/**
 *
 * @author Administrador
 */
public class ActionInputStreamUtil {
    
    private final String name;
    private final String contentType;
    private final InputStream inputStream;
    
    public ActionInputStreamUtil(String name, String contentType, InputStream inputStream) {
        this.name = name;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }
        
    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }       
}
