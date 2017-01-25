package exercise07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import exercise07.model.Course;
import exercise07.model.Student;
import exercise07.service.CourseService;
import exercise07.service.StudentService;

@Controller
@RequestMapping(
	value = "/student")
@ResponseBody //為了要使content-type = application/json
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@ResponseBody
	@RequestMapping(
		value = "/loadStudents")
	public ResponseEntity<List<Student>> loadStudents() {
		List<Student> students = studentService.list();
		if (students == null || students.isEmpty()) {
			return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/delete/{studentId}")
	public ResponseEntity<Void> delete(@PathVariable int studentId) {
		Student std = studentService.getById(studentId);
		if (std == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		studentService.delete(studentId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(
		value = "/update/{studentId}")
	@ResponseBody
	public ResponseEntity<Student> update(@PathVariable int studentId, @RequestBody Student student) {
		Student std = studentService.getById(studentId);
		if (std == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		studentService.update(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/detail/{studentId}")
	@ResponseBody
	public List<Course> detail(@PathVariable int studentId) {
		Student student = studentService.getById(studentId);
		List<Course> course = student.getCourses();
		return course;
	}

	@RequestMapping(
		value = "/add")
	@ResponseBody
	public ResponseEntity<Void> add(@RequestBody Student student) {
		studentService.add(student);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
