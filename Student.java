package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author Myra Ribeiro, 169030590
 * @version 2022-01-17
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {
	// assign attributes here
	this.setId(id);
	this.setSurname(surname);
	this.setForename(forename);
	this.setBirthDate(birthDate);
	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "";
	// your code here
	string = String.format("Name:      %s, %s\nID:        %d\nBirthdate: %s", this.surname, // Last name
		this.forename, // First name
		this.id, // Student ID
		this.birthDate // Birthdate
	);
	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	int result = 0;
	result = this.surname.compareTo(target.surname);
	if (result == 0) {
	    result = this.forename.compareTo(target.forename);
	}
	if (result == 0) {
	    result = Integer.compare(this.id, target.id);
	}
	return result;
    }

    // getters and setters defined here
    /**
     * @return the students id number (integer)
     */
    public int getId() {
	return this.id;
    }

    /**
     * @return the students surname (string)
     */
    public String getSurname() {
	return this.surname;
    }

    /**
     * @return the students forename (string)
     */
    public String getForename() {
	return this.forename;
    }

    /**
     * @return the students birthday (localDate)
     */
    public LocalDate getBirthDate() {
	return this.birthDate;
    }

    /**
     * @param id - the students integer id number to be used
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * @param surname - the students string surname to be used
     */
    public void setSurname(String surname) {
	this.surname = surname;
    }

    /**
     * @param forename - the students string forename to be used
     */
    public void setForename(String forename) {
	this.forename = forename;
    }

    /**
     * @param birthDate - the students string birthday to be used in 'YYYY-MM-DD'
     *                  format
     */
    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

}
