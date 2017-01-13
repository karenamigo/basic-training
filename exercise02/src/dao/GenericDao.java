package dao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class GenericDao<T> {

	public void insert(T object) {
		Session session = HibernateUtil.getSesstionFactory().openSession(); //開啟session，同開啟JDBC的connection
		try {
			session.beginTransaction();//開始一組會話操作
			session.save(object);//將物件映射至DB_table中儲存
			session.getTransaction().commit(); //需要commit後才會真正更新DB data
		} catch (Exception e) {
			session.getTransaction().rollback(); //若有錯誤則取消所有變更
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
