package bean;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(
	name = "Course")
public class Course {

	@Id
	@GeneratedValue(
		strategy = GenerationType.AUTO)
	@Column(
		name = "id")
	private int id;

	@Column(
		name = "name")
	private String name;

	public Course() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
