/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * an abstract class for employees and users
 * @author samir
 */
public abstract class Person{
    private static int idCounter;//for generating user id sequentially
    private enum Gender{MALE,FEMALE};
    private int id;//employee identification number
    private Gender gender;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    
    
    /**
     * Constructor: creates a person object with initialized fields
     * @param firstName is the person first name
     * @param lastName is the person last name
     * @param gender is the person gender
     * @param dateOfBirth is the person date of birth
     */
    public Person(String firstName,String lastName,String gender,Date dateOfBirth){
        idCounter++;//incrementing employee counter
        this.setId(idCounter);//setting the employee id to employee counter value
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.dateOfBirth = dateOfBirth;
        this.gender = Gender.valueOf(gender);//setting the gender type as an enum
    }
    
    /**
     * sets the user id to the provided value
     * @param id is the employee identification number
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * @return person gender in upper case 
     */
    
    public String getGender(){
        return gender.name().toUpperCase();//returns gender as a string in upper case
    }
    
    /**
     * 
     * @return person first name in upper case 
     */
    public String getFirstName(){
        return firstName.toUpperCase();//returns first name of the user
    }
    
    
    /**
     * 
     * @return Person last Name in Upper Case
     */
    public String getLastName(){
        return lastName.toUpperCase();//returns last name of the user
    }
    
    
    /**
     * 
     * @return Person date of birth as a Date object 
     */
    public Date getDOB(){
        return dateOfBirth;//returns date of birth
    }
    
    /**
     * 
     * @return Person identification number, either system generated or user set 
     */
    public int getId(){
        return id;//returns user id
    }
    
    /**
     * overrides the toString of object class
     * @return person details in formatted manner
     */
    @Override
    public String toString(){
        return String.format("%-3d %-10s %-10s %-8s %-15s",id,firstName,lastName,gender.name().toUpperCase(),dateOfBirth);
    }
    
    
    
    
}
