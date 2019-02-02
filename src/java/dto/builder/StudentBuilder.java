package dto.builder;

import dto.Student;

public class StudentBuilder {

    private final Student student = new Student();

    public StudentBuilder setFirstName( String firstname) {
        student.setFirstName(firstname);
        return this;
    }

     public StudentBuilder setLastName( String lastname) {
       student.setFirstName(lastname);
        return this;
    }
      
    public StudentBuilder setID( int id) {
        student.setID(id);
        return this;
    }

    public Student get() {
        return student;
    }
}
