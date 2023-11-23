package db;

import model.CheckAreaBean;
import org.hibernate.Session;

import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class CheckAreaDAOImpl implements CheckAreaDAO{
    @Override
    public void addNewResult(CheckAreaBean result) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getFactory().openSession();
            session.beginTransaction();
            session.persist(result);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    @Override
    public Collection<CheckAreaBean> getAllResults() throws SQLException {
        Session session = null;
        List<CheckAreaBean> results;
        try {
            session = HibernateUtils.getFactory().openSession();
            var criteriaQuery = session.getCriteriaBuilder().createQuery(CheckAreaBean.class);
            Root<CheckAreaBean> root = criteriaQuery.from(CheckAreaBean.class);
            results = session.createQuery(criteriaQuery.select(root)).getResultList();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return results;
    }

    public final String TABLE_NAME = "results";
    @Override
    public void clearResults() throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getFactory().openSession();
            session.beginTransaction();
            String sql = "delete from " + TABLE_NAME;
            session.createNativeQuery(sql, CheckAreaBean.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
