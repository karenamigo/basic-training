package exercise07.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import exercise07.model.Student;
import exercise07.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	private static final int UNKNOWN_ID = Integer.MAX_VALUE;

	private MockMvc mockMvc; //spring mvc test 入口， 傳遞request並將得到的資料與回預期結果比較

	@Mock  //當呼叫stdService的function時可以得到mock資料
	private StudentService stdService;

	@InjectMocks   //將stdService注入stdController裡面
	private StudentController stdController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this); //對於使用mockitoannotation進行初始化
		mockMvc = MockMvcBuilders.standaloneSetup(stdController).build(); //透過註冊controller來建立一個mockMvc實體
	}

	// =========================================== List All Students ==========================================

	@Test
	public void testListAllsuccess() throws Exception {
		List<Student> students = Arrays.asList(new Student(1, "Karen", "24", "Tainan City", "Computer Science"),
				new Student(2, "Andrea", "31", "Tainan City", "Marketing")); //把students轉換成list
		when(stdService.list()).thenReturn(students); //Configure mock object to return test data when the list() method invoke
		mockMvc.perform(get("/student/loadStudents")).andExpect(status().isOk()); // Invoke an HTTP request and verify the status is 200(Ok)

	}
	// =========================================== Create New Student ========================================

	@Test
	public void testCreateStudentSuccess() throws Exception {
		Student student = new Student(1, "Karen", "24", "Tainan City", "Computer Science");
		doNothing().when(stdService).add(student);
		mockMvc.perform(post("/student/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(student))).andExpect(status().isCreated());
	}

	// =========================================== Update Existing Student ===================================
	@Test
	public void testUpdateStudentSuccess() throws Exception {
		Student student = new Student(1, "Karen", "24", "Tainan City", "Computer Science");
		when(stdService.getById(student.getId())).thenReturn(student);
		doNothing().when(stdService).update(student);
		mockMvc.perform(post("/student/update/{id}", student.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(student)))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateStudentFail404NotFound() throws Exception {
		Student student = new Student(UNKNOWN_ID, "user not found", "user not found", "user not found", "user not found");
		when(stdService.getById(student.getId())).thenReturn(null);
		mockMvc.perform(post("/student/update/{id}", student.getId()).contentType(MediaType.APPLICATION_JSON).content(asJsonString(student)))
				.andExpect(status().isNotFound());
	}

	// =========================================== Delete Student ============================================

	@Test
	public void testDeleteStudentSuccess() throws Exception {
		Student student = new Student(1, "Karen", "24", "Tainan City", "Computer Science");
		when(stdService.getById(student.getId())).thenReturn(student);
		doNothing().when(stdService).delete(student.getId());
		mockMvc.perform(delete("/student/delete/{id}", student.getId())).andExpect(status().isOk());
	}

	@Test
	public void testDeleteStudentFail404NotFound() throws Exception {
		Student student = new Student(UNKNOWN_ID, "user not found", "user not found", "user not found", "user not found");

		when(stdService.getById(student.getId())).thenReturn(null);
		mockMvc.perform(delete("/student/delete/{id}", student.getId())).andExpect(status().isNotFound());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
