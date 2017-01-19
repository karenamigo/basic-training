package exercise07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import exercise07.model.Course;
import exercise07.model.Student;
import exercise07.service.CourseService;
import exercise07.service.StudentService;

@Controller
@RequestMapping(
	value = "/course")
@ResponseBody
public class CourseController {

	@Autowired
	CourseService courseService;

	@Autowired
	StudentService studentService;

	@RequestMapping(
		value = "/delete/{courseId}")
	public void delete(@PathVariable Integer courseId) {
		courseService.delete(courseId);
	}

	@RequestMapping(
		value = "/update/{courseId}")
	public void update(@ModelAttribute Course course) {
		courseService.update(course);
	}

	@RequestMapping(
		value = "/add")
	public void add(@ModelAttribute Course course, @PathVariable Integer studentId) {
		Student student = studentService.getById(studentId);
		courseService.add(course);
		student.addCourse(course);
		studentService.update(student);

	}
}
