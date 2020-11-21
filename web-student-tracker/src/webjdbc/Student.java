package webjdbc;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String course;
	
	

	public Student(String firstName, String lastName, String email, String course) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.setCourse(course);
	}

	public Student(int id, String firstName, String lastName, String email, String course) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.setCourse(course);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
