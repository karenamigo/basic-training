package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory SESSIONFACTORY;
	static {
		try {
			SESSIONFACTORY = new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSesstionFactory() {
		return SESSIONFACTORY;
	}

	public static void shutdown() {
		getSesstionFactory().close();
	}

}
