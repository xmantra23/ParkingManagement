/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 * A sub class of person.Can be used for creating a user object to hold information about a user
 * @author samir
 */
public class User extends Person {
    
    private ArrayList<TicketInfo> ticketRecords;//array list for holding ticket information for a user
    private String carTag;//user car tag number
    private String address;//user address
    private int zipCode;//user zip code
    private enum UserType{STUD,FAC,VIS};
    private UserType userType;//user type(stud,fac or vis)
    private String userClassification;//user classification
    
    /**
     * Constructor: Creates a User Object which holds information about a particular user 
     * @param userId is the user Identification Number
     * @param carTag is the user car tag number
     * @param firstName is the user first name
     * @param lastName is the user last name
     * @param dateOfBirth is the user date of birth
     * @param gender is the user gender
     * @param address is the user address
     * @param zipCode is the user zip code
     * @param userType is the user type
     * @param userClassification  is the user classification
     */
    
    
    public User(int userId,String carTag,String firstName,String lastName,Date dateOfBirth,String gender,
               String address,int zipCode,String userType,String userClassification){
        
        //calling the constructor of the super class Person
        super(firstName,lastName,gender.toUpperCase(),dateOfBirth);
        super.setId(userId);//user id is received fromt he file, not generated like for employees
        //checking they type of user
        if(userType.toUpperCase().equals("STUD"))
            this.userType = UserType.STUD;
        else if(userType.toUpperCase().equals("FAC"))
            this.userType = UserType.FAC;
        else
            this.userType = UserType.VIS;
        this.carTag = carTag; //hoding car tag
        this.address = address; // holding address
        this.zipCode = zipCode; // holding zipcode
        this.userClassification = userClassification.toUpperCase();
        ticketRecords = new ArrayList<TicketInfo>();//creating a new arraylist for ticket records
        
    }
    
    /**
     * 
     * @return user car Tag number
     */
    public String getCarTag(){
        return carTag.toUpperCase();//returning car tag in uppper case
    }
    
    /**
     * adds a ticket information for a user(record is kept as an array list )
     * @param record ticket record created as a ticket object
     */
     public void addTicket(TicketInfo record){
        ticketRecords.add(record);//adding a new ticket info to the record
    
    }
     
    /**
     * 
     * @return returns the arraylist that holds all the ticket information for a user
     */ 
    public ArrayList<TicketInfo> getRecords(){
        return ticketRecords;//returning the arraylist containing all the ticket records
    }
     
    /**
     * 
     * @return returns total number of ticket information records for a user 
     */
    public int getRecordSize(){
        return (ticketRecords.size());//returning the total number of ticket records
    }
    
    /**
     * 
     * @return user type(MAINT/COLL/MNTR)
     */
    public String getUserType(){
        return userType.name();
    }
    
    /**
     * 
     * @return user classification(year if stud, department if faculty) 
     */
    public String getUserClass(){
        return userClassification;
    }
    
    /**
     * 
     * @return user Address 
     */
    public String getUserAddress(){
        return address;
    }
    
     /**
     * Overrides the toString method from the superClass
     * @return details of a User in one line formatted
     */
    @Override
    public String toString(){
    
        return String.format("%s %8s %15s %6d",super.toString(),carTag,address,zipCode);
        
    }
    
}//end of class
