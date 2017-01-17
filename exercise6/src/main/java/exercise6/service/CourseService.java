package exercise6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exercise6.dao.GenericDao;
import exercise6.model.Course;

@Service("CourseService")
public class CourseService {

	@Autowired
//@Qualifier("CourseService")
	private GenericDao courseDao;

	@Transactional
	public Course getById(Integer id) {
		return (Course) courseDao.getById(id, Course.class);
	}

	@Transactional
	public void add(Course course) {
		courseDao.add(course);
	}

	@Transactional
	public void update(Course course) {
		courseDao.update(course);

	}

	@Transactional
	public void delete(Course course) {
		courseDao.delete(course);
	}

	@Transactional
	public void delete(Integer id) {
		courseDao.delete(courseDao.getById(id, Course.class));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Course> list() {
		return (List<Course>) courseDao.list(Course.class);
	}

}
