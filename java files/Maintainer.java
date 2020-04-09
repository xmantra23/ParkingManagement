/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class: Creates a Maintainer object, extends person Super Class and implements Employee Interface
 * @author samir
 */
public class Maintainer extends Person implements Employee{
    private Date hireDate;//employee hire date
    private Date releaseDate;//employee release date
    private double monthlySalary;//employee monthly salary
    private double onCallRate;//employee on call rate per hour
    
    /**
     * Constructor: Creates a Maintainer object with user provided arguments
     * @param firstName: is the employee first name
     * @param lastName: is the Employee Last Name
     * @param gender: is the Employee Gender
     * @param dateOfBirth: is the Employee Date of Birth
     * @param hireDate: is the Employee Hire Date
     * @param monthlySalary:is the Employee Monthly Base Salary
     * @param onCallRate : is the Employee On Call Rate per hour
     */
    
    public Maintainer(String firstName,String lastName,String gender,Date dateOfBirth,Date hireDate,double monthlySalary,double onCallRate){
        super(firstName,lastName,gender,dateOfBirth);//calling the constructor from the super class
        this.hireDate = hireDate;
        this.monthlySalary = monthlySalary;
        this.onCallRate = onCallRate;
    }
    
    /**
     * Implements the getEmpType method of the Employee interface
     * @return the employee type
     */
    @Override
    public String getEmpType(){
        return ("MAINTAINER");//returning employee type as a string
    }
    
    /**
     * Overrides the toString method of the Employee interface
     * @return the employee type maintainer details in one line formatted
     */
    @Override
    public String toString(){
        return String.format("%s %-15s %-12.2f %-12s\n",super.toString(),hireDate,monthlySalary,"MAINTAINER");
    }
    
    /**
     * implements the computeSalary method of the employee interface
     * @param onCallHours is the on Call Hour rate for this employee type
     * @return the computed salary for this employee
     */
    @Override
    public double computeSalary(int onCallHours){
        double amount = monthlySalary + (onCallHours * onCallRate);//computing salary for maintainer employee
        return amount;
    }
    /**
     * 
     * @return employee base salary 
     */
    @Override
    public double getBaseSalary(){
        return monthlySalary;
    }
    
    
    /**
     * 
     * @return employee hire date
     */
    @Override
    public Date getDOH(){
        return hireDate;
    }
    
    
    /**
     * 
     * @return total number of days employee has worked 
     */
    @Override
    public int getCareerLength(){
        Date today = new Date();
        int days = hireDate.daysBetween(today);
        return days;
    }
    
    
    
}
