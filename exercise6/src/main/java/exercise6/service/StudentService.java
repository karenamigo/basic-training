package exercise6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exercise6.dao.GenericDao;
import exercise6.model.Student;

@Service("StudentService")
public class StudentService {

	@Autowired

	private GenericDao stdDao;

	@Transactional
	public Student getById(Integer id) {
		return (Student) stdDao.getById(id, Student.class);
	}

	@Transactional
	public void add(Student student) {
		stdDao.add(student);
	}

	@Transactional
	public void update(Student student) {
		stdDao.update(student);

	}

	@Transactional
	public void delete(Student user) {
		stdDao.delete(user);
	}

	@Transactional
	public void delete(Integer id) {
		stdDao.delete(stdDao.getById(id, Student.class));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Student> list() {
		return (List<Student>) stdDao.list(Student.class);
	}

}
