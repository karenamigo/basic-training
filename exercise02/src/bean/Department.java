package bean;

import java.util.Set;

public class Department {

	private Integer id;

	private String name;

	private Set<Student> students;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudnets(Set<Student> students) {
		this.students = students;
	}

	public void addStudents(Student student) {
		students.add(student);
	}
}
