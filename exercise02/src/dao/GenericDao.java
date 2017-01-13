package dao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class GenericDao<T> {

	public void insert(T object) {
		Session session = HibernateUtil.getSesstionFactory().openSession(); //�}��session�A�P�}��JDBC��connection
		try {
			session.beginTransaction();//�}�l�@�շ|�ܾާ@
			session.save(object);//�N����M�g��DB_table���x�s
			session.getTransaction().commit(); //�ݭncommit��~�|�u����sDB data
		} catch (Exception e) {
			session.getTransaction().rollback(); //�Y�����~�h�����Ҧ��ܧ�
		} finally {
			session.close();
		}
	}

	public void update(T object) {
		Session session = HibernateUtil.getSesstionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {

			session.close();
		}

	}

	public void delete(T object) {
		Session session = HibernateUtil.getSesstionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {

			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll(String Hql) {
		Session session = HibernateUtil.getSesstionFactory().openSession();
		try {
			return session.createQuery(Hql).getResultList();
		} finally {
			session.close();
		}

	}

	public T find(Class<? extends T> c, int id) {
		Session session = HibernateUtil.getSesstionFactory().openSession();
		try {
			return (T) session.get(c, id);
		} finally {

			session.close();
		}
	}

}
