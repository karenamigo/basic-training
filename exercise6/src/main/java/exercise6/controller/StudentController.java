package exercise6.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import exercise6.model.Course;
import exercise6.model.Student;
import exercise6.service.StudentService;

@Controller
@RequestMapping(
	value = "/student")
@ResponseBody //為了要使content-type = application/json
public class StudentController {

	@Autowired
	StudentService studentService;

	@ResponseBody
	@RequestMapping(
		value = "/loadStudents")
	public List<Student> loadStudents() {
		return studentService.list();
	}

	@RequestMapping(
		value = "/delete/{studentId}")
	public void delete(@PathVariable Integer studentId) {
		studentService.delete(studentId);
	}

	@RequestMapping(
		value = "/update/{studentId}")
	public void update(@ModelAttribute Student student) {
		studentService.update(student);
	}

	@RequestMapping(
		value = "/detail/{studentId}")
	@ResponseBody
	public Set<Course> detail(@PathVariable int studentId) {
		Student student = studentService.getById(studentId);
		Set<Course> course = student.getCourses();
		return course;
	}

	@RequestMapping(
		value = "/add")
	public void add(@ModelAttribute Student student) {
		if (student.getId() == null)
			studentService.add(student);
	}

}
