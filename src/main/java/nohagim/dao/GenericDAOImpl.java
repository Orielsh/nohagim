package nohagim.dao;

import nohagim.Entities.Entity;
import nohagim.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericDAOImpl<T extends Entity> implements IGenericDAO<T> {

    @Override
    public boolean save(Entity entity) {//todo: if notify user then might throw exception
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();    //todo move to finally block
        } catch (HibernateException e) {
            System.out.println("Save Error"); //todo: logging exception.
        }
        return true; //todo: set void and remove this line. (only for test)
    }

    @Override
    public List<T> getListOfRecords(Class<T> entity) {      //todo: Handle exceptions.
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entity);
        criteria.from(entity);
        return session.createQuery(criteria).getResultList();
    }


}
