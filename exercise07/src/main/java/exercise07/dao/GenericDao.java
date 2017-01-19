package exercise07.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement
@Transactional(
	readOnly = false,
	propagation = Propagation.REQUIRED)
@Repository
public class GenericDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public List<?> list(Class clazz) {
		String simpleName = clazz.getSimpleName();
		String hql = "from " + clazz.getName() + " " + simpleName;
		return getSession().createQuery(hql).list();
	}

	@Transactional
	public List<?> listAll(Class clazz) {
		String simpleName = clazz.getSimpleName();
		String hql = "from " + clazz.getName() + " " + simpleName;
		return getSession().createQuery(hql).list();
	}

	@Transactional
	public void add(Object entity) {
		getSession().save(entity);
	}

	@Transactional
	public void update(Object entity) {

		getSession().update(entity);
	}

	@Transactional
	public void delete(Object entity) {
		getSession().delete(entity);
	}

	@Transactional
	public Object getById(Integer id, Class clazz) {
		return getSession().get(clazz, id);
	}

}
