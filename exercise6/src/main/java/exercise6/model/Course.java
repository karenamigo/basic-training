package exercise6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
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

	/*@Column(
		name = "std_id")
	private int std_id;
	*/
	@Column(
		name = "name")
	private String name;

	public Course() {

	}

	public Course(int id, String name) {
		this.id = id;
		this.name = name;
		//	this.std_id = std_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public void setStdId(int std_id) {
		this.std_id = std_id;
	}*/

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
