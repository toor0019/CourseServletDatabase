/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author gurki
 */
public class Student {
    
    public static final String COL_ID = "id";
    public static final String COL_FIRSTNAME = "first_name";
    public static final String COL_LASTNAME = "last_name";
    private String firstName;
    private String lastName;
    private int ID;

    public Student() {
    }

    public Student(String firstName, String LastName, int ID) {
        this.firstName = firstName;
        this.lastName = LastName;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }
    
    public String toString(){
    return "Student id:"+ID+" FirstName "+firstName+" LastName "+lastName; 
    }
}
