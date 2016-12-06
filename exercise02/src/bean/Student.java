package bean;

public class Student {

	private Integer id;

	private String name;

	private String age;

	private String address;

	private Department department;

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
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

}
