package bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

	@ManyToOne(
		fetch = FetchType.LAZY,
		cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(
		name = "department_id")
	private Department department;

	@OneToMany(
		fetch = FetchType.LAZY,
		mappedBy = "student")
	@JoinColumn(
		name = "student_id")
	private Set<Course> courses;

	public Student() {

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

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
