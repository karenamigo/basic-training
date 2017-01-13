package exercise6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exercise6.dao.GenericDao;
import exercise6.model.Student;

@Service("StudentService")
public class StudentService {

	@Autowired
	private GenericDao genericDao;

	@Transactional
	public Student getById(Integer id) {
		return (Student) genericDao.getById(id, Student.class);
	}

	@Transactional
	public void add(Student student) {
		genericDao.add(student);
	}

	@Transactional
	public void update(Student student) {
		genericDao.update(student);

	}

	@Transactional
	public void delete(Student user) {
		genericDao.delete(user);
	}

	@Transactional
	public void delete(Integer id) {
		genericDao.delete(genericDao.getById(id, Student.class));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Student> list() {
		return (List<Student>) genericDao.list(Student.class);
	}

}
