/*
 * @(#)HibernatePropertiesUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util;

import java.util.Properties;
import static infrastructure.util.AppStaticsUtil.HIBERNATE_KEY_CONNECTION_PASSWORD;
import static infrastructure.util.AppStaticsUtil.HIBERNATE_KEY_CONNECTION_URL;
import static infrastructure.util.AppStaticsUtil.HIBERNATE_KEY_CONNECTION_USERNAME;
import static infrastructure.util.PropertyLoaderUtil.loadProperties;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
class HibernatePropertiesUtil {

    private String password;
    private Properties properties;
    private String url;
    private String user;
    private String username;

    HibernatePropertiesUtil(String user) {
        this.user = user;
        properties = loadProperties("config/hibernate/pool" + user + ".xml");

        JasyptDecrypterUtil du = new JasyptDecrypterUtil();

        url = du.decrypt(properties.getProperty(HIBERNATE_KEY_CONNECTION_URL));
        username = du.decrypt(properties.getProperty(HIBERNATE_KEY_CONNECTION_USERNAME));
        password = du.decrypt(properties.getProperty(HIBERNATE_KEY_CONNECTION_PASSWORD));
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method description
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method description
     *
     * @return
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Method description
     *
     * @param properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Method description
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     * Method description
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method description
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
