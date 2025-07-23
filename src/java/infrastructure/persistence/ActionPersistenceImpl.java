/*
 * @(#)ActionPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.WebUser;
import domain.model.WebUserAction;
import java.io.Serializable;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.ActionRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ActionPersistenceImpl extends CrudAbstractDAO<Object, Serializable>
        implements ActionRepository {

    @Override
    public WebUserAction find(String user, String action) {
        Criteria criteria = getSession().createCriteria(WebUserAction.class);

        criteria.setFetchMode("webAction", JOIN);

        criteria.add(eq("id.wuaUser", user));
        criteria.add(eq("id.wuaAction", action));
        
        return (WebUserAction) criteria.uniqueResult();
    }

    /**
     *
     * @param action
     */
    @Override
    public void log(String action) {
        String hql = "insert into log_action (log_date, log_message) VALUES "
                + "(Sysdate,:action)";

        Query query = getSession().createSQLQuery(hql);

        query.setParameter("action", action, StandardBasicTypes.STRING);

        query.executeUpdate();
        getSession().close();
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public String getActionLogin(String user)
    {
        Criteria criteria = getSession().createCriteria(WebUser.class);

        criteria.add(eq("wuUser", user));
        return ((WebUser)criteria.uniqueResult()).getWuLogin();
    }

    @Override
    public String setEmail(String user)
    {
        Criteria criteria = getSession().createCriteria(WebUser.class);

        criteria.add(eq("wuUser", user));
        return ((WebUser)criteria.uniqueResult()).getWuEmail();
    }
}
