package exercise6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exercise6.dao.GenericDao;
import exercise6.model.Course;
import exercise6.model.Student;

@Service("CourseService")
public class CourseService {

	@Autowired
	private GenericDao genericDao;

	@Transactional
	public Course getById(Integer id) {
		return (Course) genericDao.getById(id, Course.class);
	}

	@Transactional
	public void add(Course course) {
		genericDao.add(course);
	}

	@Transactional
	public void update(Course course) {
		genericDao.update(course);

	}

	@Transactional
	public void delete(Course course) {
		genericDao.delete(course);
	}

	@Transactional
	public void delete(Integer id) {
		genericDao.delete(genericDao.getById(id, Course.class));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Course> list() {
		return (List<Course>) genericDao.list(Course.class);
	}

}
