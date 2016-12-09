package bean;

import java.util.Set;

public class Student {

	private Integer id;

	private String name;

	private String age;

	private String address;

	//private Set<Movie> movies;

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
/*
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void addMovies(Movie movie) {
		movies.add(movie);
	}*/

}
