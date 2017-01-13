package exercise6.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(
	name = "Student")
public class Student {

	@Id
	@GeneratedValue(
		strategy = GenerationType.AUTO)
	@Column(
		name = "id")
	private Integer id;

	@Column(
		name = "name")
	private String name;

	@Column(
		name = "age")
	private String age;

	@Column(
		name = "address")
	private String address;

	@Column(
		name = "department")
	private String department;

	@OneToMany(
		cascade = CascadeType.ALL)
	@JoinColumn(
		name = "student_id")
	@Autowired
	private List<Course> courses;

	public Student() {

	}

	public Student(int id, String name, String age, String address, String department) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.department = department;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
