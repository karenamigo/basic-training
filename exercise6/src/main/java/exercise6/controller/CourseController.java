package exercise6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import exercise6.model.Course;
import exercise6.model.Student;
import exercise6.service.CourseService;
import exercise6.service.StudentService;

@Controller
@RequestMapping(
	value = "/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	StudentService studentService;

	@ResponseBody
	@RequestMapping(
		value = "/loadCourses/{studentId}")
	public List<Course> loadCourses(@PathVariable int studentId) {
		Student student = studentService.getById(studentId);
		List<Course> course = student.getCourses();
		return course;
	}

	@RequestMapping(
		value = "/delete/{courseId}")
	public void delete(@PathVariable Integer courseId) {
		courseService.delete(courseId);
	}

	@RequestMapping(
		value = "/update/{courseId}")
	@ResponseBody
	public void update(@ModelAttribute Course course) {
		courseService.update(course);
	}

	@RequestMapping(
		value = "/detail/{courseId}")
	public Course detail(@PathVariable int courseId) {
		return courseService.getById(courseId);
	}

	@RequestMapping(
		value = "/add")
	public void add(@ModelAttribute Course course) {
		if (course.getId() == 0) {
			courseService.add(course);
		} else {
			courseService.update(course);
		}

	}

}
