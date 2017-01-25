package exercise07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import exercise07.model.Course;
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
		value = "/update/{stdId}/{courseId}")
	public ResponseEntity<Course> update(@PathVariable int courseId, @PathVariable int stdId, @RequestBody Course course) {
		Course stdcourse = courseService.getById(courseId);
		if (stdcourse == null) {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
		course.setStdId(stdId);
		courseService.update(course);
		return new ResponseEntity<Course>(stdcourse, HttpStatus.OK);

	}

	@RequestMapping(
		value = "/add/{stdId}")
	public ResponseEntity<Void> add(@RequestBody Course course, @PathVariable int stdId) {
		course.setStdId(stdId);
		courseService.add(course);
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(
		value = "/delete/{courseId}")
	public ResponseEntity<Void> delete(@PathVariable("courseId") Integer courseId) {
		Course course = courseService.getById(courseId);
		if (course == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		courseService.delete(courseId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
