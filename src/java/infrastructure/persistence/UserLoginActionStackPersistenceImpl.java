/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.UserLoginActionStackRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.UserLoginActionStack;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author Ricardo
 */
public class UserLoginActionStackPersistenceImpl extends CrudAbstractDAO<UserLoginActionStack, Long>
        implements UserLoginActionStackRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserLoginActionStack> find(String user) {
        Criteria criteria = getSession().createCriteria(UserLoginActionStack.class);

        criteria.add(eq("id.ulasUser", user));
        criteria.addOrder(asc("id.ulasCorrel"));

        return  criteria.list();
    }
}
